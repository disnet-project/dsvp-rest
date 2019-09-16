package edu.ctb.upm.midas.model.wikipediaApi;

public class Section {

    private Integer toclevel;
    private String level;
    private String line;
    private String number;
    private String index;
    private String fromtitle;
    private Integer byteoffset;
    private String anchor;


    public Integer getToclevel() {
        return toclevel;
    }

    public void setToclevel(Integer toclevel) {
        this.toclevel = toclevel;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getFromtitle() {
        return fromtitle;
    }

    public void setFromtitle(String fromtitle) {
        this.fromtitle = fromtitle;
    }

    public Integer getByteoffset() {
        return byteoffset;
    }

    public void setByteoffset(Integer byteoffset) {
        this.byteoffset = byteoffset;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
}
