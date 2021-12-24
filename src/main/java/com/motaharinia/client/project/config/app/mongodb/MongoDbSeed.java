package com.motaharinia.client.project.config.app.mongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDbSeed implements Serializable {
    /** The host.*/
    private String host;

    /** The port.*/
    private int port;
}
