package edu.ctb.upm.disnet.service._extract;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ctb.upm.disnet.common.util.Common;
import edu.ctb.upm.disnet.common.util.TimeProvider;
import edu.ctb.upm.disnet.common.util.UniqueId;
import edu.ctb.upm.disnet.constants.Constants;
import edu.ctb.upm.disnet.enums.StatusHttpEnum;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.api_response.DiseaseAlbumResourceService;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.ConfigurationDBpediaDiseaseList;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestFather;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestGDLL;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.Album;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseGDLL;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseLA;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.api_response.WikipediaTextsExtractionService;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.request.Request;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.request.RequestJSON;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.response.Response;
import edu.ctb.upm.disnet.model.WebLink;
import edu.ctb.upm.disnet.model.common.document_structure.Doc;
import edu.ctb.upm.disnet.model.common.document_structure.Link;
import edu.ctb.upm.disnet.model.common.document_structure.Section;
import edu.ctb.upm.disnet.model.common.document_structure.Source;
import edu.ctb.upm.disnet.model.common.document_structure.text.List_;
import edu.ctb.upm.disnet.model.common.document_structure.text.Paragraph;
import edu.ctb.upm.disnet.model.common.document_structure.text.Text;
import edu.ctb.upm.disnet.model.common.document_structure.code.Code;
import edu.ctb.upm.disnet.model.common.document_structure.code.Resource;
import edu.ctb.upm.disnet.model.jpa.*;
import edu.ctb.upm.disnet.model.response.DBpediaResponse;
import edu.ctb.upm.disnet.service.DocumentService;
import edu.ctb.upm.disnet.service._populate.WikipediaPopulateDbNative;
import edu.ctb.upm.disnet.service.helperNative.ConfigurationHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gerardo on 29/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className ExtractService
 * @see
 */
@Service
public class WikipediaExtractService {

    @Autowired
    private WikipediaPopulateDbNative wikipediaPopulateDbNative;
    @Autowired
    private DiseaseAlbumResourceService diseaseAlbumResource;
    @Autowired
    private ConfigurationHelper confHelper;
    @Autowired
    private WikipediaTextsExtractionService wteService;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private Constants constants;
    @Autowired
    private Common common;

    @Autowired
    private DocumentService documentService;


    /**
     * @return
     * @throws Exception
     */
    public boolean extract() throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTime();
        Date version = timeProvider.getSqlDate();
        List<Source> sources = null;
        HashMap<String, Resource> resourceHashMap = null;
        DBpediaResponse dBpediaResponse = getDiseaseLinkListFromDBPedia(version);

        if (dBpediaResponse!=null) {
            resourceHashMap = getWikipediaResources(dBpediaResponse.getLinks(), true, timeProvider.dateFormatyyyMMdd(version));
            sources = getWikipediaTexts(dBpediaResponse.getLinks(), true, timeProvider.dateFormatyyyMMdd(version));

            /*if (resourceHashMap!=null) {
                resourceHashMap.toString();
            }
            if (sources!=null){
                extractionReport(sources);
            }*/

            if (sources!=null&&resourceHashMap!=null) {
                //Proceso que elimina aquellos documentos que durante el proceso de recuperación de
                // datos de wikipedia no se encontraron códigos, ni secciones con textos
                removeInvalidDocumentsProcedure(sources);
                System.out.println("No poblara...");
                //wikipediaPopulateDbNative.populateResource(resourceHashMap);
                //wikipediaPopulateDbNative.populateSemanticTypes();
                //wikipediaPopulateDbNative.populate(sources, version);
                //Insertar la configuración por la que se esta creando la lista
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String configurationJson = gson.toJson(dBpediaResponse.getConfig());
                //confHelper.insert(Constants.SOURCE_WIKIPEDIA, version, constants.SERVICE_DISALBUM_CODE + " - " + constants.SERVICE_DISALBUM_NAME, configurationJson);
                res = true;
            }else{
                System.out.println("ERROR extract texts ans resources");
            }
        }else{
            System.out.println("ERROR disease album");
        }
        System.out.println("Inicio:" + inicio + " | Termino: " + timeProvider.getTime());

        return res;
    }


    /**
     * @param webLinks
     * @return
     */
    public List<Source> getWikipediaTexts(List<WebLink> webLinks, boolean isJSONRequest, String snapshot){
        System.out.println("Start Connection with WIKIPEDIA TEXT EXTRACTION API REST...");
        System.out.println("Get all texts from Wikipedia disease articles... please wait, this process can take from minutes or hours... ");

        List<Source> sourceList = null;
        Response response;
        if (isJSONRequest){
            RequestJSON request = new RequestJSON();
            request.setSnapshot(snapshot);
            response = wteService.getWikipediaTextsByJSON(request);
        }else {
            Request request = new Request();
            request.setWikipediaLinks(webLinks);
            //Opción para guardar JSON y consumirlo despues (queue)
            request.setJson(true);
            response = wteService.getTexts(request);
        }
        System.out.println("End Connection with WIKIPEDIA TEXT EXTRACTION API REST... startTime:" + response.getStart_time() + "endTime: "+ response.getEnd_time());
        System.out.println("getResponseCode: " + response.getResponseCode());
        if (response.getResponseCode().equals(StatusHttpEnum.OK.getClave())){
            sourceList = response.getSources();
        }

        return sourceList;
    }


    /**
     * @param webLinks
     * @return
     */
    public HashMap<String, Resource> getWikipediaResources(List<WebLink> webLinks, boolean isJSONRequest, String snapshot){
        System.out.println("Start Connection with WIKIPEDIA TEXT EXTRACTION API REST...");
        System.out.println("Get all texts from Wikipedia disease articles... please wait, this process can take from minutes or hours... ");

        HashMap<String, Resource> resourceMap = null;
        Response response;
        if (isJSONRequest){
            RequestJSON request = new RequestJSON();
            request.setSnapshot(snapshot);
            response = wteService.getWikipediaResourcesByJSON(request);
        }else {
            Request request = new Request();
            request.setWikipediaLinks(webLinks);
            //Opción para guardar JSON y consumirlo despues (queue)
            request.setJson(true);
            response = wteService.getResources(request);
        }
        System.out.println("End Connection with WIKIPEDIA TEXT EXTRACTION API REST... startTime:" + response.getStart_time() + "endTime: "+ response.getEnd_time());
        System.out.println("getResponseCode: " + response.getResponseCode());

        if (response.getResponseCode().equals(StatusHttpEnum.OK.getClave())){
            resourceMap = response.getResourceHashMap();
        }

        return resourceMap;
    }


    /**
     * @return
     * @throws Exception
     */
    public boolean extractionReport() throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTime();
        Date version = timeProvider.getSqlDate();
        //populateDbNative.onlyExtract();
        System.out.println("Inicio:" + inicio + " | Termino: " + timeProvider.getTime());

        return res;
    }


    //================ GET DISEASE WEB LINKS FROM DBpedia ====================
    /**
     * @return
     */
    public Album getAlbum(){
        RequestFather request = new RequestFather();
        Album album = null;
        request.setToken(Constants.TOKEN);
        System.out.println("Start Connection_ with GET DISEASE ALBUM API REST...");
        System.out.println("Get Album Information... please wait, this process can take from minutes... ");
        ResponseLA response = diseaseAlbumResource.getDiseaseAlbum(request);System.out.println(response.getAlbum().getAlbumId());
        System.out.println("Authorization: "+ response.isAuthorized());
        System.out.println("End Connection_ with GET DISEASE ALBUM API REST...");
        if (response.isAuthorized())
            album = response.getAlbum();
        return album;
    }


    /**
     * @param version
     * @return
     */
    public DBpediaResponse getDiseaseLinkListFromDBPedia(Date version){
        DBpediaResponse dBpediaResponse = null;

        //Se obtiene el identificador de lista de enfermedades recuperadas desde DBpedia "Album de enfermedades"
        Album album = getAlbum();
        if (album != null) {
            List<WebLink> webLinkList = new ArrayList<>();
            System.out.println("Start Connection_ with GET DISEASE ALBUM API REST...");
            System.out.println("Get diseases... please wait, this process can take from minutes... ");
            RequestGDLL request = new RequestGDLL();
            request.setSource(Constants.SOURCE_WIKIPEDIA);
            request.setAlbum(album.getAlbumId());
            request.setVersion(new SimpleDateFormat("yyyy-MM-dd").format(album.getDate()));
            request.setToken(Constants.TOKEN);
            System.out.println("request: " + request);
            //Se obtiene la última lista de enfermedades recuperadas desde DBpedia según su identificador "albumId"
            ResponseGDLL response = diseaseAlbumResource.getDiseaseLinkList(request);
            System.out.println("Authorization: " + response.isAuthorized());
            System.out.println("End Connection_ with GET DISEASE ALBUM API REST..." + response.getDiseases().size());
            if (response.isAuthorized()) {
                int count = 1;
                for (edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response
                        .Disease disease : response.getDiseases()) {
                    WebLink webLink = new WebLink();
                    webLink.setId(count);
                    webLink.setUrl(common.replaceUnicodeToSpecialCharacters(disease.getUrl()));
                    webLinkList.add(webLink);
                    count++;
                }
                //Validar que el source tenga información
                ConfigurationDBpediaDiseaseList conf = new ConfigurationDBpediaDiseaseList();
                conf.setAlbumId(album.getAlbumId());
                conf.setVersion( timeProvider.dateFormatyyyMMdd(album.getDate()) );
                conf.setNumberDiseases(album.getNumberDiseases());
                conf.setSource(Constants.SOURCE_WIKIPEDIA_CODE);
                conf.setServiceCode(constants.SERVICE_DISALBUM_CODE);
                List<String> requestList = new ArrayList<String>(){{
                    add(constants.SERVICE_DISALBUM_PATH_LAST);
                    add(constants.SERVICE_DISALBUM_PATH_GET);
                }};
                conf.setRequests(requestList);
                //Se forma la respuesta del método
                dBpediaResponse = new DBpediaResponse();
                dBpediaResponse.setLinks(webLinkList);
                dBpediaResponse.setConfig(conf);
            }
        }
        return dBpediaResponse;
    }
    //=========================================================================


    /**
     * @param sources
     * @return
     */
    public void removeInvalidDocumentsProcedure(List<Source> sources){
        boolean hasCodes = false;
        boolean hasSections = false;

        int countValid = 1;

        System.out.println("-------------------- VALIDATION DOCUMENT PROCEDURE --------------------");
        for (Source source : sources) {
            for (Doc document : source.getDocuments()) {
                if (document.getCodeList().size() > 0) hasCodes = true;
                if (document.getSectionList().size() > 0) hasSections = true;
                if (hasCodes || hasSections) {
                    document.setDiseaseArticle(true);
                    countValid++;
                    hasCodes = false;
                    hasSections = false;
                }
                //System.out.println("Doc(|" + document.getId() + " | " + document.getDate() + " | " + document.isDiseaseArticle() + " | " + document.getUrl().getUrl());
            }
            System.out.println("All Documents: " + source.getDocuments().size());
            System.out.println("Valid Documents: " + countValid);
            System.out.println("Invalid Documents: " + (source.getDocuments().size() - countValid));
        }
    }


    /**
     * Método que muestra un reporte de la extracción recien hecha
     *
     * @throws Exception
     */
    public void extractionReport(List<Source> sourceList) throws Exception {

        List<Integer> countCharacteresList = new ArrayList<>();
        long time_start, time_end;

        boolean hasCodes = false;
        boolean hasSections = false;

        int countValid = 1;

        System.out.println("-------------------- EXTRACTION REPORT --------------------");
        for (Source source : sourceList) {
            System.out.println("\n");
            System.out.println("-------------------- SOURCE(" + source.getId() + "_" + source.getName() + ") --------------------");
            for (Doc document: source.getDocuments()) {
                //System.out.println("Doc(" + document.getId() + "_" + document.getDate() + ") => " + document.getUrl().getUrl());
                //System.out.println("    Disease(" + document.getDisease().getId() + "_" + document.getDisease().getName() + ") ");

                //System.out.println("    Codes list...:");
                for (Code code: document.getCodeList()) {
                    //System.out.println("        Code_" + code.getId() + "[" + code.getResource().getName() + "]: " + code.getCode() + " URL_CODE:" + code.getLink().getUrl() );
                }

                if (document.getCodeList().size() > 0) hasCodes = true;

                for (Section section: document.getSectionList()) {
                    //System.out.println("    Section(" + section.getId() + ") " + section.getName() );

                    for (Text text : section.getTextList()) {
                        //System.out.println("    ------------ TEXT(" + text.getTitle() + ") -----------");
                        //System.out.println("        Text_" + text.getTextOrder() + "(" + text.getId() + ") (" + text.getClass() + ")" );

                        String aux = "";
                        if(text instanceof Paragraph){
                            //System.out.println("            " + ( (Paragraph) text).getText() );
                            countCharacteresList.add( ( (Paragraph) text).getText().length() );
                        }else if(text instanceof List_){
                            for (String bullet: ( (List_) text).getBulletList() ) {
                                //System.out.println("            -" + bullet);
                                aux = aux + bullet + "&";
                            }
                            //if(aux.length() > 2){aux = aux.substring(0, aux.length()-1);}
                            //System.out.println(" aux = " + aux);
                            countCharacteresList.add( aux.length() );
                        }

                        //System.out.println("        ------------ LINKS -----------");
                        for (Link url: text.getUrlList()) {
                            //System.out.println("            Key: " + url.getId() + ": URL(" + url.getDescription() + "): " + url.getUrl() );
                        }

                    }
                }
                if (document.getSectionList().size() > 0) hasSections = true;

                if (hasCodes || hasSections) {
                    document.setDiseaseArticle(true);
                    countValid++;
                    hasCodes = false;
                    hasSections = false;
                }
                System.out.println("Doc(|" + document.getId() + " | " + document.getDate() + " | " + document.isDiseaseArticle() + " | " + document.getUrl().getUrl());

            }

            System.out.println("# de Documentos " + source.getDocuments().size());
            System.out.println("# de Documentos válidos " + countValid);
            System.out.println("# de Documentos no válidos " + (source.getDocuments().size() - countValid) );

        }



/*
        System.out.println("=========================================================");
        Collections.sort(countCharacteresList);
        for (Integer i:
             countCharacteresList) {
            System.out.println(i);
        }
*/



        //System.out.println("the task (model informtacion of diseases from wikipedia) has taken "+ ( (time_end - time_start) / 1000 ) +" seconds");

    }


    public void onlyExtract(){
        List<WebLink> xmlLinks = new ArrayList<>();
        List<Document> documents = documentService.findAll();
        System.out.println("size: "+documents.size());
        int count = 1;
        for (Document document: documents) {
            if (document.getDate().toString().equals("2018-02-15")){
                List<HasDisease> hasDiseases = document.getHasDiseases();
                String diseaseName = "";
                for (HasDisease hasDisease:hasDiseases){
                    Disease disease = hasDisease.getDiseaseByDiseaseId();
                    diseaseName = diseaseName + disease.getName() + "; ";
                }
                List<DocumentUrl> documentUrls = document.getDocumentUrls();
                String urls = "";
                WebLink xmlLink = new WebLink();
                for (DocumentUrl documentUrl: documentUrls) {
                    String urlId = documentUrl.getUrlId();
                    Url url = documentUrl.getUrlByUrlId();
                    urls = urls + url.getUrl() + "; ";

                    xmlLink.setUrl(url.getUrl());
                    xmlLink.setConsult(diseaseName);
                    break;
                }
                System.out.println("Disease: (" + count +")" + xmlLink.getConsult() + " | URL: " + xmlLink.getUrl());
                xmlLinks.add(xmlLink);
                count++;
            }
        }
        //extractService.onlyExtract(xmlLinks);
    }

}
