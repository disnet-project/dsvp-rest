package edu.ctb.upm.midas.service.jpa.impl;
import edu.ctb.upm.midas.model.jpa.DiseaseSynonym;
import edu.ctb.upm.midas.model.jpa.DiseaseSynonymPK;
import edu.ctb.upm.midas.repository.jpa.DiseaseSynonymRepository;
import edu.ctb.upm.midas.service.jpa.DiseaseSynonymService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gerardo on 11/04/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className DiseaseSynonymServiceImpl
 * @see
 */
@Service("diseaseSynonymService")
public class DiseaseSynonymServiceImpl implements DiseaseSynonymService {

    @Autowired
    private DiseaseSynonymRepository daoDiseaseSynonym;

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public DiseaseSynonym findById(DiseaseSynonymPK diseaseSynonymPK) {
        DiseaseSynonym diseaseSynonym = daoDiseaseSynonym.findById(diseaseSynonymPK);
        return diseaseSynonym;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public DiseaseSynonym findByIdNative(DiseaseSynonymPK diseaseSynonymPK) {
        return daoDiseaseSynonym.findByIdNative(diseaseSynonymPK);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public List<DiseaseSynonym> findAll() {
        List<DiseaseSynonym> daoDiseaseSynonymAllQuery = daoDiseaseSynonym.findAllQuery();
        return daoDiseaseSynonymAllQuery;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public void save(DiseaseSynonym diseaseSynonym) {
        daoDiseaseSynonym.persist(diseaseSynonym);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int insertNative(String diseaseId, int synonymId) {
        return daoDiseaseSynonym.insertNative(diseaseId, synonymId);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateFindFull(DiseaseSynonym hasSection, DiseaseSynonymPK diseaseSynonymPK) {
        return false;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean updateFindPartial(DiseaseSynonym hasSection, DiseaseSynonymPK diseaseSynonymPK) {
        return false;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public boolean deleteById(DiseaseSynonymPK diseaseSynonymPK) {
        return false;
    }
}
