package edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.api_response.impl;
import edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.api_response.WikipediaTextsExtractionService;
import edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.client.WikipediaTextsExtractionClient;
import edu.ctb.upm.midas.model.extraction.wikipedia.texts_extraction.request.Request;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 12/02/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dgsvp-rest
 * @className WikipediaTextsExtractionServiceImpl
 * @see
 */
@Service
public class WikipediaTextsExtractionServiceImpl implements WikipediaTextsExtractionService {

    @Autowired
    private WikipediaTextsExtractionClient wteServiceClient;

    @Override
    public Response getTexts(Request request) {
        return wteServiceClient.getWikipediaTexts(request);
    }

    @Override
    public Response getResources(Request request) {
        return wteServiceClient.getWikipediaResources(request);
    }

    @Override
    public Response getWikipediaTextsByJSON(RequestJSON request) {
        return wteServiceClient.getWikipediaTextsByJSON(request);
    }

    @Override
    public Response getWikipediaResourcesByJSON(RequestJSON request) {
        return wteServiceClient.getWikipediaResourcesByJSON(request);
    }
}
