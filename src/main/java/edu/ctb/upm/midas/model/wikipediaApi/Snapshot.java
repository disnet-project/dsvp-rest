package edu.ctb.upm.midas.model.wikipediaApi;
/**
 * Created by gerardo on 16/09/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className Snapshot
 * @see
 */
public class Snapshot {

    private Integer snapshotId;
    private String snapshot;
    private String previousSnapshot;
    private Integer revId;

    public Snapshot() {
    }


    public Snapshot(Integer snapshotId, String snapshot, String previousSnapshot) {
        this.snapshotId = snapshotId;
        this.snapshot = snapshot;
        this.previousSnapshot = previousSnapshot;
    }


    public Integer getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getPreviousSnapshot() {
        return previousSnapshot;
    }

    public void setPreviousSnapshot(String previousSnapshot) {
        this.previousSnapshot = previousSnapshot;
    }

    public Integer getRevId() {
        return revId;
    }

    public void setRevId(Integer revId) {
        this.revId = revId;
    }

    @Override
    public String toString() {
        return "Snapshot{" +
                "snapshotId=" + snapshotId +
                ", snapshot='" + snapshot + '\'' +
                ", previousSnapshot='" + previousSnapshot + '\'' +
                ", revId=" + revId +
                '}';
    }
}
