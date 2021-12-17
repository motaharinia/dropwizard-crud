package com.motaharinia.client.project.config.swagger;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class TryoutSwaggerApiImpl implements TryoutSwaggerApi {

    private final AtomicInteger counter;

    public TryoutSwaggerApiImpl() {
        counter = new AtomicInteger(0);
    }

    @Override
    public CustomUser getUserByName(@NotNull String name) {
        counter.incrementAndGet();

        return CustomUser.builder()
                .uuid(UUID.randomUUID())
                .name(name)
                .age(ThreadLocalRandom.current().nextInt(100))
                .lastUpdate(Instant.now())
                .build();
    }

    @Override
    public int getHitCount() {
        return counter.get();
    }

}
