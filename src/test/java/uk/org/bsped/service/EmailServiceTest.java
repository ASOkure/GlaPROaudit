package uk.org.bsped.service;

import com.google.common.collect.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.data.DataFactory;
import uk.org.bsped.model.Audit;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.Centre;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Ignore("Must ignore to avoid sending emails during unit tests")
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void can_send_bulk_email() {
        AuditRecord auditRecord = createAuditRecord();
        emailService.sendBulkInitEmails(Lists.newArrayList(auditRecord));
    }

    @Test
    public void can_send_email() {
        emailService.sendEachInitEmail(createAuditRecord());
    }

    private AuditRecord createAuditRecord() {
        Audit audit = DataFactory.createAudit(1, "GH Audit 2016 Q4", "Test");
        Centre centre = DataFactory.createCentre(1, "ABC Hospital");
        AuditRecord auditRecord = DataFactory.createAuditRecord(1, audit, centre, AuditRecord.AuditRecordStatus.PENDING);
        return auditRecord;
    }

}
