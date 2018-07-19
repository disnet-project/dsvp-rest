package edu.ctb.upm.midas.model.pumed;
import java.util.List;

/**
 * Created by gerardo on 16/03/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className Doc
 * @see
 */
public class Doc extends edu.ctb.upm.midas.model.common.document_structure.Doc {

    private Integer paperCount;
    private List<PubMedDoc> paperList;



    public Integer getPaperCount() {
        return paperCount;
    }

    public void setPaperCount(Integer paperCount) {
        this.paperCount = paperCount;
    }

    public List<PubMedDoc> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<PubMedDoc> paperList) {
        this.paperList = paperList;
    }
}
