package edu.ctb.upm.midas.client_modules.filter.metamap.client.fallback;

import edu.ctb.upm.midas.client_modules.filter.metamap.client.MetamapClient;
import edu.ctb.upm.midas.model.filter.metamap.request.Request;
import edu.ctb.upm.midas.model.filter.metamap.response.Response;
import org.springframework.stereotype.Component;

/**
 * Created by gerardo on 17/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className MetamapClientFallback
 * @see
 */
@Component
public class MetamapClientFallback implements MetamapClient {

    @Override
    public Response filterTexts(Request request) {
        return new Response();
    }

}

