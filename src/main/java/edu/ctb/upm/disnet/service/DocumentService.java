package edu.ctb.upm.disnet.service;

import edu.ctb.upm.disnet.model.jpa.Document;
import edu.ctb.upm.disnet.model.jpa.DocumentPK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 12/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className DocumentService
 * @see
 */
public interface DocumentService {

    Document findById(DocumentPK documentPk);

    List<Document> findAll();

    void save(Document document);

    int insertNative(String documentId, Date date);

    int insertNativeUrl(String documentId, Date date, String urlId);

    int insertNativeHasSource(String documentId, Date date, String sourceId);

    boolean updateFindFull(Document document, DocumentPK documentPk);

    boolean updateFindPartial(Document document, DocumentPK documentPk);

    boolean deleteById(DocumentPK documentPk);
    
}
