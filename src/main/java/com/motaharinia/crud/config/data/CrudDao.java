package com.motaharinia.crud.config.data;

import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@UseClasspathSqlLocator
public interface CrudDao<T, ID> {
    @SqlUpdate
    void save(@BindBean T entity);

    @SqlQuery
    Optional<T> findById(ID id);

    @SqlQuery
    List<T> findAll();

//    @SqlUpdate
//    void update(@BindBean T entity);

    @SqlUpdate
    void delete(ID id);
}
