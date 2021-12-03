package com.motaharinia.client.project.config.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectConfiguration extends Configuration {
    //app
    @Valid
    @NotNull
    @JsonProperty
    private ProjectAppConfiguration app;

    //database
    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }


//
//    //grpc
//    @Valid
//    @NotNull
//    private GrpcServerFactory grpcServer = new GrpcServerFactory();
//
//    @JsonProperty("grpcServer")
//    public GrpcServerFactory getGrpcServerFactory() {
//        return grpcServer;
//    }
//
//    @JsonProperty("grpcServer")
//    public void setGrpcServerFactory(final GrpcServerFactory grpcServer) {
//        this.grpcServer = grpcServer;
//    }
}
