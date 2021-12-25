package com.motaharinia;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Injector;
import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.motaharinia.client.project.config.app.ProjectConfiguration;
import com.motaharinia.client.project.config.jdbi.JdbiModule;
import com.motaharinia.client.project.config.grpc.GrpcServer;
import com.motaharinia.client.project.config.log.rest.*;
import com.motaharinia.client.project.config.mongo.MongoModule;
import com.motaharinia.client.project.config.mvc.MessageServiceImpl;
import com.motaharinia.client.project.config.swagger.TryoutSwaggerApi;
import com.motaharinia.client.project.config.swagger.TryoutSwaggerApiImpl;
import com.motaharinia.client.project.modules.member.presentation.MemberController;
import com.motaharinia.client.project.utility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.client.project.utility.tools.string.MessageService;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.jersey.protobuf.ProtobufBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.server.AbstractServerFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import ru.vyarus.dropwizard.guice.injector.lookup.InjectorLookup;
import ru.vyarus.dropwizard.guice.injector.lookup.InjectorProvider;
import ru.vyarus.dropwizard.guice.module.installer.feature.jersey.ResourceInstaller;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Optional;


//https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/
//https://medium.com/swlh/how-to-design-restful-web-services-with-dropwizard-d5681a127cba
//https://readthedocs.org/projects/dropwizard/downloads/pdf/latest/

//---------- server types in yml
//default (separate ports for application and admin connectors)
//simple (both connectors on the same port).

//---------- pom maven:
//https://github.com/soabase/soabase/blob/master/pom.xml
//https://www.programcreek.com/java-api-examples/?code=soabase%2Fsoabase%2Fsoabase-master%2Fsoabase-jdbi%2Fsrc%2Ftest%2Fjava%2Fio%2Fsoabase%2Fjdbi%2Fattributes%2FTestJdbiDynamicAttributes.java#
//https://maven.apache.org/plugin-developers/cookbook/attach-source-javadoc-artifacts.html

//---------- jdbi3:
//https://jdbi.org/#_core_api
//https://github.com/Manikandan-K/jdbi-folder
//https://stackoverflow.com/questions/44769057/upgrading-dropwizard-jdbi-to-jdbi-3
//https://github.com/gabrielSpassos/dropwizard-jdbi-mysql
//https://www.tabnine.com/web/assistant/code/rs/5c785bd8df79be0001e5cc22#L100
//https://github.com/MarquezProject/marquez/blob/a315111a6572983309547f10825b8fc277d2f612/api/src/main/java/marquez/MarquezApp.java
//https://www.tabnine.com/code/java/methods/org.skife.jdbi.v2.DBI/setSQLLog
//https://www.programcreek.com/java-api-examples/?api=org.jdbi.v3.core.Jdbi
//https://www.tabnine.com/code/java/methods/io.dropwizard.jdbi.logging.LogbackLog/%3Cinit%3E

//---------- jdbi3-transaction:
//http://manikandan-k.github.io/2015/05/10/Transactions_in_jdbi.html
//https://groups.google.com/g/jdbi/c/S1guX6DLNXQ
//DBI.onDemand produces a DAO where every method call allocates a connection, runs a statement, and releases the connection (usually back to a pool). So effectively every method call is a distinct transaction, so that is not what you want.
//https://xvik.github.io/dropwizard-guicey/4.2.2/extras/jdbi3/
//https://xvik.github.io/dropwizard-guicey/4.2.2/examples/jdbi3/

//---------- hikaricp:
//https://github.com/mtakaki/dropwizard-hikaricp
//https://nickb.dev/blog/the-difficulty-of-performance-evaluation-of-hikaricp-in-dropwizard
//https://gist.github.com/maji-KY/646f202cacac855cd8da

//---------- liquibase:
//https://junctionbox.ca/2013/05/10/dropwizard-liquibase-migrations.html

//---------- mapstruct:
//https://mapstruct.org/documentation/stable/reference/html/#using-dependency-injection
//https://github.com/saurabh-86/dropwizard-basic

//---------- logging:
//https://github.com/dropwizard/dropwizard/blob/master/dropwizard-logging/src/test/resources/yaml/logging.yml
//https://alvinalexander.com/java/jwarehouse/deeplearning4j/deeplearning4j-scaleout/deeplearning4j-scaleout-akka/src/main/resources/hazelcast/dropwizard.yml.shtml

//---------- resource exception handling:
//https://stackoverflow.com/questions/41192968/how-can-i-override-dropwizards-default-resource-exception-handling

//---------- i18n-internationalization:
//https://programmerall.com/article/8096310810/
//https://titanwolf.org/Network/Articles/Article?AID=25095f99-5c5a-465d-b08d-5a26314c7c88

//---------- grpc:
//https://github.com/dropwizard/dropwizard-protobuf
//https://github.com/msteinhoff/dropwizard-grpc
//https://github.com/tburch/dropwizard-grpc
//https://developers.google.com/protocol-buffers/docs/reference/java-generated
//https://github.com/grpc/grpc-java
//https://github.com/os72/protoc-jar-maven-plugin
//https://github.com/os72/protoc-jar-maven-plugin/issues/20
//https://github.com/protocolbuffers/protobuf/tree/master/java
//https://www.baeldung.com/google-protocol-buffer
//https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/routeguide/RouteGuideServer.java
//https://www.baeldung.com/grpc-introduction

//---------- guice (injection):
//https://github.com/google/guice
//https://dzone.com/articles/dropwizard-and-guice
//https://javaee.github.io/hk2/guice-bridge.html
//https://xvik.github.io/dropwizard-guicey/4.0.1/getting-started/

//---------- swagger3 (openapi):
//https://github.com/lxucs/Tryout-Swagger

//---------- mongodb:
//https://dev.to/ricdev2/building-microservices-with-dropwizard-mongodb--docker--o30
//https://github.com/ricdev2/dropwizard-mongodb-ms
//https://mongodb.github.io/mongo-java-driver/3.9/driver/tutorials/authentication/

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس اصلی اجرای اپلیکیشن
 */

@Slf4j
public class CrudApplication extends Application<ProjectConfiguration> {

    private GuiceBundle guiceBundle;
    private static Injector applicationInjector;
    private InjectorProvider provider;

    public static Injector getApplicationInjector() {
        return applicationInjector;
    }

    private static void setApplicationInjector(Injector applicationInjector) {
        CrudApplication.applicationInjector = applicationInjector;
    }

    public static void main(String[] args) throws Exception {
        new CrudApplication().run(args);
//        new CrudApplication().run("server", "crud-dev.yml");
    }

    @Override
    public String getName() {
        return "CRUD Application";
    }

    @Override
    public void initialize(Bootstrap<ProjectConfiguration> bootstrap) {
        System.out.println("---------->>>>>>>>>> CrudApplication.initialize");
        provider = new InjectorProvider(this);

        //تنظیمات OpenApi swagger
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(final ProjectConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });

        //تنظیمات liquibase
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ProjectConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public String getMigrationsFileName() {
                return "liquibase.xml";
            }
        });

        //تنظیمات اکسپشن دیتابیس
        // By adding the JdbiExceptionsBundle to your application, Dropwizard will automatically unwrap
        // ant thrown SQLException or DBIException instances. This is critical for debugging, since
        // otherwise only the common wrapper exception’s stack trace is logged.
        bootstrap.addBundle(new JdbiExceptionsBundle());
        //تنظیمات GRPC
        bootstrap.addBundle(new ProtobufBundle<>());
        //تنظیمات آپلود فایل
        bootstrap.addBundle(new MultiPartBundle());

        //تنظیمات جکسون
        bootstrap.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        bootstrap.getObjectMapper().setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        bootstrap.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        bootstrap.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //guice (injection)
        guiceBundle = GuiceBundle.builder()
                .installers(ResourceInstaller.class)
                .extensions(MemberController.class)
                .modules(new JdbiModule(),new MongoModule())
                .build();
        bootstrap.addBundle(guiceBundle);

//        bootstrap.addBundle(new CorsBundle());
//        bootstrap.addBundle(new TemplateConfigBundle());
//        bootstrap.addBundle(new RateLimitBundle(new InMemoryRateLimiterFactory()));
//        bootstrap.addBundle(new CustomizeSwaggerBundle());

    }

    @Override
    public void run(ProjectConfiguration configuration, Environment environment) throws IOException, InterruptedException {
        log.info("\n---------->>>>>>>>>> CrudApplication.run");
        //سرویس ترجمه
        MessageService messageService = registerMessageSource(environment.jersey());
        //تنظیم مدیریت یکپارچه خطاها
        registerLog(environment.jersey(), configuration, messageService);
        //تنظیمات OpenApi3 (swagger)
        registerOpenApi(environment.jersey());
        //تنظیم کلاسهای داده و ریسورس و grpc
        registerServices(configuration, environment);

        setApplicationInjector(provider.get());
    }


    /**
     * متد تنظیم کننده سرویس ترجمه
     *
     * @param jersey شیی محیط Jersey
     * @return خروجی: سرویس ترجمه
     */
    private MessageService registerMessageSource(JerseyEnvironment jersey) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("lang/businessexception", "lang/comboitem", "lang/customvalidation", "lang/exception", "lang/notification", "lang/usermessage");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        jersey.register(messageSource);
        return new MessageServiceImpl(messageSource);
    }


    /**
     * متد تنظیم کننده OpenApi3 - Swagger
     *
     * @param jersey شیی محیط Jersey
     */
    private void registerOpenApi(JerseyEnvironment jersey) {
        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(TryoutSwaggerApiImpl.class).to(TryoutSwaggerApi.class).in(Singleton.class);
            }
        });
    }


    /**
     * متد تنظیم کننده لاگ خطاها
     *
     * @param jersey        شیی محیط Jersey
     * @param configuration مدل تنظیمات
     */
    private void registerLog(JerseyEnvironment jersey, ProjectConfiguration configuration, MessageService messageService) {
        ServerFactory serverFactory = configuration.getServerFactory();
        //ست کردن پورت در کلاس تنظیمات
        int appPort = 0;
        for (ConnectorFactory connector : ((DefaultServerFactory) serverFactory).getApplicationConnectors()) {
            if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
                appPort = ((HttpConnectorFactory) connector).getPort();
                break;
            }
        }

        ExceptionDto exceptionDto = new ExceptionDto(configuration.getApp().getProfile(), configuration.getApp().getName(), String.valueOf(appPort));
        log.info("\n================================\nApp-Profile:{}, App-Name:{}, App-Port:{}\n================================ ", exceptionDto.getAppProfile(), exceptionDto.getAppName(), exceptionDto.getAppPort());
        ((AbstractServerFactory) serverFactory).setRegisterDefaultExceptionMappers(false);
        jersey.register(new RestBusinessExceptionTranslator(exceptionDto, messageService));
        jersey.register(new RestConstraintViolationExceptionTranslator(exceptionDto, messageService));
        jersey.register(new RestExternalCallExceptionTranslator(exceptionDto, messageService));
        jersey.register(new RestRateLimitExceptionTranslator(exceptionDto, messageService));
        jersey.register(new RestGeneralExceptionTranslator(exceptionDto));
    }


    /**
     * متد تنظیم کننده دیتابیس و سرویسها
     *
     * @param configuration مدل تنظیمات
     * @param environment   شیی محیط برنامه
     * @throws IOException خطا
     */
    private void registerServices(ProjectConfiguration configuration, Environment environment) throws IOException {
        //تنظیمات دیتابیس
//        final JdbiFactory factory = new JdbiFactory();
//        final Jdbi jdbi = factory
//                .build(environment, configuration.getDataSourceFactory(), "mysql")
//                .installPlugin(new SqlObjectPlugin());
////                        .setSqlLogger(new Slf4JSqlLogger());
//        jdbi.setSqlLogger(new InstrumentedSqlLogger(environment.metrics()));
////        dbi.setSQLLog(new LogbackLog(LOGGER, Level.TRACE));
//
//        //تعریف کلاسهای مدیریت دسترسی داده
//        final MemberDao memberDao = jdbi.onDemand(MemberDao.class);
//
//        //تعریف کلاسهای سرویس
//        final MemberService memberService = new MemberServiceImpl(memberDao, memberSettingService);
//
//        //تعریف کلاسهای کنترلر
//        environment.jersey().register(new MemberController(memberService));

        //تنظیمات grpc
        Optional<GrpcServer> grpcServerOptional = InjectorLookup.getInstance(this, GrpcServer.class);
        if( grpcServerOptional.isPresent()){
            grpcServerOptional.get().start();
//            grpcServerOptional.get().blockUntilShutdown();
        }
    }
}
