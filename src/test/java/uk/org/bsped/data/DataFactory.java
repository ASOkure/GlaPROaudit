package uk.org.bsped.data;

import uk.org.bsped.model.Audit;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.Centre;

public class DataFactory {

    public static Centre createCentre(Integer id, String name) {
        Centre centre = new Centre();
        centre.setCentreName(name);
        centre.setCentreId(id);
        centre.setAuditLeadName("Audit Lead");
        centre.setAuditLeadEmail("uk.gh.audit@gmail.com");
        centre.setCentreLeadName("Centre Lead");
        centre.setCentreLeadEmail("uk.gh.audit@gmail.com");
        return centre;
    }

    public static Audit createAudit(Integer id, String name, String note) {
        Audit audit = new Audit();
        audit.setAuditId(id);
        audit.setName(name);
        audit.setNote(note);
        return audit;
    }

    public static AuditRecord createAuditRecord(Integer id, Audit audit, Centre centre, AuditRecord.AuditRecordStatus status) {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setAudit(audit);
        auditRecord.setCentre(centre);
        auditRecord.setStatus(status);
        auditRecord.setAuditRecordId(id);
        auditRecord.getAudit().getAuditRecords().add(auditRecord);
        return auditRecord;
    }
}
