package edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response;

import edu.ctb.upm.midas.model.WebLink;

import java.util.List;

/**
 * Created by gerardo on 09/02/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project populate-rest
 * @className DBpediaResponse
 * @see
 */
public class DBpediaResponse {

    private List<WebLink> links;
    private ConfigurationDBpediaDiseaseList config;


    public List<WebLink> getLinks() {
        return links;
    }

    public void setLinks(List<WebLink> links) {
        this.links = links;
    }

    public ConfigurationDBpediaDiseaseList getConfig() {
        return config;
    }

    public void setConfig(ConfigurationDBpediaDiseaseList config) {
        this.config = config;
    }
}
