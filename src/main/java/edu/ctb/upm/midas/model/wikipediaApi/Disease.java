package edu.ctb.upm.midas.model.wikipediaApi;

import java.util.List;
import java.util.Objects;

public class Disease {
    private String id;
    private String name;
    private Integer snapshotCount;
    List<Snapshot> snapshots;
    private Page page;
    private boolean scorn;


    public Disease() {
    }

    public Disease(String id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public Integer getSnapshotCount() {
        return snapshotCount;
    }

    public void setSnapshotCount(Integer snapshotCount) {
        this.snapshotCount = snapshotCount;
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

    public boolean isScorn() {
        return scorn;
    }

    public void setScorn(boolean scorn) {
        this.scorn = scorn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease)) return false;
        Disease disease = (Disease) o;
        return Objects.equals(getId(), disease.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", snapshots=" + snapshots +
                ", page=" + page +
        ", scorn=" + scorn +
                '}';
    }
}
