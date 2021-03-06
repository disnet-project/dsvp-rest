package edu.ctb.upm.midas.model.common.document_structure.text;

import edu.ctb.upm.midas.model.common.document_structure.Link;

import java.util.List;

/**
 * Created by gerardo on 4/4/17.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationWikipedia
 * @className List_
 * @see
 */
public class List_ extends Text {

    public List_() {
    }

    public List_(int id, int textOrder, String title, List<Link> urlList, List<String> bulletList) {
        super(id, title, textOrder, urlList);
        this.bulletList = bulletList;

    }

    private List<String> bulletList;//Texto al final de cuentas


    public List<String> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<String> bulletList) {
        this.bulletList = bulletList;
    }
}
