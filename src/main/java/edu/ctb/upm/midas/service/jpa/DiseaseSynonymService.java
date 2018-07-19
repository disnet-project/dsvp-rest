package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.DiseaseSynonym;
import edu.ctb.upm.midas.model.jpa.DiseaseSynonymPK;

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
public interface DiseaseSynonymService {

    DiseaseSynonym findById(DiseaseSynonymPK diseaseSynonymPK);

    DiseaseSynonym findByIdNative(DiseaseSynonymPK diseaseSynonymPK);

    List<DiseaseSynonym> findAll();

    void save(DiseaseSynonym diseaseSynonym);

    int insertNative(String diseaseId, int synonymId);

    boolean updateFindFull(DiseaseSynonym hasSection, DiseaseSynonymPK diseaseSynonymPK);

    boolean updateFindPartial(DiseaseSynonym hasSection, DiseaseSynonymPK diseaseSynonymPK);

    boolean deleteById(DiseaseSynonymPK diseaseSynonymPK);
    
}
