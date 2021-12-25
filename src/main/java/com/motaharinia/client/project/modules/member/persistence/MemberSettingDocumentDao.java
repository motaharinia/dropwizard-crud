package com.motaharinia.client.project.modules.member.persistence;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.motaharinia.client.project.utility.document.customcounter.CustomCounterDocumentDao;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس دسترسی داده تنظیمات عضو
 */
public class MemberSettingDocumentDao {

    private final MongoCollection<Document> documentCollection;
    private final CustomCounterDocumentDao customCounterDocumentDao;

    public MemberSettingDocumentDao(MongoCollection<Document> documentCollection, CustomCounterDocumentDao customCounterDocumentDao) {
        this.documentCollection = documentCollection;
        this.customCounterDocumentDao = customCounterDocumentDao;
    }

    public void save(@NotNull final MemberSettingDocument document) {
        if (ObjectUtils.isEmpty(document.getId())) {
            documentCollection.insertOne(Objects.requireNonNull(toMongo(document)));
        } else {
            documentCollection.updateOne(new Document("_id", document.getId()), new Document("$set", Objects.requireNonNull(toMongo(document))));
        }
    }

    @NotNull
    public Optional<MemberSettingDocument> findById(@NotNull final Long id) {
        final Optional<Document> optionalMongoDocument = Optional.ofNullable(documentCollection.find(new Document("_id", id)).first());
        return optionalMongoDocument.map(this::fromMongo);
    }

    @NotNull
    public List<MemberSettingDocument> findAll() {
        final MongoCursor<Document> documentCursor = documentCollection.find().iterator();
        final List<MemberSettingDocument> resultSet = new ArrayList<>();
        try {
            while (documentCursor.hasNext()) {
                final Document donut = documentCursor.next();
                resultSet.add(fromMongo(donut));
            }
        } finally {
            documentCursor.close();
        }
        return resultSet;
    }

    public void delete(@NotNull final MemberSettingDocument document) {
        documentCollection.deleteOne(new Document("_id", document.getId()));
    }

    private MemberSettingDocument fromMongo(final Document mongoDocument) {
        if (ObjectUtils.isEmpty(mongoDocument)) {
            return null;
        }
        final MemberSettingDocument document = new MemberSettingDocument();
        document.setId(mongoDocument.getLong("_id"));
        document.setFontSize(mongoDocument.getInteger("font_size"));
        document.setColor(mongoDocument.getString("color"));
        document.setDateOfRequest(mongoDocument.getLong("date_of_request"));
        document.setAccountSet(new HashSet<>(mongoDocument.getList("account_set", String.class)));
        return document;
    }

    private Document toMongo(final MemberSettingDocument document) {
        if (ObjectUtils.isEmpty(document)) {
            return null;
        }
        if (ObjectUtils.isEmpty(document.getId())) {
            document.setId(customCounterDocumentDao.generatePrimaryKey(getClass().getName()));
        }
        return new Document("_id", document.getId())
                .append("profile_title", document.getProfileTitle())
                .append("font_size", document.getFontSize())
                .append("color", document.getColor())
                .append("date_of_request", document.getDateOfRequest())
                .append("account_set", document.getAccountSet());
    }
}
