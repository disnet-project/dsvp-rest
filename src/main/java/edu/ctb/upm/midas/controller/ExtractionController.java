package edu.ctb.upm.midas.controller;

import edu.ctb.upm.midas.service._extract.MayoClinicExtractService;
import edu.ctb.upm.midas.service._extract.WikipediaExtractService;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by gerardo on 05/07/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project validation_medical_term
 * @className ValidationController
 * @see
 */
@RestController
@RequestMapping("${my.service.rest.request.mapping.retrieval.general.url}")
public class ExtractionController {


    @Autowired
    private WikipediaExtractService wikipediaExtractService;
    @Autowired
    private MayoClinicExtractService mayoClinicExtractService;

    @RequestMapping(path = { "${my.service.rest.request.mapping.wikipedia.retrieval.texts.path}" }, //_wikipedia extraction
            method = RequestMethod.GET,
            params = {"snapshot"})
    public String extract(@RequestParam(value = "snapshot") @Valid @NotBlank @NotNull @NotEmpty String snapshot,
                          @RequestParam(value = "json", required = false, defaultValue = "false") boolean json) throws Exception {
        wikipediaExtractService.extract(snapshot, json);
        return "Successful extraction and insertion in a DB!";
    }

    @RequestMapping(path = { "/wikipedia/report" }, //wikipedia extraction
            method = RequestMethod.GET)
    public void extractOnly() throws Exception {
        wikipediaExtractService.onlyExtract();
    }


    @RequestMapping(path = { "${my.service.rest.request.mapping.mayoclinic.retrieval.texts.path}" }, //_wikipedia extraction
            method = RequestMethod.GET,
            params = {"snapshot", "json"})
    public String mayoClinicExtract(
            @RequestParam(value = "snapshot") @Valid @NotBlank @NotNull @NotEmpty String snapshot,
            @RequestParam(value = "json", required = false, defaultValue = "true") boolean json) throws Exception {
        mayoClinicExtractService.extract(snapshot, json);
        return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/mayoclinic/report" }, //wikipedia extraction
            method = RequestMethod.GET,
            params = {"snapshot", "json"})
    public void mayoClinicReport(@RequestParam(value = "snapshot") @Valid @NotBlank @NotNull @NotEmpty String snapshot,
                                 @RequestParam(value = "json", required = false, defaultValue = "true") boolean json) throws Exception {
        mayoClinicExtractService.printReport(snapshot, json);
    }


}
