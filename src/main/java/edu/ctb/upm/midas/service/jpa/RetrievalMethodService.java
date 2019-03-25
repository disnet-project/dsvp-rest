package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.RetrievalMethod;

import java.util.List;

/**
 * Created by gerardo on 09/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className ResourceService
 * @see
 */
public interface RetrievalMethodService {

    RetrievalMethod findById(Integer retrievalMethodId);

    RetrievalMethod findByIdNative(Integer retrievalMethodId);

    RetrievalMethod findByNameNative(String name);

    RetrievalMethod findByDescriptionNative(String description);

    List<RetrievalMethod> findAllQuery();

    void save(RetrievalMethod retrievalMethod);

    int insertNative(Integer retrievalMethodId, String name, String description);

    boolean deleteById(Integer retrievalMethodId);

    void delete(RetrievalMethod retrievalMethod);

    RetrievalMethod update(RetrievalMethod retrievalMethod);

    int updateByIdQuery(RetrievalMethod retrievalMethod);
    
}
