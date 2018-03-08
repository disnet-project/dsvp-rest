package edu.ctb.upm.disnet.repository;

import edu.ctb.upm.disnet.model.jpa.Disease;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 12/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className DiseaseRepository
 * @see
 */
public interface DiseaseRepository {

    Disease findById(String diseaseId);

    Disease findByIdQuery(String diseaseId);

    Object[] findByNameNative(String diseaseName);

    Disease findByCuiQuery(String cui);

    Disease findLastDiseaseQuery();

    Object[] findLastIdNative();

    Disease findByIdNativeMapping(String diseaseId);

    Disease findByIdNativeResultClass(String diseaseId);

    List<Disease> findAllQuery();

    List<Object[]> findAllBySourceAndVersionNative(String sourceName, Date version);

    Object[] findByIdAndSourceAndVersionNative(String diseaseId, String sourceName, Date version);

    Object[] findByCuiAndSourceAndVersionNative(String cui, String sourceName, Date version);

    void persist(Disease disease);

    int insertNative(String diseaseId, String name, String cui);

    int insertNativeHasDisease(String documentId, Date date, String diseaseId);

    boolean deleteById(String diseaseId);

    void delete(Disease disease);

    Disease update(Disease disease);

    int updateByIdQuery(Disease disease);

    int updateCuiByIdAndSourceAndVersionNative(String diseaseId, String cui, String sourceName, Date version);
    
}
