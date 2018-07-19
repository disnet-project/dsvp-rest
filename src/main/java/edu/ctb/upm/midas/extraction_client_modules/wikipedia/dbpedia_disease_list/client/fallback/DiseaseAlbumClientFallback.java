package edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.client.fallback;


import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.client.DiseaseAlbumClient;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestAlbum;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestFather;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseLA;
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
public class DiseaseAlbumClientFallback implements DiseaseAlbumClient {

    @Override
    public ResponseLA getDiseaseAlbum(RequestFather request) {
        return new ResponseLA();
    }

    @Override
    public ResponseGDLL getDiseaseLinkList(RequestGDLL request) {
        return new ResponseGDLL();
    }

    @Override
    public ResponseLA getSpecifictDiseaseAlbum(RequestAlbum request){return new ResponseLA();}
}
