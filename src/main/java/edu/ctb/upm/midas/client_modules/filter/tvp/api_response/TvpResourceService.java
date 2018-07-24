package edu.ctb.upm.midas.client_modules.filter.tvp.api_response;

import edu.ctb.upm.midas.model.filter.tvp.request.Request;
import edu.ctb.upm.midas.model.filter.tvp.response.Response;

/**
 * Created by gerardo on 08/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className TvpResourceService
 * @see
 */
public interface TvpResourceService {

    Response getValidateSymptoms(Request request);

}
