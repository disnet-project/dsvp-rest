package edu.ctb.upm.disnet.service.helperNative;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.disnet.model.document_structure.Doc;
import edu.ctb.upm.disnet.model.jpa.Disease;
import edu.ctb.upm.disnet.service.DiseaseService;
import edu.ctb.upm.disnet.common.util.Common;
import edu.ctb.upm.disnet.common.util.UniqueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by gerardo on 13/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className DiseaseHelper
 * @see
 */
@Service
public class DiseaseHelperNative {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private DocumentHelperNative documentHelperNative;
    @Autowired
    private Common common;

    private static final Logger logger = LoggerFactory.getLogger(DiseaseHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;


    /**
     * @param document
     * @param documentId
     * @param version
     * @return
     * @throws JsonProcessingException
     */
    public String insertIfExist(Doc document, String documentId, Date version) throws JsonProcessingException {
        String diseaseName = document.getDisease().getName();
        String url = document.getUrl().getUrl();

        Disease diseaseEntity = diseaseService.findByName( diseaseName );
        //System.out.println(diseaseName+ "DIS: "+ diseaseEntity);
        if ( diseaseEntity == null ){
            String diseaseId = getDiseaseId();
            diseaseService.insertNative( diseaseId, diseaseName, "" );
            diseaseService.insertNativeHasDisease( documentId, version, diseaseId );
            return diseaseId;
        }else{
            //System.out.println("HasDisease: "+ documentId + " | " + version + " | " + diseaseEntity.getDiseaseId() );
            diseaseService.insertNativeHasDisease( documentId, version, diseaseEntity.getDiseaseId() );
            return diseaseEntity.getDiseaseId();
        }
    }


    /**
     * @param diseaseName
     * @return
     */
    public boolean exist(String diseaseName){
        Disease disease = diseaseService.findByName( diseaseName );
        if( disease != null )
            return true;
        else
            return false;
    }


    public String getDiseaseId(){
        String lastId = diseaseService.findLastIdNative();
        if (!common.isEmpty(lastId)){
            int last = Integer.parseInt( common.cutStringPerformance(3, 0, lastId ) );
            return uniqueId.generateDisease( last + 1 );
        }else{
            return uniqueId.generateDisease(1);
        }
    }



}
