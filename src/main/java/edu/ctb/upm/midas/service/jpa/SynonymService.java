package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.Synonym;

import java.util.List;

/**
 * Created by gerardo on 14/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className SynonymService
 * @see
 */
public interface SynonymService {

    Synonym findById(Integer synonymId);

    Synonym findByIdNative(Integer synonymId);

    Synonym findByNameQuery(String name);

    int findIdByNameQuery(String name);
    
    List<Synonym> findAll();

    void save(Synonym synonym);

    int insertNative(String name);

    boolean updateFindFull(Synonym synonym);

    boolean updateFindPartial(Synonym synonym);

    boolean deleteById(Integer synonymId);

}
