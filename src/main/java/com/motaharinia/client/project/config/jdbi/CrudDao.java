package com.motaharinia.client.project.config.jdbi;


import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository;

import java.util.List;
import java.util.Optional;

@JdbiRepository
@UseClasspathSqlLocator
public interface CrudDao<T, L>{

    @GetGeneratedKeys
    @SqlUpdate
    L create(@BindBean T entity);

    @SqlQuery
    Optional<T> findById(@Bind("id") L id);

    @SqlQuery
    List<T> findAll();

    @SqlUpdate
    void update(@BindBean T entity);

    @SqlUpdate
    void delete(@Bind("id") L id);
}
