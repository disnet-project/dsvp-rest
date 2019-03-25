package edu.ctb.upm.midas.service.jpa.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.midas.model.jpa.RetrievalMethod;
import edu.ctb.upm.midas.repository.jpa.RetrievalMethodRepository;
import edu.ctb.upm.midas.service.jpa.RetrievalMethodService;
import edu.ctb.upm.midas.service.jpa.helperNative.TextHelperNative;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className RetrievalMethodServiceImpl
 * @see
 */
@Service
public class RetrievalMethodServiceImpl implements RetrievalMethodService {

    private static final Logger logger = LoggerFactory.getLogger(TextHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private RetrievalMethodRepository daoRetrievalMethod;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public RetrievalMethod findById(Integer retrievalMethodId) {
        return daoRetrievalMethod.findById(retrievalMethodId);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public RetrievalMethod findByIdNative(Integer retrievalMethodId) {
        return daoRetrievalMethod.findByIdNative(retrievalMethodId);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public RetrievalMethod findByNameNative(String name) {
        return daoRetrievalMethod.findByNameNative(name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public RetrievalMethod findByDescriptionNative(String description) {
        return daoRetrievalMethod.findByDescriptionNative(description);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<RetrievalMethod> findAllQuery() {
        return daoRetrievalMethod.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(RetrievalMethod retrievalMethod) {
        daoRetrievalMethod.persist(retrievalMethod);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int insertNative(Integer retrievalMethodId, String name, String description) {
        return daoRetrievalMethod.insertNative(retrievalMethodId, name, description);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(Integer retrievalMethodId) {
        return false;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(RetrievalMethod retrievalMethod) {

    }

    @Transactional(propagation= Propagation.REQUIRED)
    public RetrievalMethod update(RetrievalMethod retrievalMethod) {
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int updateByIdQuery(RetrievalMethod retrievalMethod) {
        return 0;
    }
}
