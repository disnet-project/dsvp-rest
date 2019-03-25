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
 * @className SynonymRetrievalMethodPK
 * @see
 */
public class SynonymRetrievalMethodPK implements Serializable {
    private Integer synonymId;
    private Integer retrievalMethodId;

    @Column(name = "synonym_id", nullable = false)
    @Id
    public Integer getSynonymId() {
        return synonymId;
    }

    public void setSynonymId(Integer synonymId) {
        this.synonymId = synonymId;
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
        SynonymRetrievalMethodPK that = (SynonymRetrievalMethodPK) o;
        return Objects.equals(synonymId, that.synonymId) &&
                Objects.equals(retrievalMethodId, that.retrievalMethodId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(synonymId, retrievalMethodId);
    }
}
