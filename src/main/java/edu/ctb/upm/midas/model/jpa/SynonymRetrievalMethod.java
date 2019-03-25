package edu.ctb.upm.midas.model.jpa;
import javax.persistence.*;
import java.util.Objects;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className SynonymRetrievalMethod
 * @see
 */
@Entity
@Table(name = "synonym_retrieval_method", schema = "edsssdb", catalog = "")
@IdClass(SynonymRetrievalMethodPK.class)
public class SynonymRetrievalMethod {
    private Integer synonymId;
    private Integer retrievalMethodId;

    @Id
    @Column(name = "synonym_id", nullable = false)
    public Integer getSynonymId() {
        return synonymId;
    }

    public void setSynonymId(Integer synonymId) {
        this.synonymId = synonymId;
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
        SynonymRetrievalMethod that = (SynonymRetrievalMethod) o;
        return Objects.equals(synonymId, that.synonymId) &&
                Objects.equals(retrievalMethodId, that.retrievalMethodId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(synonymId, retrievalMethodId);
    }
}
