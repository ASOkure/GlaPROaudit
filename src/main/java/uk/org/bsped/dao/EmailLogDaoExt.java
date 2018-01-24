package uk.org.bsped.dao;

import uk.org.bsped.model.EmailLog;

import java.util.List;

public interface EmailLogDaoExt {

    List<EmailLog> findLatestEmailLogByAuditRecordId(Integer auditRecordId);

    List<EmailLog> findEmailLogsByAuditRecordId(Integer auditRecordId);

    List<EmailLog> findBySubject(String subject);


}
