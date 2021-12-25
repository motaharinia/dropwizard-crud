package com.motaharinia.client.project.config.grpc;

import com.google.inject.Inject;
import com.motaharinia.client.project.config.app.ProjectConfiguration;
import com.motaharinia.client.project.modules.member.business.service.MemberService;
import com.motaharinia.client.project.modules.member.rpc.server.MemberFindByIdGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import ru.vyarus.dropwizard.guice.injector.lookup.InjectorLookup;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcServer {

    private final int port;
    private final Server server;

    @Inject
    public GrpcServer(ProjectConfiguration configuration,  MemberService memberService) {
        this.port = configuration.getApp().getGrpcPort();
        this.server = ServerBuilder.forPort(port)
                .addService(new MemberFindByIdGrpcImpl(memberService))
                .build();
    }

    /**
     * Start serving requests.
     */
    public void start() throws IOException {
        server.start();
        log.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            log.error("*** shutting down gRPC server since JVM is shutting down");
            try {
                GrpcServer.this.stop();
            } catch (InterruptedException exception) {
                log.error("*** shutting down gRPC server exception:",exception);
                Thread.currentThread().interrupt();
            }
            log.error("*** server shut down");
        }));
    }

    /**
     * Stop serving requests and shutdown resources.
     */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
