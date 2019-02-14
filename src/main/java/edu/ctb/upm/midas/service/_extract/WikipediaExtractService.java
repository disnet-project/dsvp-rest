package edu.ctb.upm.midas.service._extract;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ctb.upm.midas.client_modules.extraction.wikipedia.disease_list.api_response.DiseaseAlbumResourceService;
import edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.api_response.WikipediaTextsExtractionService;
import edu.ctb.upm.midas.common.util.Common;
import edu.ctb.upm.midas.common.util.TimeProvider;
import edu.ctb.upm.midas.common.util.UniqueId;
import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.enums.StatusHttpEnum;
import edu.ctb.upm.midas.model.WebLink;
import edu.ctb.upm.midas.model.common.document_structure.Doc;
import edu.ctb.upm.midas.model.common.document_structure.Link;
import edu.ctb.upm.midas.model.common.document_structure.Section;
import edu.ctb.upm.midas.model.common.document_structure.Source;
import edu.ctb.upm.midas.model.common.document_structure.code.Code;
import edu.ctb.upm.midas.model.common.document_structure.code.Resource;
import edu.ctb.upm.midas.model.common.document_structure.text.List_;
import edu.ctb.upm.midas.model.common.document_structure.text.Paragraph;
import edu.ctb.upm.midas.model.common.document_structure.text.Text;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestAlbum;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestFather;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestGDLL;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response.*;
import edu.ctb.upm.midas.model.extraction.wikipedia.texts_extraction.request.Request;
import edu.ctb.upm.midas.model.jpa.Document;
import edu.ctb.upm.midas.model.jpa.DocumentUrl;
import edu.ctb.upm.midas.model.jpa.HasDisease;
import edu.ctb.upm.midas.model.jpa.Url;
import edu.ctb.upm.midas.service._populate.WikipediaPopulateDbNative;
import edu.ctb.upm.midas.service.jpa.DocumentService;
import edu.ctb.upm.midas.service.jpa.helperNative.ConfigurationHelper;
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

    private String snapshot;

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * @param snapshot
     * @param json
     * @return
     * @throws Exception
     */
    public boolean extract(String snapshot, boolean json) throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTime();
        Date version = (json)?timeProvider.stringToDate(snapshot):timeProvider.getSqlDate();
        this.snapshot = timeProvider.dateFormatyyyyMMdd(version);
        List<Source> sources = null;
        HashMap<String, Resource> resourceHashMap = null;
        DBpediaResponse dBpediaResponse = getDiseaseLinkListFromDBPedia(version);
        System.out.println("snapshot="+timeProvider.dateFormatyyyyMMdd(version)+", json="+json);

        if (dBpediaResponse!=null) {
            resourceHashMap = retrieveResources(dBpediaResponse.getLinks(), timeProvider.dateFormatyyyyMMdd(version), json);
            sources = retrieveTexts(dBpediaResponse.getLinks(), timeProvider.dateFormatyyyyMMdd(version), json);

            /*if (resourceHashMap!=null) {
                resourceHashMap.toString();
            }
            if (sources!=null){
                printReport(sources);
            }*/

            if (sources!=null&&resourceHashMap!=null) {
                //Proceso que elimina aquellos documentos que durante el proceso de recuperación de
                // datos de wikipedia no se encontraron códigos, ni secciones con textos
                removeInvalidDocumentsProcedure(sources);
                //System.out.println("No poblara...");
                wikipediaPopulateDbNative.populateResource(resourceHashMap);
                wikipediaPopulateDbNative.populateSemanticTypes();
                wikipediaPopulateDbNative.populate(sources, version, json);
                //Insertar la configuración por la que se esta creando la lista
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String configurationJson = gson.toJson(dBpediaResponse.getConfig());
                confHelper.insert(Constants.SOURCE_WIKIPEDIA, version, constants.SERVICE_DISALBUM_CODE, configurationJson);
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
    public List<Source> retrieveTexts(List<WebLink> webLinks, String snapshot, boolean isJSONRequest){
        System.out.println("Start Connection with WIKIPEDIA TEXT EXTRACTION API REST...");
        System.out.println("Get all texts from Wikipedia disease articles... please wait, this process can take from minutes or hours... ");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Source> sourceList = null;
        Response response;
        if (isJSONRequest){
            RequestJSON request = new RequestJSON();
            request.setSnapshot(snapshot);
            response = wteService.getWikipediaTextsByJSON(request);
//            System.out.println(gson.toJson(response));
        }else {
            Request request = new Request();
            request.setWikipediaLinks(webLinks);
            //Opción para guardar JSON y consumirlo despues (queue)
            request.setJson(true);
            request.setSnapshot(snapshot);
            response = wteService.getTexts(request);
        }
        System.out.println("End Connection with WIKIPEDIA TEXT EXTRACTION API REST... startTime:" + response.getStart_time() + "endTime: "+ response.getEnd_time());
        System.out.println("getResponseCode: " + response.getResponseCode() + " => "+ response.getResponseMessage());
        if (response.getResponseCode().equals(StatusHttpEnum.OK.getClave())){
            sourceList = response.getSources();
        }

        return sourceList;
    }


    /**
     * @param webLinks
     * @return
     */
    public HashMap<String, Resource> retrieveResources(List<WebLink> webLinks, String snapshot, boolean isJSONRequest){
        System.out.println("Start Connection with WIKIPEDIA TEXT EXTRACTION API REST...");
        System.out.println("Get all texts from Wikipedia disease articles... please wait, this process can take from minutes or hours... ");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
            request.setSnapshot(snapshot);
            response = wteService.getResources(request);
        }
//        System.out.println(gson.toJson(response));
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
    public boolean extractionReport(List<WebLink> webLinks, String snapshot, boolean isJSONRequest) throws Exception {
        boolean res = false;
        String inicio = timeProvider.getTime();
        Date version = timeProvider.getSqlDate();
        List<Source> sources = retrieveTexts(webLinks, snapshot, isJSONRequest);
        printReport(sources, isJSONRequest);
        System.out.println("Inicio:" + inicio + " | Termino: " + timeProvider.getTime());

        return res;
    }


    //================ GET DISEASE WEB LINKS FROM DBpedia ====================
    /**
     * @param snapshot
     * @return
     */
    public Album getSpecifictAlbum(String snapshot){
        RequestAlbum request = new RequestAlbum();
        Album album = null;
        request.setSource(Constants.SOURCE_WIKIPEDIA);
        request.setSnapshot(snapshot);
        request.setToken(Constants.TOKEN);
        System.out.println("Start Connection_ with GET DISEASE ALBUM API REST...");
        System.out.println("Get Album Information... please wait, this process can take from minutes... ");
        ResponseLA response = diseaseAlbumResource.getSpecificAlbum(request);
        System.out.println(response.getAlbum());
        System.out.println("Authorization: "+ response.isAuthorized());
        System.out.println("End Connection_ with GET DISEASE ALBUM API REST...");
        if (response.isAuthorized())
            album = response.getAlbum();
        return album;
    }


    /**
     * @return
     */
    public Album getLastAlbum(){
        RequestFather request = new RequestFather();
        Album album = null;
        request.setToken(Constants.TOKEN);
        System.out.println("Start Connection_ with GET DISEASE ALBUM API REST...");
        System.out.println("Get Album Information... please wait, this process can take from minutes... ");
        ResponseLA response = diseaseAlbumResource.getDiseaseAlbum(request);
        System.out.println(response.getAlbum().getAlbumId() + " - " + response.getAlbum().getDate());
        System.out.println("Authorization: "+ response.isAuthorized());
        System.out.println("End Connection_ with GET DISEASE ALBUM API REST...");
        if (response.isAuthorized())
            album = response.getAlbum();
        return album;
    }


    /**
     * @param snapshot
     * @return
     * @throws InterruptedException
     */
    public DBpediaResponse getDiseaseLinkListFromDBPedia(Date snapshot) throws InterruptedException {
        DBpediaResponse dBpediaResponse = null;

        //Se obtiene el identificador de lista de enfermedades recuperadas desde DBpedia "Album de enfermedades"
        Album album = null;
        while(true){
            album = getLastAlbum();
//            album = getSpecifictAlbum(date.dateFormatyyyyMMdd(version));
            System.out.println(timeProvider.dateFormatyyyyMMdd(album.getDate()) + " == " + timeProvider.dateFormatyyyyMMdd(snapshot));
            if (timeProvider.dateFormatyyyyMMdd(album.getDate()).equals(timeProvider.dateFormatyyyyMMdd(snapshot))) break;
            System.out.println("Wait (1 hour = 3600000 mls) for another disease list request");
            Thread.sleep(3600000);
        }
        if (album != null) {
            List<WebLink> webLinkList = new ArrayList<>();
            System.out.println("Start Connection_ with GET DISEASE ALBUM API REST...");
            System.out.println("Get diseases... please wait, this process can take from minutes... ");
            RequestGDLL request = new RequestGDLL();
            request.setSource(Constants.SOURCE_WIKIPEDIA);
            request.setAlbum(album.getAlbumId());
            request.setSnapshot(new SimpleDateFormat("yyyy-MM-dd").format(album.getDate()));
            request.setToken(Constants.TOKEN);
            System.out.println("request: " + request);
            //Se obtiene la última lista de enfermedades recuperadas desde DBpedia según su identificador "albumId"
            ResponseGDLL response = diseaseAlbumResource.getDiseaseLinkList(request);
            System.out.println("Authorization: " + response.isAuthorized());
            System.out.println("End Connection_ with GET DISEASE ALBUM API REST..." + response.getDiseases().size());
            if (response.isAuthorized()) {
                int count = 1;
                for (Disease disease: response.getDiseases()) {
                    WebLink webLink = new WebLink();
                    webLink.setId(count);
                    webLink.setUrl(common.replaceUnicodeToSpecialCharacters(disease.getUrl()));
                    webLink.setRelevant(isRelevant(disease));
                    System.out.println(webLink);
                    webLinkList.add(webLink);
                    count++;
                }
                //Validar que el source tenga información
                ConfigurationDBpediaDiseaseList conf = new ConfigurationDBpediaDiseaseList();
                conf.setAlbumId(album.getAlbumId());
                conf.setVersion( timeProvider.dateFormatyyyyMMdd(album.getDate()) );
                conf.setNumberDiseases(album.getNumberDiseases());
                conf.setSource(Constants.SOURCE_WIKIPEDIA_CODE);
                conf.setServiceCode(constants.SERVICE_DISALBUM_CODE);
                conf.setUseDiseaseSafeList(response.isUseDiseaseSafeList());
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


    /**
     * Método que se encarga de marcar como relevante una enfermedad
     *
     * Recibe una enfermedad y verifica los parámetros de irrelevancia para
     * calcular la relevancia
     *
     * Parcialmente irrelevante | Totalmente irrelevante | RELEVANTE
     * FALSE(0)                   FALSE(0)               = TRUE(1)  *
     * FALSE(0)                   TRUE(1)                = FALSE(0)
     * TRUE(1)                    FALSE(0)               = TRUE(1)  *
     * TRUE(1)                    TRUE(1)                = FALSE(0)
     *
     * @param disease
     * @return
     */
    public boolean isRelevant(Disease disease){
        boolean isRelevant = false;
        if ((disease.isPartlyIrrelevant()==false && disease.isTotallyIrrelevant()==false)
                || (disease.isPartlyIrrelevant() && disease.isTotallyIrrelevant()==false))
            isRelevant = true;
        if (disease.isTotallyIrrelevant()) isRelevant = false;
        return isRelevant;
    }
    //================= GET DISEASE WEB LINKS FROM DBpedia =====================


    /**
     * @param sources
     * @return
     */
    public void removeInvalidDocumentsProcedure(List<Source> sources){
        boolean hasCodes = false;
        boolean hasSections = false;

        int countValid = 1;

        System.out.println("-------------------- DOCUMENT VALIDATION PROCEDURE --------------------");
        for (Source source : sources) {
            for (Doc document : source.getDocuments()) {
                if (document.getCodeList().size() > 0) hasCodes = true;
                if (document.getSectionList().size() > 0) hasSections = true;
                if (hasCodes || hasSections) {//AGREGAR ACTUALIZACIÓN para que no considere artículos de una lista de artículos detectados
                    document.setDiseaseArticle(true);
                    countValid++;
                    //Reinicia los valores
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
    public void printReport(List<Source> sourceList, boolean isJSONRequest) throws Exception {

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
                        if (isJSONRequest) text = common.getTypeText(text);
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
                    edu.ctb.upm.midas.model.jpa.Disease disease = hasDisease.getDiseaseByDiseaseId();
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
