package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.api_response.impl;


import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.api_response.DiseaseAlbumResourceService;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.client.DiseaseAlbumClient;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestAlbum;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestFather;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestGDLL;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseGDLL;
import edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseLA;
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
public class DiseaseAlbumResourceServiceImpl implements DiseaseAlbumResourceService {

    @Autowired
    private DiseaseAlbumClient diseaseAlbumClient;

    @Override
    public ResponseLA getDiseaseAlbum(RequestFather request) {
        return diseaseAlbumClient.getDiseaseAlbum( request );
    }

    @Override
    public ResponseGDLL getDiseaseLinkList(RequestGDLL request) {
        return diseaseAlbumClient.getDiseaseLinkList(request);
    }

    @Override
    public ResponseLA getSpecificAlbum(RequestAlbum request) {
        return diseaseAlbumClient.getSpecifictDiseaseAlbum(request);
    }


}
