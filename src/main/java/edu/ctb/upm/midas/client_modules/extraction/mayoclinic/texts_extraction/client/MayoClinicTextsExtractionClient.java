package edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.client;


import edu.ctb.upm.midas.client_modules.extraction.mayoclinic.texts_extraction.client.fallback.MayoClinicTextsExtractionClientFallback;
import edu.ctb.upm.midas.configuration.FeignConfiguration;
import edu.ctb.upm.midas.model.extraction.common.request.RequestJSON;
import edu.ctb.upm.midas.model.extraction.common.response.Response;
import edu.ctb.upm.midas.model.extraction.common.request.Request;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gerardo on 17/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dgsvp
 * @className WikipediaTextsExtractionClient
 * @see
 */

@FeignClient(name = "${my.service.client.mcte.name}",
        url = "${my.service.client.mcte.url}",
        fallback = MayoClinicTextsExtractionClientFallback.class,
        configuration = FeignConfiguration.class)
public interface MayoClinicTextsExtractionClient {

    @RequestMapping(value = "${my.service.client.mcte.texts.path}", method = RequestMethod.POST)
    Response getTexts(@RequestBody Request request);

    @RequestMapping(value = "${my.service.client.mcte.resources.path}", method = RequestMethod.POST)
    Response getResources(@RequestBody Request request);

    @RequestMapping(value = "${my.service.client.mcte.texts.json.path}", method = RequestMethod.POST)
    Response getTextsByJSON(@RequestBody RequestJSON request);

    @RequestMapping(value = "${my.service.client.mcte.resources.json.path}", method = RequestMethod.POST)
    Response getResourcesByJSON(@RequestBody RequestJSON request);

}
