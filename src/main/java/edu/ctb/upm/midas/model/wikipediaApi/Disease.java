package edu.ctb.upm.midas.model.wikipediaApi;

import java.util.List;

public class Disease {
    private String id;
    private String name;
    List<Snapshot> snapshots;
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

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    @Override
    public String toString() {
        return "Disease{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", snapshots=" + snapshots +
                ", page=" + page +
                '}';
    }
}
