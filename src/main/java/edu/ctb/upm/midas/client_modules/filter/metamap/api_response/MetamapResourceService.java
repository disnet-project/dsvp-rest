package edu.ctb.upm.midas.client_modules.filter.metamap.api_response;

import edu.ctb.upm.midas.model.filter.metamap.request.Request;
import edu.ctb.upm.midas.model.filter.metamap.response.Response;

/**
 * Created by gerardo on 31/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className MetamapResourceService
 * @see
 */
public interface MetamapResourceService {

    Response filterDiseaseName(Request request);

    Response filterTexts(Request request);
}
