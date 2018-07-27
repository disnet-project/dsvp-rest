package edu.ctb.upm.midas.model.extraction.common.request;


import edu.ctb.upm.midas.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Request {

    @Valid
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 10, max = 10, message = Constants.ERR_EMPTY_PARAMETER)
    private String snapshot;
    private boolean json;
    private String extractionType;

    public Request() {
    }

    public Request(@Valid @NotNull(message = Constants.ERR_NO_PARAMETER) @Size(min = 10, max = 10, message = Constants.ERR_EMPTY_PARAMETER) String snapshot, boolean json) {
        this.snapshot = snapshot;
        this.json = json;
    }

    public Request(@Valid @NotNull(message = Constants.ERR_NO_PARAMETER) @Size(min = 10, max = 10, message = Constants.ERR_EMPTY_PARAMETER) String snapshot, boolean json, String extractionType) {
        this.snapshot = snapshot;
        this.json = json;
        this.extractionType = extractionType;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public boolean isJson() {
        return json;
    }

    public void setJson(boolean json) {
        this.json = json;
    }

    public String getExtractionType() {
        return extractionType;
    }

    public void setExtractionType(String extractionType) {
        this.extractionType = extractionType;
    }
}
