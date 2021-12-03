package com.motaharinia.client.project.config.data;


import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@UseClasspathSqlLocator
public interface CrudDao<T, Long>{

    @GetGeneratedKeys
    @SqlUpdate
    Long create(@BindBean T entity);

    @SqlQuery
    Optional<T> findById(@Bind("id") Long id);

    @SqlQuery
    List<T> findAll();

    @SqlUpdate
    void update(@BindBean T entity);

    @SqlUpdate
    void delete(@Bind("id") Long id);
}
