package edu.ctb.upm.midas.service.jpa.helperNative;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.midas.model.common.document_structure.Section;
import edu.ctb.upm.midas.service.jpa.HasSectionService;
import edu.ctb.upm.midas.service.jpa.SectionService;
import edu.ctb.upm.midas.common.util.Common;
import edu.ctb.upm.midas.common.util.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by gerardo on 26/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className HasSectionHelper
 * @see
 */
@Service
public class HasSectionHelperNative {


    @Autowired
    private HasSectionService hasSectionService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionHelperNative sectionHelperNative;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private Common common;

    private static final Logger logger = LoggerFactory.getLogger(HasSectionHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;


    /**
     * @param documentId
     * @param version
     * @param section
     * @return
     */
    @Transactional
    public String insert(String documentId, Date version, Section section){
        //Busca la sección que ya debe existir
//        System.out.println("Sección a buscar: " + section.getName());
        edu.ctb.upm.midas.model.jpa.Section existSection = sectionService.findByName( section.getName() );
        if (existSection!=null) {
            //inserta la relación entre el documento y la sección (insert ignore)
            hasSectionService.insertNative(documentId, version, existSection.getSectionId());
        }else{
            //inserta la seccion si no existe
            sectionHelperNative.insert(section.getName(), section.getDescription());
            existSection = sectionService.findByName( section.getName() );
            //inserta la relación entre el documento y la sección (insert ignore)
            hasSectionService.insertNative(documentId, version, existSection.getSectionId());
        }
        //retorna el id de la sección
        return existSection.getSectionId();
    }





}
