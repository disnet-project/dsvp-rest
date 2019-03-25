package edu.ctb.upm.midas.repository.jpa.impl;
import edu.ctb.upm.midas.model.jpa.RetrievalMethod;
import edu.ctb.upm.midas.repository.jpa.AbstractDao;
import edu.ctb.upm.midas.repository.jpa.RetrievalMethodRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className RetrievalMethodRepositoryImpl
 * @see
 */
@Repository("RetrievalMethodRepositoryDao")
public class RetrievalMethodRepositoryImpl extends AbstractDao<Integer, RetrievalMethod> implements RetrievalMethodRepository {

    @SuppressWarnings("unchecked")
    @Override
    public RetrievalMethod findById(Integer retrievalMethodId) {
        RetrievalMethod retrievalMethod = getByKey(retrievalMethodId);
        return retrievalMethod;
    }

    @SuppressWarnings("unchecked")
    @Override
    public RetrievalMethod findByIdNative(Integer retrievalMethodId) {
        RetrievalMethod retrievalMethod = null;
        List<RetrievalMethod> retrievalMethodList = (List<RetrievalMethod>) getEntityManager()
                .createNamedQuery("RetrievalMethod.findByIdNative")
                .setParameter("retrievalMethodId", retrievalMethodId)
//                .setParameter("description", description)
                .getResultList();
        if (CollectionUtils.isNotEmpty(retrievalMethodList))
            retrievalMethod = retrievalMethodList.get(0);
        return retrievalMethod;
    }

    @SuppressWarnings("unchecked")
    @Override
    public RetrievalMethod findByNameNative(String name) {
        RetrievalMethod retrievalMethod = null;
        List<RetrievalMethod> retrievalMethodList = (List<RetrievalMethod>) getEntityManager()
                .createNamedQuery("RetrievalMethod.findByNameNative")
                .setParameter("name", "%" + name + "%")
//                .setParameter("name", name)
                .getResultList();
        if (CollectionUtils.isNotEmpty(retrievalMethodList))
            retrievalMethod = retrievalMethodList.get(0);
        return retrievalMethod;
    }

    @SuppressWarnings("unchecked")
    @Override
    public RetrievalMethod findByDescriptionNative(String description) {
        RetrievalMethod retrievalMethod = null;
        List<RetrievalMethod> retrievalMethodList = (List<RetrievalMethod>) getEntityManager()
                .createNamedQuery("RetrievalMethod.findByDescriptionNative")
                .setParameter("description", "%" + description + "%")
//                .setParameter("description", description)
                .getResultList();
        if (CollectionUtils.isNotEmpty(retrievalMethodList))
            retrievalMethod = retrievalMethodList.get(0);
        return retrievalMethod;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RetrievalMethod> findAllQuery() {
        return (List<RetrievalMethod>) getEntityManager()
                .createNamedQuery("RetrievalMethod.findAll")
                .setMaxResults(0)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void persist(RetrievalMethod retrievalMethod) {
        super.persist(retrievalMethod);
    }

    @Override
    public int insertNative(Integer retrievalMethodId, String name, String description) {
        return getEntityManager()
                .createNamedQuery("RetrievalMethod.insertNative")
                .setParameter("retrievalMethodId", retrievalMethodId)
                .setParameter("name", name)
                .setParameter("description", description)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(Integer retrievalMethodId) {
        return false;
    }

    @Override
    public void delete(RetrievalMethod retrievalMethod) {

    }

    @Override
    public RetrievalMethod update(RetrievalMethod retrievalMethod) {
        return null;
    }

    @Override
    public int updateByIdQuery(RetrievalMethod retrievalMethod) {
        return 0;
    }
}
