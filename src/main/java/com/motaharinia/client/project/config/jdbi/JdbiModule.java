package com.motaharinia.client.project.config.jdbi;

import com.codahale.metrics.jdbi3.InstrumentedSqlLogger;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;
import com.motaharinia.client.project.config.app.ProjectConfiguration;
import com.motaharinia.client.project.modules.member.persistence.MemberDao;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class JdbiModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Singleton
    public Jdbi configuresDBI(ProjectConfiguration configuration, Environment environment) {
        //تنظیمات دیتابیس
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory
                .build(environment, configuration.getDataSourceFactory(), "mysql")
                .installPlugin(new SqlObjectPlugin());
//                        .setSqlLogger(new Slf4JSqlLogger());
        jdbi.setSqlLogger(new InstrumentedSqlLogger(environment.metrics()));
//        dbi.setSQLLog(new LogbackLog(LOGGER, Level.TRACE));
        return jdbi;

    }

    @Provides
    public MemberDao memberDao(Jdbi jdbi) {
        return jdbi.onDemand(MemberDao.class);
    }

    @Provides
    @RequestScoped
    public Handle handle(Jdbi jdbi) {
        return jdbi.open();
    }
}
