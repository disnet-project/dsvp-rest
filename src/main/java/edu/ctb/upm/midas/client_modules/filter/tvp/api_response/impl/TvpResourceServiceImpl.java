package edu.ctb.upm.midas.client_modules.filter.tvp.api_response.impl;

import edu.ctb.upm.midas.client_modules.filter.tvp.api_response.TvpResourceService;
import edu.ctb.upm.midas.client_modules.filter.tvp.client.TvpClient;
import edu.ctb.upm.midas.model.filter.tvp.request.Request;
import edu.ctb.upm.midas.model.filter.tvp.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 17/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className TvpResourceServiceImpl
 * @see
 */
@Service
public class TvpResourceServiceImpl implements TvpResourceService {

    @Autowired
//    @Lazy
    private TvpClient tvpClient;

    //@HystrixCommand(groupKey = "tp-notification-service", fallbackMethod = "notificationsAreDown")
    //@HystrixCommand(fallbackMethod = "retrieveFallback")
    public Response getValidateSymptoms(Request request) {
        return tvpClient.getValidateSymptoms( request );
    }

    /*public Response notificationsAreDown(Request request) {
        return tvpClient.getValidateSymptoms( request );
    }*/

    /*public Response retrieveFallback(Request request, Throwable ex){
        assert "filterTexts command failed".equals(ex.getMessage());
        throw new RuntimeException("retrieveFallback failedddddd" + ex.getMessage());
    }*/
}
