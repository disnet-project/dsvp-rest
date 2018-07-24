package edu.ctb.upm.midas.client_modules.extraction.wikipedia.texts_extraction.api_response;

import edu.ctb.upm.midas.model.extraction.wikipedia.texts_extraction.request.Request;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;

/**
 * Created by gerardo on 12/02/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dgsvp-rest
 * @className WikipediaTextsExtractionService
 * @see
 */
public interface WikipediaTextsExtractionService {

    Response getTexts(Request request);

    Response getResources(Request request);

    Response getWikipediaTextsByJSON( RequestJSON request);

    Response getWikipediaResourcesByJSON( RequestJSON request);
}
