package com.motaharinia.client.project.config.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser {
    @NotNull(message = "uuid can not be null")
    private UUID uuid;

    @NotNull(message = "name can not be null")
    private String name;

    private int age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Instant lastUpdate;
}
