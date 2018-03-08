package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.client;


import edu.ctb.upm.disnet.configuration.FeignConfiguration;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.client.fallback.WikipediaTextsExtractionClientFallback;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.request.Request;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.texts_extraction.model.response.Response;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gerardo on 17/08/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className CustomizeWikipediaTextsExtractionClient
 * @see
 */
@FeignClient(name = "${my.service.client.wte.name}",
        url = "${my.service.client.wte.url}",
        fallback = WikipediaTextsExtractionClientFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomizeWikipediaTextsExtractionClient {

    @RequestMapping(value = "${my.service.client.wte.texts.path}", method = RequestMethod.POST)
    Response getWikipediaTexts(@RequestBody Request request);

    @RequestMapping(value = "${my.service.client.wte.sources.path}", method = RequestMethod.POST)
    Response getWikipediaResources(@RequestBody Request request);

}
