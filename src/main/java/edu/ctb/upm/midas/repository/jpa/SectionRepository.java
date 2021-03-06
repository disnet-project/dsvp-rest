package edu.ctb.upm.midas.repository.jpa;

import edu.ctb.upm.midas.model.jpa.Section;

import java.util.List;

/**
 * Created by gerardo on 12/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className SectionRepository
 * @see
 */
public interface SectionRepository {

    Section findById(String sectionId);

    Section findByIdQuery(String sectionId);

    Section findByNameQuery(String sectionName);

    Section findByDescriptionQuery(String sectionDesc);

    Section findLastSectionQuery();

    String findLastSectionIdQuery();

    Section findByIdNative(String sectionId);

    Section findByIdNativeResultClass(String sectionId);

    List<Section> findAllQuery();

    void persist(Section section);

    int insertNative(String sectionId, String name, String description);

    boolean deleteById(String sectionId);

    void delete(Section section);

    Section update(Section section);

    int updateByIdQuery(Section section);
    
}
