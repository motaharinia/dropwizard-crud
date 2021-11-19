package com.motaharinia.crud.modules.member.persistence;

import com.motaharinia.crud.config.data.CrudDao;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.Optional;

@RegisterBeanMapper(Member.class)
public interface MemberDao extends CrudDao<Member,Long> {
    @SqlQuery
    Optional<Member> findByNationalCode(String nationalCode);
}
