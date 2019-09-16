package edu.ctb.upm.midas.model.wikipediaApi;

import java.util.List;

public class Page {

    private Integer pageid;
    private String title;
    private List<Revision> revisions;


    public Page() {
    }

    public Page(Integer pageid, String title) {
        this.pageid = pageid;
        this.title = title;
    }

    public Page(Integer pageid, String title, List<Revision> revisions) {
        this.pageid = pageid;
        this.title = title;
        this.revisions = revisions;
    }



    public Integer getPageid() {
        return pageid;
    }

    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Revision> getRevisions() {
        return revisions;
    }

    public void setRevisions(List<Revision> revisions) {
        this.revisions = revisions;
    }



    @Override
    public String toString() {
        return "Page{" +
                "pageid=" + pageid +
                ", title='" + title + '\'' +
                ", revisions=" + revisions +
                '}';
    }
}
