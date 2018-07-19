package edu.ctb.upm.midas.service.jpa;

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
 * @className HasSectionService
 * @see
 */
public interface DocumentSetService {

    DocumentSet findById(DocumentSetPK documentSetPK);

    DocumentSet findByIdNative(DocumentSetPK documentSetPK);

    List<DocumentSet> findAll();

    void save(DocumentSet documentSet);

    int insertNative(String documentId, Date version, String paperId);

    boolean updateFindFull(DocumentSet documentSet, DocumentSetPK diseaseSynonymPK);

    boolean updateFindPartial(DocumentSet documentSet, DocumentSetPK diseaseSynonymPK);

    boolean deleteById(DocumentSetPK diseaseSynonymPK);
    
}
