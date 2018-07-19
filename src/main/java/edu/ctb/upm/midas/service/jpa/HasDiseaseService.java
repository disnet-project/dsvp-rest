package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.HasDisease;
import edu.ctb.upm.midas.model.jpa.HasDiseasePK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 23/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className HasDiseaseService
 * @see
 */
public interface HasDiseaseService {

    HasDisease findById(HasDiseasePK hasDiseasePK);

    List<HasDisease> findAll();

    void save(HasDisease hasDisease);

    int insertNative(String documentId, Date date, String diseaseId);

    boolean updateFindFull(HasDisease hasDisease, HasDiseasePK hasDiseasePK);

    boolean updateFindPartial(HasDisease hasDisease, HasDiseasePK hasDiseasePK);

    boolean deleteById(HasDiseasePK hasDiseasePK);
    
}
