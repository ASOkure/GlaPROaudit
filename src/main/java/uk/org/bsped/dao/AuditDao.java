package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Audit;

@Transactional
public interface AuditDao extends CrudRepository<Audit, Integer>, AuditDaoExt {

}