package edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.client.fallback;


import edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.client.WikipediaTextsExtractionClient;
import edu.ctb.upm.midas.model.extraction.wikipedia.texts_extraction.request.Request;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import org.springframework.stereotype.Component;

/**
 * Created by gerardo on 17/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className TvpClientFallback
 * @see
 */
@Component
public class WikipediaTextsExtractionClientFallback implements WikipediaTextsExtractionClient {

    @Override
    public Response getWikipediaTexts(Request request) {
        return new Response();
    }

    @Override
    public Response getWikipediaResources(Request request) {
        return new Response();
    }

    @Override
    public Response getWikipediaTextsByJSON(RequestJSON request) {
        return new Response();
    }

    @Override
    public Response getWikipediaResourcesByJSON(RequestJSON request) {
        return new Response();
    }
}

