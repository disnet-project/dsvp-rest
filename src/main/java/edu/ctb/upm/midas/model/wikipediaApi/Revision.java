package edu.ctb.upm.midas.model.wikipediaApi;

import java.util.List;

public class Revision {

    private Integer revid;
    private Integer parentid;
    private Integer size;
    private String user;
    private Integer userid;
    private String timestamp;
    private String date;
    private String previousDate;
    private String comment;
    private boolean minor;
    private Integer snapshotId;
    private String snapshot;
    private String previousSnapshot;
    private Integer sectionCount;
    private List<Section> sections;
    private String text;


    public Revision() {
    }

    public Revision(Integer snapshotId, String snapshot, String previousSnapshot) {
        this.snapshotId = snapshotId;
        this.snapshot = snapshot;
        this.previousSnapshot = previousSnapshot;
    }

    public Integer getRevid() {
        return revid;
    }

    public void setRevid(Integer revid) {
        this.revid = revid;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMinor() {
        return minor;
    }

    public void setMinor(boolean minor) {
        this.minor = minor;
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

    public Integer getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(Integer sectionCount) {
        this.sectionCount = sectionCount;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Revision{" +
                "revid=" + revid +
                ", parentid=" + parentid +
                ", size=" + size +
                ", user='" + user + '\'' +
                ", userid=" + userid +
                ", timestamp='" + timestamp + '\'' +
                ", date='" + date + '\'' +
                ", previousDate='" + previousDate + '\'' +
                ", comment='" + comment + '\'' +
                ", minor=" + minor +
                ", snapshotId=" + snapshotId +
                ", snapshot='" + snapshot + '\'' +
                ", previousSnapshot='" + previousSnapshot + '\'' +
                ", sections='" + sections + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
