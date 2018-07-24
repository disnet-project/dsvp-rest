package edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response;

import java.util.Date;

/**
 * Created by gerardo on 02/11/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className Album
 * @see
 */
public class Album {

    private String albumId;
    private Date date;
    private Integer numberDiseases;


    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumberDiseases() {
        return numberDiseases;
    }

    public void setNumberDiseases(Integer numberDiseases) {
        this.numberDiseases = numberDiseases;
    }

}
