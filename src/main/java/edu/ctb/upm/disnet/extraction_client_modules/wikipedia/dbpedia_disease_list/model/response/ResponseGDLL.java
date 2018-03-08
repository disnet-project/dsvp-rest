package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response;

import java.util.List;

public class ResponseGDLL extends ResponseFather{

    private List<Disease> diseases;


    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    @Override
    public String toString() {
        return "ResponseGDLL{responseFather=" + super.toString() +
                "diseases=" + diseases +
                '}';
    }
}
