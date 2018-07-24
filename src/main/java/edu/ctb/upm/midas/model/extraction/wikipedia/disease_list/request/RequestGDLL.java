package edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request;

import edu.ctb.upm.midas.constants.Constants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RequestGDLL extends RequestFather {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private String source;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private String album;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, max = 10, message = Constants.ERR_EMPTY_PARAMETER)
    private String snapshot;



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    @Override
    public String toString() {
        return "RequestGDLL{" +
                "source='" + source + '\'' +
                ", album='" + album + '\'' +
                ", snapshot='" + snapshot + '\'' +
                '}';
    }
}
