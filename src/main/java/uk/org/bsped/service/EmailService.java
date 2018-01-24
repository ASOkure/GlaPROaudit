package uk.org.bsped.service;



import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import uk.org.bsped.dao.EmailLogDao;
import uk.org.bsped.dao.EmailTemplateDao;
import uk.org.bsped.dao.UserDao;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.EmailLog;
import uk.org.bsped.model.EmailTemplate;
import uk.org.bsped.velocity.AbstractAuditEmailPreparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service

public class EmailService {
	
	static Logger log = Logger.getLogger(EmailService.class.getName());
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private EmailTemplateDao emailTemplateDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EmailLogDao emailLogDao;
    @Autowired
    private VelocityEngine velocityEngine;
    @Value("${gh_audit.debug_mode}")
    private boolean isDebug;
    @Value("${gh_audit.reply_url}")
    private String replyUrl;
    @Value("${gh_audit.reply_email}")
    private String replyEmail;
    @Value("${gh_audit.cc_emails}")
    private String ccEmails;
    @Value("${gh_audit.email_title}")
    private String emailTitle;

    public void sendBulkInitEmails(List<AuditRecord> auditRecords) {
    	
        log.info("Debug mode? " + isDebug);
        List<MimeMessagePreparator> preparatorList = new ArrayList<>();
        for (final AuditRecord auditRecord : auditRecords) {
            log.info("AuditRecord : " + auditRecord);
            preparatorList.add(getInitEmailMsgPreparator(auditRecord));
        }
        MimeMessagePreparator[] preparatorArray = preparatorList.toArray(new MimeMessagePreparator[preparatorList.size()]);
        log.info("Message size = " + preparatorArray.length);
        mailSender.send(preparatorArray);
    }

    public void sendEachInitEmail(AuditRecord auditRecord) {
        MimeMessagePreparator preparator = getInitEmailMsgPreparator(auditRecord);
       
        	mailSender.send(preparator);
        logEmailHistory(auditRecord, preparator);
      
    }

    public void sendEachReminderEmail(AuditRecord auditRecord) {
        MimeMessagePreparator preparator = getReminderEmailMsgPreparator(auditRecord);
        mailSender.send(preparator);
        logEmailHistory(auditRecord, preparator);
    }

    private void logEmailHistory(AuditRecord auditRecord, MimeMessagePreparator preparator) {
        EmailLog emailLog = new EmailLog();
        emailLog.setTimestamp(Calendar.getInstance());
        emailLog.setCreator(userDao.findUserByUsername("SYSTEM"));
        emailLog.setCounter(emailLog.getCounter() == null ? 1 : (emailLog.getCounter() + 1));
        emailLog.setAuditRecordId(auditRecord.getAuditRecordId());
        AbstractAuditEmailPreparator abstractAuditEmailPreparator = (AbstractAuditEmailPreparator) preparator;
        emailLog.setContent(abstractAuditEmailPreparator.getContent(auditRecord));
        emailLog.setSubject(abstractAuditEmailPreparator.getSubject(auditRecord));
        emailLog = emailLogDao.save(emailLog);
        log.info("Email being logged with emailLogId = " + emailLog.getId());
    }

    private MimeMessagePreparator getInitEmailMsgPreparator(final AuditRecord auditRecord) {
        return new AbstractAuditEmailPreparator(auditRecord, "velocity/init_audit_invite.vm", isDebug, replyUrl, replyEmail, ccEmails, emailTitle, velocityEngine);
    }

    private MimeMessagePreparator getReminderEmailMsgPreparator(final AuditRecord auditRecord) {
        return new AbstractAuditEmailPreparator(auditRecord, "velocity/audit_reminder.vm", isDebug, replyUrl, replyEmail, ccEmails, emailTitle, velocityEngine);
    }

   /* public void sendEmail(String address, String subject, String content) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(address);
        mail.setFrom("uk.gh.audit@gmail.com");
        mail.setSubject(subject);
        mail.setText(content);
        mailSender.send(mail);
    }*/

    protected EmailTemplate getEmailTemplate(String name) {
        return emailTemplateDao.findEmailTemplateByName(name);
    }

}
