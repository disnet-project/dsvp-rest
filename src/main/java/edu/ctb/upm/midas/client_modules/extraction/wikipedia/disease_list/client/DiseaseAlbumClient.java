package edu.ctb.upm.midas.client_modules.extraction.wikipedia.disease_list.client;


import edu.ctb.upm.midas.configuration.FeignConfiguration;
import edu.ctb.upm.midas.client_modules.extraction.wikipedia.disease_list.client.fallback.DiseaseAlbumClientFallback;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestAlbum;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestFather;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestGDLL;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response.ResponseGDLL;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response.ResponseLA;
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
 * @className TvpClient
 * @see
 */

@FeignClient(name = "${my.service.client.disease_album.name}",
        url = "${my.service.client.disease_album.url}",
        fallback = DiseaseAlbumClientFallback.class,
        configuration = FeignConfiguration.class)
public interface DiseaseAlbumClient {

    //@Headers("token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJncmFyZG9sYWdhckBob3RtYWlsLmNvbSIsImF1ZCI6IndlYiIsIm5hbWUiOiJHZXJhcmRvIExhZ3VuZXMiLCJ1c2VyIjp0cnVlLCJpYXQiOjE1MDY0MzIwNjQsInNlY3JldF9jbGFpbSI6IlBlcnRlciBQYXJrZXIifQ.mC-hTx9a6vRX8-2QlP1W4vjnBiVk2D9ySnhasz7az12gZ_wx7u4gw20V1VC41zYydGBbX_A7MVJ0uGmzWwIRWQ")
    @RequestMapping(value = "${my.service.client.disease_album.path.last}", method = RequestMethod.POST)
    ResponseLA getDiseaseAlbum(@RequestBody RequestFather request);

    @RequestMapping(value = "${my.service.client.disease_album.path.get}", method = RequestMethod.POST)
    ResponseGDLL getDiseaseLinkList(@RequestBody RequestGDLL request);

    @RequestMapping(value = "${my.service.client.disease_album.path.get.one}", method = RequestMethod.POST)
    ResponseLA getSpecifictDiseaseAlbum(@RequestBody RequestAlbum request);

}
