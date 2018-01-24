package uk.org.bsped.util;

import org.apache.log4j.Logger;

import lombok.extern.log4j.Log4j;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.service.EmailService;

@Log4j
public class EmailTextProcessor {
	
	static Logger log = Logger.getLogger(EmailTextProcessor.class.getName());
    public static String processContent(AuditRecord auditRecord, String text, String url) {
        return text.replace("<USER_FULL_NAME>", getValue(auditRecord.getCentre().getAuditLeadName()))
                .replace("<HOSPITAL_NAME>", getValue(auditRecord.getCentre().getCentreName()))
                .replace("<AUDIT_NAME>", getValue(auditRecord.getAudit().getName()))
                .replace("<AUDIT_URL>", url);
    }

    public static String processSubject(AuditRecord auditRecord, String text) {
        log.info("auditRecord = " + auditRecord);
        return text.replace("<USER_FULL_NAME>", getValue(auditRecord.getCentre().getAuditLeadName()))
                .replace("<HOSPITAL_NAME>", getValue(auditRecord.getCentre().getCentreName()))
                .replace("<AUDIT_NAME>", getValue(auditRecord.getAudit().getName()));
    }

    public static String getValue(String value) {
        return value == null ? "" : value;
    }
}
