package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ctb.upm.disnet.model.common.document_structure.Source;
import edu.ctb.upm.disnet.model.common.document_structure.code.Resource;

import java.util.HashMap;
import java.util.List;

public class Response {

    private String responseCode;
    private String responseMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Source> sources;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HashMap<String, Resource> resourceHashMap;
    private String start_time;
    private String end_time;


    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public HashMap<String, Resource> getResourceHashMap() {
        return resourceHashMap;
    }

    public void setResourceHashMap(HashMap<String, Resource> resourceHashMap) {
        this.resourceHashMap = resourceHashMap;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
