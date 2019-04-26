package edu.ctb.upm.midas.service._extract;
import edu.ctb.upm.midas.common.util.Common;
import edu.ctb.upm.midas.common.util.TimeProvider;
import edu.ctb.upm.midas.constants.Constants;
import edu.ctb.upm.midas.model.jpa.Code;
import edu.ctb.upm.midas.model.jpa.Disease;
import edu.ctb.upm.midas.service.jpa.CodeService;
import edu.ctb.upm.midas.service.jpa.DiseaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

/**
 * Created by gerardo on 22/04/2019.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project dsvp-rest
 * @className ReportTest
 * @see
 */
@Service
public class ReportTest {

    private static final Logger logger = LoggerFactory.getLogger(ReportTest.class);

    @Autowired
    TimeProvider timeProvider;
    @Autowired
    private Constants constants;
    @Autowired
    private Common common;

    @Autowired
    DiseaseService diseaseService;
    @Autowired
    CodeService codeService;

    public static final String DISNET_UTS_CROSSWALK_SERVICE_URL = "http://localhost:8095/api/uts_crosswalk/search_term";
    public static final String DISEASE_SOURCE_MESH = "MeSH";
    public static final String DISEASE_CODE_MESH = "MSH";
    public static final String DISEASE_CODE_OMIM = "OMIM";
    public static final String DISEASE_CODE_ICD10 = "ICD-10";

    private static final String SOURCE_PARAM_KEY = "source";
    private static final String SOURCE_CODE_PARAM_KEY = "sourceCode";



    public void getDiseaseCodesInfo() throws ParseException {
        String sourceOrigin = "pubmed";
        String snapshotOrigin = "2018-04-03";
        String findSource = "MeSH";
        int findSourceId = 75;
        String sourceTarget = "wikipedia";

//        ReportTest.CodeList externalCodes = getCUIDisease("MSH", "D018366");
//        for (String code: externalCodes.getCodes()) {
//            System.out.println(code);
//        }

        int count = 1;
        for (Disease disease: diseaseService.findAllBySourceAndVersionNativeDiseases(sourceOrigin, timeProvider.stringToDate(snapshotOrigin))) {
            System.out.println(count + ". Disease => id: " + disease.getDiseaseId() + ", name: " + disease.getName());
//            List<Code> codes = codeService.findBySourceAndSnapshotAndDiseaseIdNative(sourceOrigin, timeProvider.stringToDate(snapshotOrigin), disease.getDiseaseId()/*, findSource*/);
            List<Code> codes = codeService.findBySourceAndSnapshotAndDiseaseIdAndResourceNameNative(sourceOrigin, timeProvider.stringToDate(snapshotOrigin), disease.getDiseaseId(), findSource);
            if (codes!=null) {
                System.out.print("Code => ");
                for (Code code : codes) {
                    ReportTest.CodeList cuiCodes = getCUIDisease(DISEASE_CODE_MESH, code.getCode());
                    System.out.print(" @ code: " + code.getCode() + " | CUI code: ");
                    cuiCodes.getCodes().forEach(System.out::print);
                }
                System.out.println("");
            }
            count++;
        }

    }


    public ReportTest.CodeList getCUIDisease(String source, String sourceCode) {
        String code = "";
        URI targetUrl = UriComponentsBuilder.fromUriString(DISNET_UTS_CROSSWALK_SERVICE_URL)
                .queryParam(SOURCE_PARAM_KEY, source)
                .queryParam(SOURCE_CODE_PARAM_KEY, sourceCode)
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        try {
            System.out.println("entra");
            ReportTest.CodeList codes = restTemplate.getForObject(targetUrl, ReportTest.CodeList.class);
            if (codes!=null)System.out.println("info");
            else System.out.println("no info");

            return codes;

        }catch (Exception e){
            logger.error("Unexpected response", e);
            return null;
        }


    }

    public static class CodeList {
        private List<String> codes;

        public List<String> getCodes() {
            return codes;
        }

        public void setCodes(List<String> codes) {
            this.codes = codes;
        }
    }


}
