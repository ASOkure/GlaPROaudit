package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.AuditRecord;

@Transactional
public interface AuditRecordDao extends CrudRepository<AuditRecord, Integer>, AuditRecordDaoExt {

}