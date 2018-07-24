package edu.ctb.upm.midas.client_modules.filter.tvp.client.fallback;

import edu.ctb.upm.midas.client_modules.filter.tvp.client.TvpClient;
import edu.ctb.upm.midas.model.filter.tvp.request.Request;
import edu.ctb.upm.midas.model.filter.tvp.response.Response;
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
public class TvpClientFallback implements TvpClient {


    public Response getValidateSymptoms(Request request)  {
        //System.out.println("SUCEDE ALGO?");
        return new Response();
    }

}

