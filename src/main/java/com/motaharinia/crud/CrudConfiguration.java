package com.motaharinia.crud;

import io.dropwizard.Configuration;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CrudConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";
}
