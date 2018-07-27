package edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.api_response.impl;

import edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.api_response.MayoClinicTextsExtractionService;
import edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.client.MayoClinicTextsExtractionClient;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import edu.ctb.upm.midas.model.extraction.common.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 12/02/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dgsvp-rest
 * @className MayoClinicTextsExtractionServiceImpl
 * @see
 */
@Service
public class MayoClinicTextsExtractionServiceImpl implements MayoClinicTextsExtractionService {

    @Autowired
    private MayoClinicTextsExtractionClient mcteServiceClient;

    @Override
    public Response getTexts(Request request) {
        return mcteServiceClient.getTexts(request);
    }

    @Override
    public Response getResources(Request request) {
        return mcteServiceClient.getResources(request);
    }

    @Override
    public Response getTextsByJSON(RequestJSON request) {
        return mcteServiceClient.getTextsByJSON(request);
    }

    @Override
    public Response getResourcesByJSON(RequestJSON request) {
        return mcteServiceClient.getResourcesByJSON(request);
    }
}
