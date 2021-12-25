package com.motaharinia.client.project.utility.document.customcounter;

import com.mongodb.client.MongoCollection;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public class CustomCounterDocumentDao {

    final MongoCollection<Document> documentCollection;

    public CustomCounterDocumentDao(MongoCollection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @NotNull
    public CustomCounterDocument save(@NotNull final CustomCounterDocument document) {
        final Document mongoDocument = new Document("_id", document.getId())
                .append("primary_key", document.getPrimaryKey());
        if (ObjectUtils.isEmpty(document.getId())) {
            documentCollection.insertOne(mongoDocument);
        } else {
            documentCollection.updateOne(new Document("_id", document.getId()),  new Document("$set",mongoDocument));
        }
        return document;
    }

    @NotNull
    public Optional<CustomCounterDocument> findById(@NotNull final String id) {
        final Optional<Document> optionalMongoDocument = Optional.ofNullable(documentCollection.find(new Document("_id", id)).first());
        return optionalMongoDocument.map(this::fromMongo);
    }

    private CustomCounterDocument fromMongo(final Document mongoDocument) {
        if (ObjectUtils.isEmpty(mongoDocument)) {
            return null;
        }
        final CustomCounterDocument document = new CustomCounterDocument();
        document.setId(mongoDocument.getString("_id"));
        document.setPrimaryKey(mongoDocument.getLong("primary_key"));
        return document;
    }

    @NotNull
    public Long generatePrimaryKey(@NotNull String documentName) {
        Optional<CustomCounterDocument> customDocumentOptional = this.findById(documentName);
        if (customDocumentOptional.isPresent()) {
            CustomCounterDocument customCounterDocument = customDocumentOptional.get();
            customCounterDocument.setPrimaryKey(customCounterDocument.getPrimaryKey() + 1);
            this.save(customCounterDocument);
            return customCounterDocument.getPrimaryKey();
        } else {
            return this.save(new CustomCounterDocument(documentName, 1L)).getPrimaryKey();
        }
    }
}
