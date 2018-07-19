package edu.ctb.upm.midas.model.pumed;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.ctb.upm.midas.model.common.document_structure.Link;
import edu.ctb.upm.midas.model.common.document_structure.Synonym;
import edu.ctb.upm.midas.model.common.document_structure.Term;
import edu.ctb.upm.midas.model.common.document_structure.code.Code;

import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 29/3/17.
 * @project ExtractionInformationWikipedia
 * @version ${<VERSION>}
 * @author Gerardo Lagunes G.
 * @className Disease
 * @see
 */
public class Disease extends edu.ctb.upm.midas.model.common.document_structure.Disease {

    private String meSHUI;//único para pubmed
    private String meSHMH;//términos mesh con los que se buscan los artículos
    //mismos que meSHMH, pero dentro de una lista separados
    private Integer meSHTermCount;
    private List<Term> meSHTerms;
    private List<String> meSHMN;//códigos de la estructura de árbol
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer synonymCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Synonym> synonyms;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer codeCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Code> codes;
    private String isA;//Relacionado con otras enfermedades
    private List<Link> links;




    public String getMeSHUI() {
        return meSHUI;
    }

    public void setMeSHUI(String meSHUI) {
        this.meSHUI = meSHUI;
    }

    public String getMeSHMH() {
        return meSHMH;
    }

    public void setMeSHMH(String meSHMH) {
        this.meSHMH = meSHMH;
    }

    public Integer getMeSHTermCount() {
        return meSHTermCount;
    }

    public void setMeSHTermCount(Integer meSHTermCount) {
        this.meSHTermCount = meSHTermCount;
    }

    public List<Term> getMeSHTerms() {
        return meSHTerms;
    }

    public void setMeSHTerms(List<Term> meSHTerms) {
        this.meSHTerms = meSHTerms;
    }

    public List<String> getMeSHMN() {
        return meSHMN;
    }

    public void setMeSHMN(List<String> meSHMN) {
        this.meSHMN = meSHMN;
    }

    public Integer getSynonymCount() {
        return synonymCount;
    }

    public void setSynonymCount(Integer synonymCount) {
        this.synonymCount = synonymCount;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public Integer getCodeCount() {
        return codeCount;
    }

    public void setCodeCount(Integer codeCount) {
        this.codeCount = codeCount;
    }

    public List<Code> getCodes() {
        return codes;
    }

    public void setCodes(List<Code> codes) {
        this.codes = codes;
    }

    public String getIsA() {
        return isA;
    }

    public void setIsA(String isA) {
        this.isA = isA;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease)) return false;
        Disease disease = (Disease) o;
        return Objects.equals(getMeSHUI(), disease.getMeSHUI());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMeSHUI());
    }

    @Override
    public String toString() {
        return "Disease{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", definition='" + super.getDefinition() + '\'' +
                ", cui='" + super.getCui() + '\'' +
                ", meSHUI='" + meSHUI + '\'' +
                ", meSHMH='" + meSHMH + '\'' +
                ", meSHTermCount=" + meSHTermCount +
                ", meSHTerms=" + meSHTerms +
                ", meSHMN=" + meSHMN +
                ", synonymCount=" + synonymCount +
                ", synonyms=" + synonyms +
                ", codeCount=" + codeCount +
                ", codes=" + codes +
                ", isA='" + isA + '\'' +
                ", links=" + links +
                '}';
    }
}
