package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.Resource;

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
public interface ResourceService {

    Resource findById(Integer resourceId);

    Resource findByName(String sourceName);

    int findIdByNameQuery(String resourceName);

    List<Resource> findAll();

    void save(Resource resource);

    int insertNative(String name);

    boolean updateFindFull(Resource resource);

    boolean updateFindPartial(Resource resource);

    boolean deleteById(Integer resourceId);
    
}
