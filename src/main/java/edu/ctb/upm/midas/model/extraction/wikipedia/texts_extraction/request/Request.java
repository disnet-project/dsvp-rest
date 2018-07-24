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
    private boolean json;

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
}
