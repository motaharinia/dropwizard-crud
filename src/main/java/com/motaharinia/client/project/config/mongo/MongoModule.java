package com.motaharinia.client.project.config.mongo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.motaharinia.client.project.config.app.ProjectConfiguration;
import com.motaharinia.client.project.modules.member.persistence.MemberSettingDocumentDao;
import com.motaharinia.client.project.utility.document.customcounter.CustomCounterDocumentDao;
import io.dropwizard.setup.Environment;

public class MongoModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public MongoClient configuresClient(ProjectConfiguration configuration, Environment environment) {
        //تنظیمات مانگو
        final MongoDbFactoryConnection mongoDBManagerConn = new MongoDbFactoryConnection(configuration.getMongoDBConnection());
        final MongoDbManaged mongoDBManaged = new MongoDbManaged(mongoDBManagerConn.getClient());
        environment.lifecycle().manage(mongoDBManaged);
        return mongoDBManagerConn.getClient();
    }

    @Provides
    @Singleton
    public MongoDatabase configuresDatabase(ProjectConfiguration configuration, MongoClient mongoClient) {
        //دیتابیس پروژه
        return mongoClient.getDatabase(configuration.getMongoDBConnection().getDatabase());
    }

    @Provides
    public CustomCounterDocumentDao customCounterDocumentDao(MongoDatabase mongoDatabase) {
        return new CustomCounterDocumentDao(mongoDatabase.getCollection("custom-counter"));
    }

    @Provides
    public MemberSettingDocumentDao memberSettingDocumentDao(MongoDatabase mongoDatabase, CustomCounterDocumentDao customCounterDocumentDao) {
        return new MemberSettingDocumentDao(mongoDatabase.getCollection("member-setting"),customCounterDocumentDao);
    }
}
