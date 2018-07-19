package edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.api_response;


import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestAlbum;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestFather;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.request.RequestGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseGDLL;
import edu.ctb.upm.midas.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response.ResponseLA;

/**
 * Created by gerardo on 02/11/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className DiseaseAlbumResourceService
 * @see
 */
public interface DiseaseAlbumResourceService {

    ResponseLA getDiseaseAlbum(RequestFather request);

    ResponseGDLL getDiseaseLinkList(RequestGDLL request);

    ResponseLA getSpecificAlbum(RequestAlbum request);

}
