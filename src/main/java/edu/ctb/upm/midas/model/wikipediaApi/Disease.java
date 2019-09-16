package edu.ctb.upm.midas.model.wikipediaApi;

import java.util.Date;

public class Disease {
    private String id;
    private String name;
    private Integer snapshotId;
    private Date currentSnapshot;
    private Date previousSnapshot;
    private Page page;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    public Date getCurrentSnapshot() {
        return currentSnapshot;
    }

    public void setCurrentSnapshot(Date currentSnapshot) {
        this.currentSnapshot = currentSnapshot;
    }

    public Date getPreviousSnapshot() {
        return previousSnapshot;
    }

    public void setPreviousSnapshot(Date previousSnapshot) {
        this.previousSnapshot = previousSnapshot;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
