package edu.ctb.upm.midas.client_modules.extraction.wikipedia.disease_list.api_response;


import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestAlbum;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestFather;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.request.RequestGDLL;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response.ResponseGDLL;
import edu.ctb.upm.midas.model.extraction.wikipedia.disease_list.response.ResponseLA;

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
