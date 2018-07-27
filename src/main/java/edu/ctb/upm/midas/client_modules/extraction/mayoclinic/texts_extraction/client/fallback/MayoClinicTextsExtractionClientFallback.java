package edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.client.fallback;


import edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.client.MayoClinicTextsExtractionClient;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import edu.ctb.upm.midas.model.extraction.common.request.Request;
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
public class MayoClinicTextsExtractionClientFallback implements MayoClinicTextsExtractionClient {

    @Override
    public Response getTexts(Request request) {
        return new Response();
    }

    @Override
    public Response getResources(Request request) {
        return new Response();
    }

    @Override
    public Response getTextsByJSON(RequestJSON request) {
        return new Response();
    }

    @Override
    public Response getResourcesByJSON(RequestJSON request) {
        return new Response();
    }
}

