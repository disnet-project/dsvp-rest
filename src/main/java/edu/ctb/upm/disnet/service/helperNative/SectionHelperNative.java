package edu.ctb.upm.disnet.service.helperNative;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.disnet.model.wikipedia.document_structure.Section;
import edu.ctb.upm.disnet.service.SectionService;
import edu.ctb.upm.disnet.common.util.Common;
import edu.ctb.upm.disnet.common.util.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by gerardo on 12/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className SectionHelper
 * @see
 */
@Service
public class SectionHelperNative {


    @Autowired
    private SectionService sectionService;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private Common common;

    private static final Logger logger = LoggerFactory.getLogger(SectionHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;



    /**
     * @param sectionMap
     * @throws Exception
     */
    public void insertIfExist(TreeMap<String, String> sectionMap) throws Exception{
        for (Map.Entry<String, String> section:
                sectionMap.entrySet()) {
//            System.out.println("SEC: " + section.getKey() + "_" + section.getValue());
            if ( !exist(section.getKey()) ){
//                System.out.println("SEC: NO EXISTE ");
                String sectionId = getLastId();
                sectionService.insertNative( sectionId, section.getKey(), section.getValue() );
            }
        }
    }


    /**
     * @param sectionList
     * @return
     * @throws Exception
     */
    public List<edu.ctb.upm.disnet.model.jpa.Section> getSectionList(List<Section> sectionList) throws Exception{
        List<edu.ctb.upm.disnet.model.jpa.Section> sections = new ArrayList<>();
        edu.ctb.upm.disnet.model.jpa.Section sectionEntity;

        for (Section section:
             sectionList) {
            sectionEntity = sectionService.findByName( section.getName() );
            sections.add( sectionEntity );
        }

        return sections;
    }


    /**
     * @param sectionName
     * @return
     */
    public boolean exist(String sectionName){
        edu.ctb.upm.disnet.model.jpa.Section section = sectionService.findByName( sectionName );
        if( section != null )
            return true;
        else
            return false;
    }


    /**
     * @return
     */
    public String getLastId(){

        if (sectionService.findAll().size() > 0){
            String sectionId = sectionService.findLastSectionIdQuery();
            int last = Integer.parseInt( common.cutStringPerformance(3, 0, sectionId ) );
            //System.out.println("secId: " + sectionId + " last: " + (last+1) + " |" + common.cutStringPerformance(3, 0, sectionId ));

            return uniqueId.generateSection( last + 1 );

        }else{
            return uniqueId.generateSection( 1 );
        }

    }


}
