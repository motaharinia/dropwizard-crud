package com.motaharinia.client.project.config.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectAppConfiguration implements Serializable {
    //app
    @NotEmpty
    private String profile;
    @NotEmpty
    private String name;
    @NotNull
    private int grpcPort;
}
