package com.motaharinia.client.project.config.swagger;

import org.jetbrains.annotations.NotNull;

public interface TryoutSwaggerApi {
    CustomUser getUserByName(@NotNull String name);
    int getHitCount();
}
