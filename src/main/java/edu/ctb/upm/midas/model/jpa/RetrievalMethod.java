package edu.ctb.upm.midas.model.jpa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by gerardo on 25/03/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className RetrievalMethod
 * @see
 */
@Entity
@Table(name = "retrieval_method", schema = "edsssdb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RetrievalMethod.findAll", query = "SELECT r FROM RetrievalMethod r")
        , @NamedQuery(name = "RetrievalMethod.findById", query = "SELECT r FROM RetrievalMethod r WHERE r.retrievalMethodId = :retrievalMethodId")
        , @NamedQuery(name = "RetrievalMethod.findByName", query = "SELECT r FROM RetrievalMethod r WHERE r.name = :name")
        , @NamedQuery(name = "RetrievalMethod.findByDescription", query = "SELECT r FROM RetrievalMethod r WHERE r.description = :description")
        , @NamedQuery(name = "RetrievalMethod.updateById", query = "UPDATE RetrievalMethod r SET r.name = :name WHERE r.retrievalMethodId = :retrievalMethodId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "RetrievalMethod.findByIdNative",
                query = "SELECT r.retrieval_method_id, r.name, r.description "
                        + "FROM retrieval_method r WHERE r.retrieval_method_id = :retrievalMethodId",
                resultClass = RetrievalMethod.class

        ),
        @NamedNativeQuery(
                name = "RetrievalMethod.findByNameNative",
                query = "SELECT r.retrieval_method_id, r.name, r.description "
                        + "FROM retrieval_method r WHERE r.name LIKE :name",
                resultClass = RetrievalMethod.class
        ),
        @NamedNativeQuery(
                name = "RetrievalMethod.findByDescriptionNative",
                query = "SELECT r.retrieval_method_id, r.name, r.description "
                        + "FROM retrieval_method r WHERE r.description LIKE :description",
                resultClass = RetrievalMethod.class
        ),


        @NamedNativeQuery(
                name = "RetrievalMethod.insertNative",
                query = "INSERT INTO retrieval_method (retrieval_method_id, name, description) "
                        + "VALUES (:retrievalMethodId, :name, :description)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "RetrievalMethodMapping",
                entities = @EntityResult(
                        entityClass = RetrievalMethod.class,
                        fields = {
                                @FieldResult(name = "retrievalMethodId", column = "retrieval_method_id"),
                                @FieldResult(name = "name", column = "name"),
                                @FieldResult(name = "description", column = "description")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="retrievalMethodId")
public class RetrievalMethod {
    private Integer retrievalMethodId;
    private String name;
    private String description;

    @Id
    @Column(name = "retrieval_method_id", nullable = false)
    public Integer getRetrievalMethodId() {
        return retrievalMethodId;
    }

    public void setRetrievalMethodId(Integer retrievalMethodId) {
        this.retrievalMethodId = retrievalMethodId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 350)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrievalMethod that = (RetrievalMethod) o;
        return Objects.equals(retrievalMethodId, that.retrievalMethodId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(retrievalMethodId, name, description);
    }
}
