package edu.ctb.upm.midas.repository.jpa;


import edu.ctb.upm.midas.model.jpa.DocumentSet;
import edu.ctb.upm.midas.model.jpa.DocumentSetPK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 23/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className HasSectionRepository
 * @see
 */
public interface DocumentSetRepository {

    DocumentSet findById(DocumentSetPK documentSetPK);

    DocumentSet findByIdNative(DocumentSetPK documentSetPK);

    DocumentSet findByIdNativeResultClass(DocumentSetPK documentSetPK);

    List<DocumentSet> findAllQuery();

    void persist(DocumentSet documentSet);

    int insertNative(String documentId, Date version, String paperId);

    boolean deleteById(DocumentSetPK documentSetPK);

    void delete(DocumentSet documentSet);

    DocumentSet update(DocumentSet documentSet);

    int updateByIdQuery(DocumentSet documentSet);
    
}
