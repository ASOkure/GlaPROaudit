package uk.org.bsped.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uk.org.bsped.service.AuditService;

@Component
public class ScheduledTasks
{
  static Logger log = Logger.getLogger(ScheduledTasks.class.getName());
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  @Autowired
  private AuditService auditService;
  @Value("${gh_audit.schedule_task_enabled}")
  private boolean isScheduledTaskEnabled;
  
  @Scheduled(fixedDelayString="${gh_audit.quarterly_email_check_rate:7200}000")
  public void sendQuarterlyEmails()
  {
    if (this.isScheduledTaskEnabled)
    {
      log.info("Creating scheduled quarterly audit requests on " + dateFormat.format(new Date()));
      this.auditService.createNewScheduledAudit();
    }
    else
    {
      log.info("Quarterly audit schedule is switched off.");
    }
  }
  
  @Scheduled(fixedDelayString="${gh_audit.reminder_email_check_rate:7200}000")
  public void sendReminderEmails()
  {
    if (this.isScheduledTaskEnabled)
    {
      log.info("Creating scheduled reminder emails on " + dateFormat.format(new Date()));
      this.auditService.sendReminderEmails();
    }
    else
    {
      log.info("Scheduled is switched off.");
    }
  }
  
  @Scheduled(fixedDelayString="${gh_audit.unsend_email_check_rate:7200}000")
  public void sendUnSentEmails()
  {
    if (this.isScheduledTaskEnabled)
    {
      log.info("Continuing unsent quarterly audit request emails on " + dateFormat.format(new Date()));
      this.auditService.sendUnsentAuditRecords();
    }
    else
    {
      log.info("Scheduled task is switched off.");
    }
  }
}