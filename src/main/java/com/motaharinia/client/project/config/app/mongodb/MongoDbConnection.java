package com.motaharinia.client.project.config.app.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDbConnection implements Serializable {
    /**
     * The credentials user and password.
     */
    private MongoDbCredentials credentials;

    /**
     * The lis of seeds.
     */
    private List<MongoDbSeed> seeds;

    /**
     * The db.
     */
    private String database;
}
