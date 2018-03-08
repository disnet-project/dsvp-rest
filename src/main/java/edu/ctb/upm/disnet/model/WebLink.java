package edu.ctb.upm.disnet.model;
import java.util.Objects;

/**
 * Created by gerardo on 09/02/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project populate-rest
 * @className WebLink
 * @see
 */
public class WebLink {

    private Integer id;
    private String url;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WebLink)) return false;
        WebLink webLink = (WebLink) o;
        return Objects.equals(getUrl(), webLink.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUrl());
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
