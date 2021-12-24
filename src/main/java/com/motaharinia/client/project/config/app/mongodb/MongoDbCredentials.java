package com.motaharinia.client.project.config.app.mongodb;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDbCredentials implements Serializable {
    /** The user name.*/
    private String username;

    /** The password.*/
    private String password;
}
