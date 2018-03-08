package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.client.fallback;


import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.client.WikipediaTextsExtractionClient;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.request.Request;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.response.Response;
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
}

