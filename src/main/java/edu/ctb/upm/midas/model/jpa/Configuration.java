package edu.ctb.upm.midas.model.jpa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by gerardo on 20/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project demo
 * @className Configuration
 * @see
 */
@Entity
@Table(name = "configuration", catalog = "edsssdb", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Configuration.findAll", query = "SELECT c FROM Configuration c")
        , @NamedQuery(name = "Configuration.findByConfId", query = "SELECT c FROM Configuration c WHERE c.confId = :confId")
        , @NamedQuery(name = "Configuration.findByTool", query = "SELECT c FROM Configuration c WHERE c.toolId = :toolId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Configuration.insertNative",
                query = "INSERT INTO configuration (conf_id, source_id, snapshot, tool_id, configuration) " +
                        "VALUES (:conf_id, :source_id, :snapshot, :tool_id, :configuration) "
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "ConfigurationMapping",
                entities = @EntityResult(
                        entityClass = Configuration.class,
                        fields = {
                                @FieldResult(name = "confId", column = "conf_id"),
                                @FieldResult(name = "sourceId", column = "source_id"),
                                @FieldResult(name = "version", column = "version"),
                                @FieldResult(name = "tool", column = "tool"),
                                @FieldResult(name = "configuration", column = "configuration")
                        }
                )
        )
})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="confId")
public class Configuration {
    private String confId;
    private String sourceId;
    private Date snapshot;
    private String toolId;
    private String configuration;

    @Id
    @Column(name = "conf_id", nullable = false, length = 50)
    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    @Basic
    @Column(name = "source_id", nullable = false, length = 10)
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Basic
    @Column(name = "version", nullable = false)
    public Date getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Date version) {
        this.snapshot = version;
    }

    @Basic
    @Column(name = "tool", nullable = false, length = 50)
    public String getToolId() {
        return toolId;
    }

    public void setToolId(String tool) {
        this.toolId = tool;
    }

    @Basic
    @Column(name = "configuration", nullable = true, length = -1)
    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(confId, that.confId) &&
                Objects.equals(toolId, that.toolId) &&
                Objects.equals(configuration, that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confId, toolId, configuration);
    }
}
