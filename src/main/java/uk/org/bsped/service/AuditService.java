package uk.org.bsped.service;

import com.google.common.collect.Sets;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.dao.AuditDao;
import uk.org.bsped.dao.AuditRecordDao;
import uk.org.bsped.dao.CentreDao;
import uk.org.bsped.dao.EmailLogDao;
import uk.org.bsped.dao.UserDao;
import uk.org.bsped.model.Audit;
import uk.org.bsped.model.AuditRecord;
import uk.org.bsped.model.Centre;
import uk.org.bsped.model.EmailLog;
import uk.org.bsped.model.Users;
import uk.org.bsped.util.CalendarHelper;
import uk.org.bsped.util.QuarterUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j
public class AuditService {
	
	static Logger log = Logger.getLogger(AuditService.class.getName());
    @Autowired
    private AuditDao auditDao;
    @Autowired
    private CentreDao centreDao;
    @Autowired
    private AuditRecordDao auditRecordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EmailLogDao emailLogDao;
    @Autowired
    private EmailService emailService;

    @Value("${gh_audit.audit_name_prefix}")
    private String auditNamePrefix;
    @Value("${gh_audit.audit_note}")
    private String auditNote;
    @Value("${gh_audit.reminder_email_interval}")
    private int reminderEmailInterval;
    @Value("${gh_audit.debug_mode}")
    private boolean debug;


    @Transactional
    public void createNewScheduledAudit() {
        Calendar calendar = Calendar.getInstance();
        String previousAuditName = getStandardAuditName(QuarterUtils.getPreviousQuarterName(calendar));
        String currentAuditName = getStandardAuditName(QuarterUtils.getCurrentQuarterName(calendar));
        //check previous quarter
        if (CollectionUtils.isEmpty(auditDao.findByName(previousAuditName))) {
            createNewAudit(previousAuditName, auditNote, "SYSTEM");
        } else {
            log.info("Previous Audit: " + previousAuditName + " has been sent before, ignore this scheduled check");
        }
        //check current quarter and must be on quarter end
        if (CollectionUtils.isEmpty(auditDao.findByName(currentAuditName)) && QuarterUtils.isQuarterEnd(calendar)) {
            createNewAudit(currentAuditName, auditNote, "SYSTEM");
        } else {
            log.info("Current Audit: " + currentAuditName + " has been sent before, ignore this scheduled check");
        }
    }

    private String getStandardAuditName(String quarterName) {
        return auditNamePrefix + " " + quarterName;
    }

    @Transactional
    public Integer createNewAudit(String auditName, String auditNote, String username) {
        Audit audit = createAudit(auditName, auditNote, username);
        Audit latestAudit = auditDao.findOne(audit.getAuditId());
        latestAudit.getAuditRecords().forEach(auditRecord -> {
            emailService.sendEachInitEmail(auditRecord);
            updateAuditRequestStatus(auditRecord);
            pauseInSeconds(20);
        });
        return audit.getAuditId();
    }

    @Transactional
    public void sendUnsentAuditRecords() {
        List<AuditRecord> auditRecords = auditRecordDao.findAllPendingAuditRecords();
        auditRecords.forEach(auditRecord -> {
            emailService.sendEachInitEmail(auditRecord);
            updateAuditRequestStatus(auditRecord);
            pauseInSeconds(20);
        });
    }

    @Transactional
    public void sendReminderEmails() {
        List<AuditRecord> auditRecords = filter(auditRecordDao.findAllRequestedAuditRecords());
        auditRecords.forEach(auditRecord -> {
            emailService.sendEachReminderEmail(auditRecord);
            pauseInSeconds(20);
        });
    }

    private List<AuditRecord> filter(List<AuditRecord> allRequestedAuditRecords) {
        List<AuditRecord> auditRecords = new ArrayList<>();
        for (AuditRecord auditRecord : allRequestedAuditRecords) {
            List<EmailLog> emailLogList = emailLogDao.findLatestEmailLogByAuditRecordId(auditRecord.getAuditRecordId());
            EmailLog emailLog = findLastest(emailLogList);
            if (emailLog != null) {
                int diff = CalendarHelper.calculateDateDifference(emailLog.getTimestamp(), Calendar.getInstance());
                if (diff >= reminderEmailInterval) {
                    auditRecords.add(auditRecord);
                }
            }
        }
        return auditRecords;
    }

    private EmailLog findLastest(List<EmailLog> emailLogList) {
        if (CollectionUtils.isEmpty(emailLogList)) {
            return null;
        }
        emailLogList.sort(new Comparator<EmailLog>() {
            @Override
            public int compare(EmailLog o1, EmailLog o2) {
                return o2.getTimestamp().compareTo(o1.getTimestamp());
            }
        });
        return emailLogList.get(0);
    }

    private void updateAuditRequestStatus(AuditRecord auditRecord) {
        auditRecord.setStatus(AuditRecord.AuditRecordStatus.REQUESTED);
        auditRecord.setRequestTimestamp(Calendar.getInstance());
        auditRecordDao.save(auditRecord);
        log.info("AuditRecord status has been updated! Audit Record ID = " + auditRecord.getAuditRecordId());
    }

    public Audit createAudit(String auditName, String auditNote, String username) {
        Audit audit = new Audit();
        audit.setName(auditName);
        audit.setNote(auditNote);

        Users user = userDao.findUserByUsername(username);
        if (user != null) {
            audit.setCreator(user);
        }
        audit.setCreateTimestamp(Calendar.getInstance());
        audit.setAuditRecords(createAuditRecords(audit));
        audit = auditDao.save(audit);
        return audit;
    }

    private Set<AuditRecord> createAuditRecords(Audit audit) {
        Set<AuditRecord> auditRecordSet = Sets.newLinkedHashSet();
        List<Centre> centres = (List<Centre>) centreDao.findAll();
        auditRecordSet.addAll(centres.stream().map(c -> createAuditRecord(audit, c)).collect(Collectors.toList()));
        return auditRecordSet;
    }

    private AuditRecord createAuditRecord(Audit audit, Centre centre) {
        AuditRecord auditRecord = new AuditRecord();
        auditRecord.setCentre(centre);
        auditRecord.setStatus(AuditRecord.AuditRecordStatus.PENDING);
        auditRecord.setAudit(audit);
        return auditRecord;
    }

    private void pauseInSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            log.error("Interrupted while at sleep.", e);
        }
    }
}
