package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.SynonymCode;
import edu.ctb.upm.midas.model.jpa.SynonymCodePK;

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
public interface SynonymCodeService {

    SynonymCode findById(SynonymCodePK synonymCodePK);

    SynonymCode findByIdNative(SynonymCodePK synonymCodePK);

    List<SynonymCode> findAll();

    void save(SynonymCode synonymCode);

    int insertNative(int synonymId, String code, int resourceId);

    boolean updateFindFull(SynonymCode synonymCode, SynonymCodePK synonymCodePK);

    boolean updateFindPartial(SynonymCode synonymCode, SynonymCodePK synonymCodePK);

    boolean deleteById(SynonymCodePK synonymCodePK);
    
}
