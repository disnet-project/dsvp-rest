package edu.ctb.upm.midas.model.jpa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 20/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project demo
 * @className Text
 * @see
 */
@Entity
@Table(name = "text", catalog = "edsssdb", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Text.findAll", query = "SELECT t FROM Text t")
        , @NamedQuery(name = "Text.findById", query = "SELECT t FROM Text t WHERE t.textId = :textId")
        , @NamedQuery(name = "Text.findByTextId", query = "SELECT t FROM Text t WHERE t.textId = :textId")
        , @NamedQuery(name = "Text.findByContentType", query = "SELECT t FROM Text t WHERE t.contentType = :contentType")
        , @NamedQuery(name = "Text.updateById", query = "UPDATE Text t SET t.contentType = :contentType, t.text = :text WHERE t.textId = :textId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Text.findByIdNative",
                query = "SELECT t.text_id, t.content_type, t.text "
                        + "FROM text t WHERE t.text_id = :textId",
                resultSetMapping="TextMapping"

        ),
        @NamedNativeQuery(
                name = "Text.findByIdNativeResultClass",
                query = "SELECT t.text_id, t.content_type, t.text "
                        + "FROM text t WHERE t.text_id = :textId",
                resultClass = Text.class
        ),


        @NamedNativeQuery(
                name = "Text.insertNative",
                query = "INSERT IGNORE INTO text (text_id, content_type, text) "
                        + "VALUES (:textId, :contentType, :text)"
        ),
        @NamedNativeQuery(
                name = "HasText.insertNative_",
                query = "INSERT IGNORE INTO has_text (document_id, date, section_id, text_id, text_order) "
                        + "VALUES (:documentId, :date, :sectionId, :textId, :textOrder)"
        ),
        @NamedNativeQuery(
                name = "TextUrl.insertNative",
                query = "INSERT IGNORE INTO text_url (text_id, url_id) "
                        + "VALUES (:textId, :urlId)"
        ),


        @NamedNativeQuery(
                name = "Text.findBySourceAndVersionNative",
                query = "SELECT s.source_id, s.name, ht.document_id, ht.date, t.text_id, t.content_type, t.text " +
                        "FROM has_text ht " +
                        "LEFT JOIN text t ON t.text_id = ht.text_id " +
                        "LEFT JOIN has_source hs ON hs.document_id = ht.document_id AND hs.date = ht.date " +
                        "LEFT JOIN source s ON s.source_id = hs.source_id " +
                        "WHERE ht.date = :version " +
                        "AND s.name = :source " +
                        "AND t.text IS NOT NULL " +
                        "AND LENGTH(t.text) > 0 " //EVITAR VALORES VACIOS
        ),


        // Esta consulta no es buena porque solo consulta like con la version y no con la fuente
        // pero de momento se queda así para solucionar el problema de retomar la inserción de terminos metamap
        @NamedNativeQuery(
                name = "Text.findByLikeVersionNative",
                query = "SELECT s.source_id, s.name, ht.document_id, ht.date, t.text_id, t.content_type, t.text " +
                        "FROM has_text ht " +
                        "INNER JOIN text t ON t.text_id = ht.text_id " +
                        "INNER JOIN has_source hs ON hs.document_id = ht.document_id AND hs.date = ht.date " +
                        "INNER JOIN source s ON s.source_id = hs.source_id " +
                        "WHERE ht.date = :version " +
                        "AND s.name = :source  " +
                        "AND t.text IS NOT NULL  " +
                        "AND LENGTH(t.text) > 0 " +
                        "AND ht.text_id NOT IN " +
                        "( " +
                        "SELECT DISTINCT tt.text_id " +
                        "FROM text tt " +
                        "INNER JOIN has_symptom hsym on hsym.text_id = tt.text_id " +
                        "WHERE tt.text_id LIKE :versionLike " +
                        "    AND tt.text_id IS NOT NULL " +
                        "    AND LENGTH(tt.text) > 0 " +
                        ") " //EVITAR VALORES VACIOS
        ),
        @NamedNativeQuery(
                name = "Text.findTextWithDetails",
                query = "SELECT DISTINCT d.disease_id " +
                        "              , d.name " +
                        "              , s.description " +
                        "              , ht.text_order " +
                        "              , t.content_type " +
                        "              , t.text_id " +
                        "              , getDisnetConceptsCountInAText(:source, :snapshot, t.text_id, 0) has_no_val_me " +
                        "              , getDisnetConceptsCountInAText(:source, :snapshot, t.text_id, 1) has_val_me " +
                        "              , t.text " +
                        "              -- , wordCount(t.text) word_count\n " +
                        "              , count(DISTINCT u.url_id) url_count " +
                        " FROM disease d " +
                        " INNER JOIN has_disease hd ON hd.disease_id = d.disease_id " +
                        " INNER JOIN document doc ON doc.document_id = hd.document_id AND doc.date = hd.date " +
                        " INNER JOIN has_source hs ON hs.document_id = doc.document_id AND hs.date = doc.date " +
                        " INNER JOIN source sce ON sce.source_id = hs.source_id " +
                        "-- source and version\n" +
                        " INNER JOIN has_section hsec ON hsec.document_id = doc.document_id AND hsec.date = doc.date " +
                        " INNER JOIN section s on hsec.section_id = s.section_id " +
                        " INNER JOIN has_text ht ON ht.document_id = hsec.document_id AND ht.date = hsec.date AND ht.section_id = hsec.section_id " +
                        " INNER JOIN text t on ht.text_id = t.text_id " +
                        " INNER JOIN has_symptom hsym ON hsym.text_id = ht.text_id " +
                        " INNER JOIN symptom sym ON sym.cui = hsym.cui " +
                        " LEFT JOIN text_url tu on t.text_id = tu.text_id " +
                        " LEFT JOIN url u on tu.url_id = u.url_id " +
                        "WHERE sce.name = :source " +
                        "AND hs.date = :snapshot " +
                        "AND d.relevant = 1 " +
                        "GROUP BY d.name, d.disease_id, t.text_id " +
                        "ORDER BY d.name ASC"
        ),
        @NamedNativeQuery(
                name = "getDisnetConceptsCountInAText",
                query = "SELECT getDisnetConceptsCountInAText(:source, :snapshot, :text_id, :validated_medical_element)"
        )





})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "TextMapping",
                entities = @EntityResult(
                        entityClass = Text.class,
                        fields = {
                                @FieldResult(name = "textId", column = "text_id"),
                                @FieldResult(name = "contentType", column = "content_type"),
                                @FieldResult(name = "text", column = "text")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="textId")
public class Text {
    private String textId;
    private String contentType;
    private String text;
    private List<HasSymptom> hasSymptomsByTextId;
    private List<HasText> hasTextsByTextId;
    private List<TextUrl> textUrlsByTextId;

    @Id
    @Column(name = "text_id", nullable = false, length = 255)
    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    @Basic
    @Column(name = "content_type", nullable = false, length = 10)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "text", nullable = false, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text1 = (Text) o;
        return Objects.equals(textId, text1.textId) &&
                Objects.equals(contentType, text1.contentType) &&
                Objects.equals(text, text1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textId, contentType, text);
    }

    @OneToMany(mappedBy = "textByTextId")
    public List<HasSymptom> getHasSymptomsByTextId() {
        return hasSymptomsByTextId;
    }

    public void setHasSymptomsByTextId(List<HasSymptom> hasSymptomsByTextId) {
        this.hasSymptomsByTextId = hasSymptomsByTextId;
    }

    @OneToMany(mappedBy = "textByTextId")
    public List<HasText> getHasTextsByTextId() {
        return hasTextsByTextId;
    }

    public void setHasTextsByTextId(List<HasText> hasTextsByTextId) {
        this.hasTextsByTextId = hasTextsByTextId;
    }

    @OneToMany(mappedBy = "textByTextId")
    public List<TextUrl> getTextUrlsByTextId() {
        return textUrlsByTextId;
    }

    public void setTextUrlsByTextId(List<TextUrl> textUrlsByTextId) {
        this.textUrlsByTextId = textUrlsByTextId;
    }
}
