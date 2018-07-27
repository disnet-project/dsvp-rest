package edu.ctb.upm.midas.model.common.document_structure.text;


import edu.ctb.upm.midas.model.common.document_structure.Link;

import java.util.List;

/**
 * Created by gerardo on 3/4/17.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationWikipedia
 * @className Text
 * @see
 */
//Un Text siempre será un <p>, <ul><ol>
public class Text {

    private int id;
    private String title;//EL nombre si tiene, será un <h3>
    private int textOrder;

    private List<Link> urlList;

    //chapuza en lo que encuentro una mejor solución
    private String text;
    private List<String> bulletList;

    public Text() {
    }

    public Text(int id, String title, int textOrder) {
        this.id = id;
        this.title = title;
        this.textOrder = textOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTextOrder() {
        return textOrder;
    }

    public void setTextOrder(int textOrder) {
        this.textOrder = textOrder;
    }

    public List<Link> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<Link> urlList) {
        this.urlList = urlList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<String> bulletList) {
        this.bulletList = bulletList;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", textOrder=" + textOrder +
                ", urlList=" + urlList +
                ", text='" + text + '\'' +
                ", bulletList=" + bulletList +
                '}';
    }
}
