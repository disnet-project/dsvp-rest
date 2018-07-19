package edu.ctb.upm.midas.repository.jpa;


import edu.ctb.upm.midas.model.jpa.PaperTerm;
import edu.ctb.upm.midas.model.jpa.PaperTermPK;

import java.util.List;

/**
 * Created by gerardo on 23/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className PaperTermRepository
 * @see
 */
public interface PaperTermRepository {

    PaperTerm findById(PaperTermPK paperTermPK);

    PaperTerm findByIdNative(PaperTermPK paperTermPK);

    PaperTerm findByIdNativeResultClass(PaperTermPK paperTermPK);

    List<PaperTerm> findAllQuery();

    void persist(PaperTerm paperTerm);

    int insertNative(String paperId, Integer termId);

    boolean deleteById(PaperTermPK paperTermPK);

    void delete(PaperTerm paperTerm);

    PaperTerm update(PaperTerm paperTerm);

    int updateByIdQuery(PaperTerm paperTerm);
    
}
