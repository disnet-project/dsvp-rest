package edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.client;


import edu.ctb.upm.midas.configuration.FeignConfiguration;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.client.fallback.DiseaseAlbumClientFallback;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestAlbum;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestFather;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseLA;
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
 * @className CustomizeTvpClient
 * @see
 */
@FeignClient(name = "${my.service.client.disease_album.name}",
        url = "${my.service.client.disease_album.url}",
        fallback = DiseaseAlbumClientFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomizeDiseaseAlbumClient {

    @RequestMapping(value = "${my.service.client.disease_album.path.last}", method = RequestMethod.POST)
    ResponseLA getDiseaseAlbum(@RequestBody RequestFather request);

    @RequestMapping(value = "${my.service.client.disease_album.path.get}", method = RequestMethod.POST)
    ResponseGDLL getDiseaseLinkList(@RequestBody RequestGDLL response);

    @RequestMapping(value = "${my.service.client.disease_album.path.get.one}", method = RequestMethod.POST)
    ResponseLA getSpecifictDiseaseAlbum(@RequestBody RequestAlbum request);

}
