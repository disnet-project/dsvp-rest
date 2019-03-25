package edu.ctb.upm.midas.model.jpa;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className CodeRetrievalMethodPK
 * @see
 */
public class CodeRetrievalMethodPK implements Serializable {
    private String code;
    private Integer resourceId;
    private Integer retrievalMethodId;

    @Column(name = "code", nullable = false, length = 150)
    @Id
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "resource_id", nullable = false)
    @Id
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Column(name = "retrieval_method_id", nullable = false)
    @Id
    public Integer getRetrievalMethodId() {
        return retrievalMethodId;
    }

    public void setRetrievalMethodId(Integer retrievalMethodId) {
        this.retrievalMethodId = retrievalMethodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeRetrievalMethodPK that = (CodeRetrievalMethodPK) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(retrievalMethodId, that.retrievalMethodId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, resourceId, retrievalMethodId);
    }
}
