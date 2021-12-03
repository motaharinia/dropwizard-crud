package com.motaharinia.client.project.config.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAppConfiguration {
    //app
    @NotEmpty
    private String profile;
    @NotEmpty
    private String name;
    @NotNull
    private int grpcPort;
}
