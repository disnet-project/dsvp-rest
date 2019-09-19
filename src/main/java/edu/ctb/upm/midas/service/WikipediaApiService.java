package edu.ctb.upm.midas.service;

import com.google.gson.*;
import edu.ctb.upm.midas.common.util.Common;
import edu.ctb.upm.midas.common.util.TimeProvider;
import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.model.wikipediaApi.*;
import edu.ctb.upm.midas.service.jpa.DocumentService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class WikipediaApiService {

    private static final Logger logger = LoggerFactory.getLogger(WikipediaApiService.class);

    private String encoding = "UTF-8";
    @Autowired
    private DocumentService documentService;

    public void init(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Common common = new Common();
        TimeProvider timeProvider = new TimeProvider();

        List<Disease> diseases = documentService.findAllDistinctArticlesAndSnapshot();
        int count = 1, total = diseases.size();
        if (diseases.size()>0) {
            for (Disease disease : diseases) {
                logger.info(count + ". DISEASE to " + total + " (" + (count*100)/total + "%)." + disease.getName() /*+ " | " + disease.getSnapshotId() + " | " + disease.getCurrentSnapshot() + " | " + disease.getPreviousSnapshot()*/);
                List<Snapshot> snapshots = documentService.findAllSnapshotsOfAArticle(disease.getId());
                if (snapshots!=null) {
                    Page page = getPageIdAndTheirSpecificRevisionByTitleAndSnapshot(disease.getName(), snapshots);
                    disease.setPage(page);
                    disease.setSnapshots(snapshots);
                    //Escribir json
                    try {
                        String fileNAme = common.writeAnalysisJSONFile(gson.toJson(disease), disease, count, timeProvider.getNowFormatyyyyMMdd());
                        logger.info("Write JSON file successful! => " + fileNAme);
                    }catch (Exception e){logger.error("Error to write the JSON file", e);}
                }
//                if (count==3) break;
                count++;
            }
            logger.info("End procedure");
        }
//        System.out.println(diseases.toString());
    }


    public void test(){
        List<Snapshot> snapshots = new ArrayList<Snapshot>(){{
            add(new Snapshot(1, "2018-02-01", "2018-02-01"));
            add(new Snapshot(2, "2018-02-15", "2018-02-15"));
        }};
        Page page = getPageIdAndTheirSpecificRevisionByTitleAndSnapshot("Ciguatera", snapshots);
//        System.out.println(page);
    }


    public Page getPageIdAndTheirSpecificRevisionByTitleAndSnapshot(String pageTitle, List<Snapshot> snapshots){
        Page page = new Page();
        Revision previousR = null;
        List<Revision> revisionList = new ArrayList<>();
        try {
            int snapshotCount = 1;
            for (Snapshot snapshot: snapshots) {
                String responseWikipediaAPI = getWikipediaApiQueryResponse(pageTitle, snapshot.getSnapshot());
                Revision revision = null;
//            System.out.println("Wikipedia API response = " + responseWikipediaAPI);

                //Parser string response Wikipedia API to Java JSON object
                JsonElement jsonElement = parseWikipediaResponse(responseWikipediaAPI);
                //Get information from Json object
                JsonElement pages = jsonElement.getAsJsonObject().get("query").getAsJsonObject().get("pages");
                //Obtiene todos los elementos de un JsonElement en forma de mapa
                Set<Map.Entry<String, JsonElement>> elementPages = pages.getAsJsonObject().entrySet();

                //Valida que el mapa no sea nulo
                if (elementPages!=null) {
                    //Recorre los elementos del mapa que corresponde al elemento con el pageid,
                    // => " {"24811533": "
                    for (Map.Entry<String, JsonElement> elementPage : elementPages) {
//                System.out.println( elementPage.getKey()+ " <-> " + elementPage.getValue());
                        //Obtiene todos los elementos de un JsonElement en forma de mapa
                        Set<Map.Entry<String, JsonElement>> elementPageInfo = elementPage.getValue().getAsJsonObject().entrySet();
                        //Valida que el mapa del elemento pages no sea nulo
                        if (elementPageInfo!=null) {
                            //Recorre los elementos del mapa
                            for (Map.Entry<String, JsonElement> element : elementPageInfo) {
                System.out.println( element.getKey()+ " <-> " + element.getValue());

                                //Verifica cada elemento para asignar sus valores a los campos correspondientes
                                //del recien creado objeto Page
                                getPageIdAndSetInPageObject(page, element);
                                getPageTitleAndSetInPageObject(page, element);
//                                System.out.println(element.getKey() + " - " + element.getValue());
                                //Valida que el elemento del mapa sea de nombre "revisions". Porque necesita
                                //ser tratado especialmente para poder acceder a sus elementos y valores
                                if (element.getKey().equalsIgnoreCase(Constants.REVISIONS_ELEMENT_NAME)) {
                                    //Parsea el elemento revision
                                    JsonElement revisionsSet = parseWikipediaResponse(
                                            //Para dar formato y hacer el parse a Json del String, es necesario
                                            //quitar el primer y el último elemento que son "[" y "]" para obtener un
                                            //String(JSON) que inicie y termine con "{" y "}"
                                            deleteFirstAndLastChar(element.getValue().toString())
                                    );
                                    //Doble verificación para saber si el elemento "revisions" es un objeto Json
                                    boolean isJsonObject = revisionsSet.isJsonObject();
                                    if (isJsonObject) {
                                        revision = new Revision();
                                        //Se recorren los elementos del mapa "revisions"
                                        for (Map.Entry<String, JsonElement> revElement : revisionsSet.getAsJsonObject().entrySet()) {
                                            getRevIdAndSetInRevisionObject(revision, revElement);
                                            getParentIdAndSetInRevisionObject(revision, revElement);
                                            getMinorAndSetInRevisionObject(revision, revElement);
                                            getUserAndSetInRevisionObject(revision, revElement);
                                            getUserIdAndSetInRevisionObject(revision, revElement);
                                            getTimestampAndSetInRevisionObject(revision, revElement);
                                            getSizeAndSetInRevisionObject(revision, revElement);
                                            getCommentAndSetInRevisionObject(revision, revElement);

//                                        System.out.println(revElement.getKey() + " - " + revElement.getValue());
                                        }
                                        if (previousR==null){
                                            revision.setPreviousDate("");
                                        }else{
                                            revision.setPreviousDate(previousR.getDate());
                                        }
                                        snapshot.setRevId(revision.getRevid());
                                        revisionList.add(revision);
                                    }//END if (isJsonObject)
                                }//END if compare if the element is kind of "revisions"
                                if (element.getKey().equalsIgnoreCase(Constants.REDIRECTS_ELEMENT_NAME)) {
                                    JsonArray redirectSet = element.getValue().getAsJsonArray();
                                    //Doble verificación para saber si el elemento "revisions" es un objeto Json
                                    if (redirectSet!=null) {
                                        for (JsonElement redirectElement : redirectSet) {
                                            JsonObject redirectObj = redirectElement.getAsJsonObject();
                                            Integer redirectpageid = (redirectObj.get("pageid") instanceof JsonNull)?0:(redirectObj.get("pageid").getAsInt());
                                            String redirectpagetitle = redirectObj.get("title").getAsString();
                                            if (pageTitle.equalsIgnoreCase(redirectpagetitle)){
                                                page.setIsredirect(true);
                                                page.setRedirectpageid(redirectpageid);
                                                page.setRedirectpagetitle(redirectpagetitle);
                                                break;
                                            }
                                        }

                                        System.out.println("ENTRA red");
                                    }
                                }//END if compare if the element is kind of "redirects"
                            }//END for that each element of pages element
                        }//END if (elementPageInfo!=null)
                    }
                }//END if (elementPages!=null)

                //Si las fechas son iguales significa que se trata de la misma actualización (revision) y por lo tanto
                //el mismo texto de la anterior se debe colocar en la actual actualización (revision)
                //con el fin de no hacer llamadas de más a la "Wikipedia API"
                if (revision.getDate().equalsIgnoreCase(revision.getPreviousDate())){
                    //Si es la misma versión se copia la información del texto y de las secciones de la
                    //actualización (revision) anterior
//                    System.out.println("ES LA MISMA REVISIÓN: (" + revision.getDate() + "==" + revision.getPreviousDate() + ") => (" + revision.getSnapshot() + ")");
                    revision.setText(previousR.getText());
                    revision.setSectionCount(previousR.getSectionCount());
                    revision.setSections(previousR.getSections());
                    revision.setCharacterCount(previousR.getCharacterCount());
                }else{
//                    System.out.println("NO ES LA MISMA REVISIÓN: (" + revision.getDate() + "==" + revision.getPreviousDate() + ") => (" + revision.getSnapshot() + ")");
                    getRevisionTextAndSectionList(revision);
                }

//                System.out.println(revision.toString());

                snapshotCount++;
                previousR = revision;
            }
            removeRepetedRevision(revisionList);
            if (page!=null) page.setRevisions(revisionList);
        }catch (Exception e){
            logger.error("Error getPageIdAndTheirSpecificRevisionByTitleAndSnapshot ", e);
        }
        return page;
    }


    public List<Revision> removeRepetedRevision(List<Revision> elements){
        List<Revision> resList = elements;
        Set<Revision> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.addAll(elements);
        elements.clear();
        elements.addAll(linkedHashSet);
        return resList;
    }


    public Long getNumberOfCharactersOfAllTextsFromARevision(String htmlText){
        Long characterCount = 0L;
        Long paragraphCharacterCount = 0L;
        Long tableCharacterCount = 0L;
        Long listCharacterCount = 0L;
        Long imgCharacterCount = 0L;
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlText);

        Elements paragraphs = doc.getElementsByTag(Constants.HTML_P);
        Elements tables = doc.getElementsByTag(Constants.HTML_TABLE);
        Elements ulElements = doc.getElementsByTag(Constants.HTML_UL);
        Elements olElements = doc.getElementsByTag(Constants.HTML_OL);
        Elements dlElements = doc.getElementsByTag(Constants.HTML_DL);
        //todos los caption de imagenes, exepto la del infobox,
        //que esa ya se obtiene en "tables"
        Elements imgElements = doc.select(Constants.QUERY_DIV_CLASS + "thumbcaption" + Constants.RIGHT_PARENTHESIS);

        for (Element paragraph: paragraphs) {
            paragraphCharacterCount = paragraphCharacterCount + (long) paragraph.text().length();
        }
        for (Element table: tables) {
//            System.out.println(table.text() + " - " + table.ownText());
            tableCharacterCount = tableCharacterCount + (long) table.text().length();
        }
        for (Element ulElement: ulElements) {
            listCharacterCount = listCharacterCount + (long) ulElement.text().length();
        }
        for (Element olElement: olElements) {
            listCharacterCount = listCharacterCount + (long) olElement.text().length();
        }
        for (Element dlElement: dlElements) {
            listCharacterCount = listCharacterCount + (long) dlElement.text().length();
        }
        for (Element imgElement: imgElements) {
            imgCharacterCount = imgCharacterCount + (long) imgElement.text().length();
        }


//        logger.info("====================================================== ( PARA:"+paragraphCharacterCount+" TBL:"+tableCharacterCount+" LIST:"+listCharacterCount + " IMG:" + imgCharacterCount + " ) => " + (paragraphCharacterCount + tableCharacterCount + listCharacterCount + imgCharacterCount) );

        characterCount = (paragraphCharacterCount + tableCharacterCount + listCharacterCount + imgCharacterCount);

//        System.out.println("HTML_PARSE => " + doc.outerHtml());

        return characterCount;
    }


    public String getRevisionTextAndSectionList(Revision revision){
        Common common = new Common();
        String text = "";
        try {

            String responseWikipediaAPI = getRevisionTextAndSectionsWikipediaApiQueryResponse(revision.getRevid());
//            System.out.println("Wikipedia API response = " + responseWikipediaAPI);

            //Parser string response Wikipedia API to Java JSON object
            JsonElement jsonElement = parseWikipediaResponse(responseWikipediaAPI);
            //Get information from Json object
            JsonElement parse = jsonElement.getAsJsonObject().get("parse");
            JsonElement sections = jsonElement.getAsJsonObject().get("parse").getAsJsonObject().get("sections");
            //Obtiene todos los elementos de un JsonElement en forma de mapa
            Set<Map.Entry<String, JsonElement>> parseElements = parse.getAsJsonObject().entrySet();
            JsonArray sectionElements = sections.getAsJsonArray();


            //Valida que el mapa no sea nulo
            if (parseElements!=null) {
                for (Map.Entry<String, JsonElement> parseElement : parseElements) {
//                    System.out.println( parseElement.getKey()+ " <-> " + parseElement.getValue());
                    getTextAndSetInRevisionObject(revision, parseElement);
                }
//                System.out.println("    TEXT: " + revision.getText());
            }

            if (sectionElements!=null){
                List<Section> sectionList = new ArrayList<>();
                for (JsonElement sectionElement : sectionElements) {
                    JsonObject sectionObj = sectionElement.getAsJsonObject();
                    Integer toclevel = (sectionObj.get("toclevel") instanceof JsonNull)?0:(sectionObj.get("toclevel").getAsInt());
                    String level = sectionObj.get("level").getAsString();
                    String line = sectionObj.get("line").getAsString();
                    String number = sectionObj.get("number").getAsString();
                    String index = sectionObj.get("index").getAsString();
                    String fromtitle = (common.isEmpty(sectionObj.get("fromtitle").getAsString()))?"":(sectionObj.get("fromtitle").getAsString());//Este es un ejemplo de que algunos elementos no son devueltos o no existen. Ver Hepatomegaly
                    Integer byteoffset = (sectionObj.get("byteoffset") instanceof JsonNull)?0:(sectionObj.get("byteoffset").getAsInt());
                    String anchor = sectionObj.get("anchor").getAsString();

                    Section section = new Section(toclevel, level, line, number, index, fromtitle, byteoffset, anchor);
                    sectionList.add(section);
                }
                revision.setSections(sectionList);
                revision.setSectionCount(sectionList.size());
                revision.setCharacterCount(getNumberOfCharactersOfAllTextsFromARevision(revision.getText()));
//                System.out.println(revision.getRevid());
            }

        }catch (Exception e){
            logger.error("Error to get revision text Wikipedia API", e);
        }
        return text;
    }


    public Page getRedirectInfo(Disease disease){
        Page page = null;

        return page;
    }


    public void getTextAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_TEXT_NAME.equalsIgnoreCase(element.getKey())) revision.setText(element.getValue().getAsString());
    }


    public void getCommentAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_COMMENT_NAME.equalsIgnoreCase(element.getKey())) revision.setComment(element.getValue().getAsString());
    }


    public void getSizeAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_SIZE_NAME.equalsIgnoreCase(element.getKey())) revision.setSize(element.getValue().getAsInt());
    }


    public void getTimestampAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        TimeProvider timeProvider = new TimeProvider();
        if (Constants.REVISIONS_ELEMENT_TIMESTAMP_NAME.equalsIgnoreCase(element.getKey())) {
            revision.setTimestamp(element.getValue().getAsString());
            revision.setDate(element.getValue().getAsString().substring(0, 10));
        }
    }


    public void getUserIdAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_USERID_NAME.equalsIgnoreCase(element.getKey())) revision.setUserid(element.getValue().getAsInt());
    }


    public void getUserAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_USER_NAME.equalsIgnoreCase(element.getKey())) revision.setUser(element.getValue().getAsString());
    }


    public void getMinorAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_MINOR_NAME.equalsIgnoreCase(element.getKey())) revision.setMinor(element.getValue().getAsBoolean());
    }


    public void getParentIdAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_PARENTID_NAME.equalsIgnoreCase(element.getKey())) revision.setParentid(element.getValue().getAsInt());
    }


    public void getRevIdAndSetInRevisionObject(Revision revision, Map.Entry<String, JsonElement> element){
        if (Constants.REVISIONS_ELEMENT_REVID_NAME.equalsIgnoreCase(element.getKey())) revision.setRevid(element.getValue().getAsInt());
    }


    public void getPageIdAndSetInPageObject(Page page, Map.Entry<String, JsonElement> element){
        if (page.getPageid()==null) {
            if (Constants.PAGES_ELEMENT_PAGEID_NAME.equalsIgnoreCase(element.getKey()))
                page.setPageid(element.getValue().getAsInt());
        }
    }


    public void getPageTitleAndSetInPageObject(Page page, Map.Entry<String, JsonElement> element){
        if (page.getTitle()==null) {
            if (Constants.PAGES_ELEMENT_TITLE_NAME.equalsIgnoreCase(element.getKey()))
                page.setTitle(element.getValue().getAsString());
        }
    }


    public void getPageRedirectInfoAndSetInPageObject(Page page, Map.Entry<String, JsonElement> element){
        Common common = new Common();
        if (common.isEmpty(page.getRedirectpagetitle())) {
            if (Constants.PAGES_ELEMENT_TITLE_NAME.equalsIgnoreCase(element.getKey()))
                page.setTitle(element.getValue().getAsString());
        }
    }


    public String deleteFirstAndLastChar(String str){
        return str.substring(1, str.length()-1);
    }


    public JsonElement parseWikipediaResponse(String wikipediaResponse){
        JsonElement jsonElement = null;
//        try {
            jsonElement = new JsonParser().parse(
                    new InputStreamReader(
                            new ByteArrayInputStream(
                                    wikipediaResponse.getBytes(StandardCharsets.UTF_8)
                            )
                    )
            );
//        }catch (Exception e){
//            logger.error("Error parser String Wikipedia response", e.getMessage());
//        }
        return jsonElement;
    }


    public String getWikipediaApiQueryResponse(String titleArticle, String snapshot) throws MalformedURLException {
        //title: Blue rubber bleb nevus syndrome
        //snapshot: 2018-02-15
        String response = "";
        try {
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&prop=revisions|redirects&format=json&rvprop=ids|flags|timestamp|userid|user|size|comment&rvstart=" + snapshot + "T00:00:00Z&rvdir=older&rvlimit=1&redirects&titles=" + titleArticle.replace(" ", Constants.BLANK_SPACE_CODE));
            response = getResponseBody(url);
        }catch (Exception e){
            logger.error("Error to make Wikipedia API URL", e);
        }
        return response;
    }


    public String getResponseBody(URL url){
        String response = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            String line = null;
            while (null != (line = br.readLine())) {
                line = line.trim();
                if (true) {
                    response += line;
                }
            }
        } catch (Exception e){
            logger.error("Error to get data with the Wikipedia API", e);
        }
        return response;
    }


    public String getRevisionTextAndSectionsWikipediaApiQueryResponse(Integer revisionId){
        String response = "";
        try {
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=parse&format=json&formatversion=2&prop=sections|text&oldid=" + revisionId);
            response = getResponseBody(url);
        }catch (Exception e){
            logger.error("Error to make Wikipedia API URL (action parse revision data)", e);
        }
        return response;

    }


    public void getDiseasesInfoAndPopulateTheDBProcedure(){
        Common common = new Common();
        File dir = new File(Constants.STATISTICS_HISTORY_FOLDER);

        File[] directoryListing = dir.listFiles();

        int count = 1;
        if (directoryListing != null) {
            for (File diseaseFile : directoryListing) {
                try {
                    Disease jsonFileDisease = common.readDiseaseJSONFileAnalysis(diseaseFile);
                    count++;
                }//END try
                catch(Exception exception){
                    System.out.println("File " + diseaseFile.getAbsolutePath() + " is not OLE");
                }//END catch
            }//END for (File file : directoryListing) {
        }//END if (directoryListing != null) {
    }


    public void readDiseaseJSONFile(){

    }


    public void findErrorsInTheLog(){
        boolean existError = false;
        String previousLine = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    Constants.ANALYSIS_HISTORY_DIRECTORY + "diseases_impl"));
            String line = reader.readLine();
            while (line != null) {
//                System.out.println(line);
                if(line.contains(": Error")){
                    System.out.println(line);
                    System.out.println("    " + previousLine);
                }

                previousLine = line;
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
