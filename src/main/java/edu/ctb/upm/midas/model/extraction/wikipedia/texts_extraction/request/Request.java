package edu.ctb.upm.midas.model.extraction.wikipedia.texts_extraction.request;

import edu.ctb.upm.midas.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Request {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private List<edu.ctb.upm.midas.model.WebLink> wikipediaLinks;
    private String snapshot;
    private boolean json;

    public Request() {
    }

    public Request(List<edu.ctb.upm.midas.model.WebLink> wikipediaLinks, String snapshot, boolean json) {
        this.wikipediaLinks = wikipediaLinks;
        this.snapshot = snapshot;
        this.json = json;
    }

    public List<edu.ctb.upm.midas.model.WebLink> getWikipediaLinks() {
        return wikipediaLinks;
    }

    public void setWikipediaLinks(List<edu.ctb.upm.midas.model.WebLink> wikipediaLinks) {
        this.wikipediaLinks = wikipediaLinks;
    }

    public boolean isJson() {
        return json;
    }

    public void setJson(boolean json) {
        this.json = json;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }
}
