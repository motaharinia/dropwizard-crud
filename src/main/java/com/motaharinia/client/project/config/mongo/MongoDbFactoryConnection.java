package com.motaharinia.client.project.config.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.motaharinia.client.project.config.app.mongodb.MongoDbConnection;
import com.motaharinia.client.project.config.app.mongodb.MongoDbCredentials;
import com.motaharinia.client.project.config.app.mongodb.MongoDbSeed;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MongoDbFactoryConnection {
    /**
     * The configuration for connect to MongoDB Server.
     */
    private final MongoDbConnection mongoDBConnection;

    /**
     * Constructor.
     *
     * @param mongoDBConnection the mongoDB connection data.
     */
    public MongoDbFactoryConnection(final MongoDbConnection mongoDBConnection) {
        this.mongoDBConnection = mongoDBConnection;
    }

    /**
     * Gets the connection to MongoDB.
     *
     * @return the mongo Client.
     */
    public MongoClient getClient() {
        log.info("Creating mongoDB client.");
        final MongoDbCredentials configCredentials = mongoDBConnection.getCredentials();

        final MongoCredential credentials = MongoCredential.createScramSha1Credential(
                configCredentials.getUsername(),
                "admin",
                configCredentials.getPassword().toCharArray());

        return MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credentials)
                        .applyToClusterSettings(builder -> builder.hosts(getServers())).build()
        );
    }

    /**
     * Map the object {@link MongoDbSeed} to objects {@link ServerAddress} that contain the information of servers.
     *
     * @return the list of servers.
     */
    private List<ServerAddress> getServers() {
        final List<MongoDbSeed> seeds = mongoDBConnection.getSeeds();
        return seeds.stream()
                .map(seed -> new ServerAddress(seed.getHost(), seed.getPort()))
                .collect(Collectors.toList());
    }
}
