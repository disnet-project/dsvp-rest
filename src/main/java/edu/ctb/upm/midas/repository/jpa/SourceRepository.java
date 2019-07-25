package edu.ctb.upm.midas.repository.jpa;

import edu.ctb.upm.midas.model.jpa.Source;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 28/04/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className SourceRepository
 * @see
 */
public interface SourceRepository {

    Source findById(String sourceId);

    Source findByIdQuery(String sourceId);

    Source findByNameQuery(String sourceName);

    String findByNameNative(String sourceName);

    Source findLastSourceQuery();

    String findLastSourceIdQuery();

    Source findByIdNative(String sourceId);

    Source findByIdNativeResultClass(String sourceId);

    List<Date> findAllSnapshotBySourceNative(String source);

    List<Source> findAllQuery();

    void persist(Source source);

    int insertNative(String sourceId, String name);

    int insertNativeUrl(String sourceId, String urlId);

    boolean deleteById(String sourceId);

    void delete(Source source);

    Source update(Source source);

    int updateByIdQuery(Source source);

}
