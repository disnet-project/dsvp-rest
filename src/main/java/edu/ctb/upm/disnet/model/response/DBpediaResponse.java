package edu.ctb.upm.disnet.model.response;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.ConfigurationDBpediaDiseaseList;
import edu.ctb.upm.disnet.model.WebLink;

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
