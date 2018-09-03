package edu.ctb.upm.midas.model.common.document_structure.text;

import edu.ctb.upm.midas.model.common.document_structure.Link;

import java.util.List;

/**
 * Created by gerardo on 30/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project eidw
 * @className Table
 * @see
 */
public class Table extends Text {


    private List<String> trList;

    public Table() {
    }

    public Table(int id, int textOrder, String title, List<Link> urlList, List<String> trList) {
        super(id, title, textOrder, urlList);
        this.trList = trList;
    }




    public List<String> getTrList() {
        return trList;
    }

    public void setTrList(List<String> trList) {
        this.trList = trList;
    }
}
