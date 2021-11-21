package com.motaharinia.crud;

import com.codahale.metrics.jdbi3.InstrumentedSqlLogger;
import com.motaharinia.crud.modules.member.business.service.MemberService;
import com.motaharinia.crud.modules.member.business.service.MemberServiceImpl;
import com.motaharinia.crud.modules.member.persistence.MemberDao;
import com.motaharinia.crud.modules.member.presentation.MemberController;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.jdbi3.bundles.JdbiExceptionsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


//https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/
//https://medium.com/swlh/how-to-design-restful-web-services-with-dropwizard-d5681a127cba
//https://readthedocs.org/projects/dropwizard/downloads/pdf/latest/

//---------- pom maven
//https://github.com/soabase/soabase/blob/master/pom.xml
//https://www.programcreek.com/java-api-examples/?code=soabase%2Fsoabase%2Fsoabase-master%2Fsoabase-jdbi%2Fsrc%2Ftest%2Fjava%2Fio%2Fsoabase%2Fjdbi%2Fattributes%2FTestJdbiDynamicAttributes.java#
//https://maven.apache.org/plugin-developers/cookbook/attach-source-javadoc-artifacts.html

//---------- jdbi3
//https://jdbi.org/#_core_api
//https://github.com/Manikandan-K/jdbi-folder
//https://stackoverflow.com/questions/44769057/upgrading-dropwizard-jdbi-to-jdbi-3
//https://github.com/gabrielSpassos/dropwizard-jdbi-mysql
//https://www.tabnine.com/web/assistant/code/rs/5c785bd8df79be0001e5cc22#L100
//https://github.com/MarquezProject/marquez/blob/a315111a6572983309547f10825b8fc277d2f612/api/src/main/java/marquez/MarquezApp.java
//https://www.tabnine.com/code/java/methods/org.skife.jdbi.v2.DBI/setSQLLog
//https://www.programcreek.com/java-api-examples/?api=org.jdbi.v3.core.Jdbi
//https://www.tabnine.com/code/java/methods/io.dropwizard.jdbi.logging.LogbackLog/%3Cinit%3E

//---------- liquibase
//https://junctionbox.ca/2013/05/10/dropwizard-liquibase-migrations.html

//---------- mapstruct
//https://mapstruct.org/documentation/stable/reference/html/#using-dependency-injection
//https://github.com/saurabh-86/dropwizard-basic

//---------- logging
//https://github.com/dropwizard/dropwizard/blob/master/dropwizard-logging/src/test/resources/yaml/logging.yml
//https://alvinalexander.com/java/jwarehouse/deeplearning4j/deeplearning4j-scaleout/deeplearning4j-scaleout-akka/src/main/resources/hazelcast/dropwizard.yml.shtml
//

/**
 * Hello world!
 *
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
    public void run(CrudConfiguration configuration, Environment environment) throws Exception {
        log.info("--------------- {}", configuration.getDefaultName());
        log.info("--------------- {}", configuration.getTemplate());

        log.info("Registering REST resources");

        //wrapper ایجاد سازنده و
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory
                        .build(environment, configuration.getDataSourceFactory(), "mysql")
                        .installPlugin(new SqlObjectPlugin());
//                        .setSqlLogger(new Slf4JSqlLogger());
        jdbi.setSqlLogger(new InstrumentedSqlLogger(environment.metrics()));
//        dbi.setSQLLog(new LogbackLog(LOGGER, Level.TRACE));

        //تعریف کلاسهای مدیریت دسترسی داده
        final MemberDao memberDao = jdbi.onDemand(MemberDao.class);

        //تعریف کلاسهای سرویس
        final MemberService memberService = new MemberServiceImpl(memberDao);

        //تعریف کلاسهای کنترلر
        environment.jersey().register(new MemberController(environment.getValidator() , memberService));
    }

    public static void main(String[] args) throws Exception {
        new CrudApplication().run(args);
//        new CrudApplication().run("server", "crud.yml");
    }
}
