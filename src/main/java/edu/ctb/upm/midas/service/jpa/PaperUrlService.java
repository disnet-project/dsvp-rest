package edu.ctb.upm.midas.service.jpa;

import edu.ctb.upm.midas.model.jpa.PaperUrl;
import edu.ctb.upm.midas.model.jpa.PaperUrlPK;

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
public interface PaperUrlService {

    PaperUrl findById(PaperUrlPK paperUrlPK);

    PaperUrl findByIdNative(PaperUrlPK paperUrlPK);

    List<PaperUrl> findAll();

    void save(PaperUrl paperUrl);

    int insertNative(String paperId, String urlId);

    boolean updateFindFull(PaperUrl paperUrl, PaperUrlPK paperUrlPK);

    boolean updateFindPartial(PaperUrl paperUrl, PaperUrlPK paperUrlPK);

    boolean deleteById(PaperUrlPK paperUrlPK);
    
}
