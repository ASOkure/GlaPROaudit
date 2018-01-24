package uk.org.bsped.dao;

import uk.org.bsped.model.AuditRecord;

import java.util.List;

public interface AuditRecordDaoExt {

    List<AuditRecord> findAllPendingAuditRecords();

    List<AuditRecord> findAllRequestedAuditRecords();
}
