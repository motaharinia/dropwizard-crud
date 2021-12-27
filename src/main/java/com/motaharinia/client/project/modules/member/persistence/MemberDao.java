package com.motaharinia.client.project.modules.member.persistence;

import com.motaharinia.client.project.config.jdbi.CrudDao;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.vyarus.guicey.jdbi3.installer.repository.JdbiRepository;
import ru.vyarus.guicey.jdbi3.tx.InTransaction;

import java.util.Optional;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس دسترسی داده عضو
 */

//@AllowUnusedBindings
@JdbiRepository
@RegisterBeanMapper(Member.class)
public interface MemberDao extends CrudDao<Member,Long> {
    @SqlQuery
    Optional<Member> findByNationalCode(@Bind("nationalCode") String nationalCode);
}
