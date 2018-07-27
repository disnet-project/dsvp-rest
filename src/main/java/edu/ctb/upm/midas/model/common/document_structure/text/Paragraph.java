package edu.ctb.upm.midas.model.common.document_structure.text;
/**
 * Created by gerardo on 4/4/17.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationWikipedia
 * @className Paragraph
 * @see
 */
public class Paragraph extends Text {

    public Paragraph() {
    }

    public Paragraph(int id, int textOrder, String title, String text) {
        super(id, title, textOrder);
        this.text = text;

    }

    private String text;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
