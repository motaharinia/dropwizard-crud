package com.motaharinia.client.project.config.swagger;

import org.eclipse.jetty.server.Authentication;
import org.jetbrains.annotations.NotNull;

public interface TryoutSwaggerApi {
    CustomUser getUserByName(@NotNull String name);
    int getHitCount();
}
