package com.motaharinia.crud;

import com.codahale.metrics.jdbi3.InstrumentedSqlLogger;
import com.miao.easyi18n.support.ResourceBundleMessageSource;
import com.motaharinia.crud.config.log.rest.*;
import com.motaharinia.crud.config.mvc.MessageServiceImpl;
import com.motaharinia.crud.modules.member.business.service.MemberService;
import com.motaharinia.crud.modules.member.business.service.MemberServiceImpl;
import com.motaharinia.crud.modules.member.persistence.MemberDao;
import com.motaharinia.crud.modules.member.presentation.MemberController;
import com.motaharinia.crud.utility.custom.customdto.exception.ExceptionDto;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.jetty.ConnectorFactory;
import io.dropwizard.jetty.HttpConnectorFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.server.AbstractServerFactory;
import io.dropwizard.server.DefaultServerFactory;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


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


/**
 * Hello world!
 */

@Slf4j
public class CrudApplication extends Application<CrudConfiguration> {

    @Override
    public void initialize(Bootstrap<CrudConfiguration> bootstrap) {
        //liquibase
        bootstrap.addBundle(new MigrationsBundle<>() {
            @Override
            public DataSourceFactory getDataSourceFactory(CrudConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public String getMigrationsFileName() {
                return "liquibase.xml";
            }
        });

        // By adding the JdbiExceptionsBundle to your application, Dropwizard will automatically unwrap
        // ant thrown SQLException or DBIException instances. This is critical for debugging, since
        // otherwise only the common wrapper exception’s stack trace is logged.
        bootstrap.addBundle(new JdbiExceptionsBundle());

//        bootstrap.addBundle(new CorsBundle());
//        bootstrap.addBundle(new RateLimitBundle(new InMemoryRateLimiterFactory()));
    }

    @Override
    public void run(CrudConfiguration configuration, Environment environment) {


        log.info("Registering REST resources");

        //wrapper ایجاد سازنده و
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory
                .build(environment, configuration.getDataSourceFactory(), "mysql")
                .installPlugin(new SqlObjectPlugin());
//                        .setSqlLogger(new Slf4JSqlLogger());
        jdbi.setSqlLogger(new InstrumentedSqlLogger(environment.metrics()));
//        dbi.setSQLLog(new LogbackLog(LOGGER, Level.TRACE));


        //سرویس ترجمه
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("lang/businessexception", "lang/comboitem", "lang/customvalidation", "lang/exception", "lang/notification", "lang/usermessage");
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        environment.jersey().register(messageSource);


        ServerFactory serverFactory = configuration.getServerFactory();
        //ست کردن پورت در کلاس تنظیمات
        int appPort = 0;
        for (ConnectorFactory connector : ((DefaultServerFactory) serverFactory).getApplicationConnectors()) {
            if (connector.getClass().isAssignableFrom(HttpConnectorFactory.class)) {
                appPort = ((HttpConnectorFactory) connector).getPort();
                break;
            }
        }
        ExceptionDto exceptionDto = new ExceptionDto(configuration.getAppProfile(),configuration.getAppName(),String.valueOf(appPort));
        log.info("\n================================\nApp-Profile:{}, App-Name:{}, App-Port:{}\n================================ ", exceptionDto.getAppProfile(), exceptionDto.getAppName(), exceptionDto.getAppPort());
        //تنظیم مدیریت یکپارچه خطاها
        ((AbstractServerFactory) serverFactory).setRegisterDefaultExceptionMappers(false);
        environment.jersey().register(new RestBusinessExceptionTranslator(exceptionDto,new MessageServiceImpl(messageSource)));
        environment.jersey().register(new RestConstraintViolationExceptionTranslator(exceptionDto,new MessageServiceImpl(messageSource)));
        environment.jersey().register(new RestExternalCallExceptionTranslator(exceptionDto,new MessageServiceImpl(messageSource)));
        environment.jersey().register(new RestRateLimitExceptionTranslator(exceptionDto,new MessageServiceImpl(messageSource)));
        environment.jersey().register(new RestGeneralExceptionTranslator(exceptionDto));

        //تعریف کلاسهای مدیریت دسترسی داده
        final MemberDao memberDao = jdbi.onDemand(MemberDao.class);

        //تعریف کلاسهای سرویس
        final MemberService memberService = new MemberServiceImpl(memberDao);

        //تعریف کلاسهای کنترلر
        environment.jersey().register(new MemberController(memberService));
    }

    public static void main(String[] args) throws Exception {
        new CrudApplication().run(args);
//        new CrudApplication().run("server", "crud-dev.yml");
    }
}
