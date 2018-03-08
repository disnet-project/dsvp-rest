package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.request;

import edu.ctb.upm.disnet.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Request {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private List<edu.ctb.upm.disnet.model.WebLink> wikipediaLinks;

    public List<edu.ctb.upm.disnet.model.WebLink> getWikipediaLinks() {
        return wikipediaLinks;
    }

    public void setWikipediaLinks(List<edu.ctb.upm.disnet.model.WebLink> wikipediaLinks) {
        this.wikipediaLinks = wikipediaLinks;
    }
}
