package uk.org.bsped.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.EmailLog;

@Transactional
public interface EmailLogDao extends CrudRepository<EmailLog, Integer>, EmailLogDaoExt {

}