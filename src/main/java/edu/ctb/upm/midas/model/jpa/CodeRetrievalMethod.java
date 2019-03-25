package edu.ctb.upm.midas.model.jpa;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className CodeRetrievalMethod
 * @see
 */
@Entity
@Table(name = "code_retrieval_method", schema = "edsssdb", catalog = "")
@IdClass(CodeRetrievalMethodPK.class)
public class CodeRetrievalMethod {
    private String code;
    private Integer resourceId;
    private Integer retrievalMethodId;

    @Id
    @Column(name = "code", nullable = false, length = 150)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Id
    @Column(name = "resource_id", nullable = false)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Id
    @Column(name = "retrieval_method_id", nullable = false)
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
        CodeRetrievalMethod that = (CodeRetrievalMethod) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(retrievalMethodId, that.retrievalMethodId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, resourceId, retrievalMethodId);
    }
}
