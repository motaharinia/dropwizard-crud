package com.motaharinia.client.project.config.mongo;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

public class MongoDbManaged implements Managed {
    /**
     * The mongoDB client.
     */
    private final MongoClient mongoClient;

    /**
     * Constructor.
     *
     * @param mongoClient the mongoDB client.
     */
    public MongoDbManaged(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
