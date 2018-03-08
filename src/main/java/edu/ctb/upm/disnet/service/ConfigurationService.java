package edu.ctb.upm.disnet.service;
import edu.ctb.upm.disnet.model.jpa.Configuration;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 04/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className ConfigurationService
 * @see
 */
public interface ConfigurationService {

    Configuration findById(String configurationId);

    Configuration findByIdQuery(String configurationId);

    Object[] findBySourceNameNative(String sourceName);

    Object[] findBySourceIdNative(String sourceId);

    Object[] findByVersionNative(Date version);

    Object[] findByToolNative(String toolName);

    Object[] findByContigurationNative(String configuration);

    Object[] findByIdNative(String configuration, int resourceId);

    List<Configuration> findAllNative();

    void persist(Configuration configuration);

    int insertNative(String configurationId, String sourceId, Date version, String tool, String configuration);

    boolean deleteById(String configurationId);

    void delete(Configuration configuration);

    Configuration update(Configuration configuration);

    int updateByIdQuery(Configuration configuration);

}
