package edu.ctb.upm.disnet.service.helperNative;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ctb.upm.disnet.model.document_structure.code.Resource;
import edu.ctb.upm.disnet.service.ResourceService;
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
 * Created by gerardo on 15/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className ResourceHelper
 * @see
 */
@Service
public class ResourceHelperNative {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UniqueId uniqueId;
    @Autowired
    private Common common;

    private static final Logger logger = LoggerFactory.getLogger(ResourceHelperNative.class);
    @Autowired
    ObjectMapper objectMapper;



    /**
     * @param resourceMap
     * @return
     * @throws Exception
     */
    public List<edu.ctb.upm.disnet.model.jpa.Resource> insertIfExist(Map<String, Resource> resourceMap) throws Exception{

        Map<String, Resource> resourceMapOrdered = new TreeMap(resourceMap);

        List<edu.ctb.upm.disnet.model.jpa.Resource> resources = new ArrayList<>();

        for (Map.Entry<String, Resource> resourceEntry:
                resourceMap.entrySet() ) {
//            System.out.println("Resource: " + resourceEntry.getKey());

            if ( exist( resourceEntry.getKey() ) ){
                resources.add( resourceService.findByName( resourceEntry.getKey() ) );
            }else {

                edu.ctb.upm.disnet.model.jpa.Resource resource = new edu.ctb.upm.disnet.model.jpa.Resource();
                resource.setName( resourceEntry.getKey() );
//                logger.info("Object Persist: {}", objectMapper.writeValueAsString(resource));
                resourceService.save(resource);
//                logger.info("Object Persist: {}", objectMapper.writeValueAsString(resource));

                resources.add( resource );
            }
        }
        return resources;
    }


    /**
     * @param nameResource
     * @return
     */
    public boolean exist(String nameResource){
        edu.ctb.upm.disnet.model.jpa.Resource resource = resourceService.findByName( nameResource );
        if( resource != null )
            return true;
        else
            return false;
    }


}
