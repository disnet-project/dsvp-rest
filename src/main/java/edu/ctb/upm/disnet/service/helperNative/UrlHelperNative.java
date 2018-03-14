package edu.ctb.upm.disnet.service.helperNative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.disnet.common.util.Common;
import edu.ctb.upm.disnet.common.util.UniqueId;
import edu.ctb.upm.disnet.model.wikipedia.document_structure.Link;
import edu.ctb.upm.disnet.model.jpa.Url;
import edu.ctb.upm.disnet.service.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 13/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className UrlHelper
 * @see
 */
@Service
public class UrlHelperNative {

    @Autowired
    private UrlService urlService;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private Common common;

    private static final Logger logger = LoggerFactory.getLogger(UrlHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;


    /**
     * @param link
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    public String getUrl(Link link, String id) throws JsonProcessingException {
        String urlId = uniqueId.generateUrl( id, link.getId() );
        if (urlService.insertNative( urlId, link.getUrl() ) > 0)
            return urlId;
        else
            return "";
    }


    /**
     * @param links
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    public List<String> getUrl(List<Link> links, String id) throws JsonProcessingException {
        List<String> urls = new ArrayList<>();

        for (Link link: links) {
            String urlId = uniqueId.generateUrl( id, link.getId() );
            urlService.insertNative( urlId, link.getUrl() );
            urls.add( urlId );
        }

        return urls;
    }

    /**
     * @param link
     * @param id
     * @return
     * @throws JsonProcessingException
     */
    public String getSimpleUrlId(Link link, int id) throws JsonProcessingException {
        String urlId = uniqueId.generateDocumentUrlId( id );
        Url url = urlService.findById(urlId);
        if (url!=null){
            String newUrl = urlId + "_" + uniqueId.generate(6);
            if (urlService.insertNative( newUrl, link.getUrl() ) > 0)
                return newUrl;
            else
                return "";
        }else{
            if (urlService.insertNative( urlId, link.getUrl() ) > 0)
                return urlId;
            else
                return "";
        }

    }


    /**
     * @param url
     * @return
     */
    public Url findUrl(String url){
        Url oUrl = urlService.findByName(url);
        if (oUrl != null) return oUrl;
        else return null;
    }


}
