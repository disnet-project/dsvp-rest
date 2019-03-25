package edu.ctb.upm.midas.repository.jpa;


import edu.ctb.upm.midas.model.jpa.RetrievalMethod;

import java.util.List;

/**
 * Created by gerardo on 28/04/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className SourceRepository
 * @see
 */
public interface RetrievalMethodRepository {

    RetrievalMethod findById(Integer retrievalMethodId);

    RetrievalMethod findByIdNative(Integer retrievalMethodId);

    RetrievalMethod findByNameNative(String name);

    RetrievalMethod findByDescriptionNative(String description);

    List<RetrievalMethod> findAllQuery();

    void persist(RetrievalMethod retrievalMethod);

    int insertNative(Integer retrievalMethodId, String name, String description);

    boolean deleteById(Integer retrievalMethodId);

    void delete(RetrievalMethod retrievalMethod);

    RetrievalMethod update(RetrievalMethod retrievalMethod);

    int updateByIdQuery(RetrievalMethod retrievalMethod);

}
