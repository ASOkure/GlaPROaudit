package uk.org.bsped.velocity;

import com.google.common.collect.Sets;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;
import uk.org.bsped.model.AuditRecord;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Log4j
public class AbstractAuditEmailPreparator implements MimeMessagePreparator {

	  static Logger log = Logger.getLogger(AbstractAuditEmailPreparator.class.getName());

    AuditRecord auditRecord;
    String templateName;
    boolean isDebug;
    String replyUrl;
    String replyEmail;
    String ccEmails;
    String emailTitle;
    VelocityEngine velocityEngine;

    public AbstractAuditEmailPreparator(AuditRecord auditRecord, String templateName,
                                        boolean isDebug,
                                        String replyUrl,
                                        String replyEmail,
                                        String ccEmails,
                                        String emailTitle,
                                        VelocityEngine velocityEngine) {
        this.auditRecord = auditRecord;
        this.templateName = templateName;
        this.isDebug =isDebug;
        this.replyUrl = replyUrl;
        this.replyEmail = replyEmail;
        this.ccEmails = ccEmails;
        this.emailTitle = emailTitle;
        this.velocityEngine = velocityEngine;
    }

    @Override
    public void prepare(MimeMessage mimeMessage) throws Exception {
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        
        String msgTo = "";
        Boolean processEmail = false;
        if(isDebug && replyEmail != null && !replyEmail.isEmpty()){
        	msgTo = replyEmail;
        	processEmail = true;
        	 log.info("msgTo1 = " + msgTo);
        }else if(!isDebug && auditRecord.getCentre().getAuditLeadEmail() != null &&  !auditRecord.getCentre().getAuditLeadEmail().trim().isEmpty()){
        	msgTo = auditRecord.getCentre().getAuditLeadEmail();
        	 log.info("msgTo2 = " + msgTo);
        	processEmail = true;
        }else {
        	msgTo = replyEmail; //this applies when any leademail is empty or null, it send the email to the replyemail address this should not be null;
        	processEmail = true;
        }
       if(processEmail){
    	   log.info("Debug = " + isDebug);
           log.info("prepare = " + (isDebug ? replyEmail : auditRecord.getCentre().getAuditLeadEmail()));
    	   message.setTo(msgTo);
    	   message.setFrom(replyEmail);
        //add on the cc
    	   addOnCc(message, auditRecord);
    	   message.setSubject(getSubject(auditRecord));
    	   message.setText(getContent(auditRecord), true);
       }
    }

    public String getContent(AuditRecord auditRecord) {
        Map model = new HashMap();
        model.put("auditRecord", auditRecord);
        model.put("replyUrl", getUrl(auditRecord.getAuditRecordId()));
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, model);
    }

    public String getSubject(AuditRecord auditRecord) {
        return emailTitle + auditRecord.getAudit().getName();
    }

    private void addOnCc(MimeMessageHelper message, AuditRecord auditRecord) throws MessagingException {
        if (isDebug) {
            log.info("Debug mode, sending to reply email address only: " + replyEmail);
            message.setCc(replyEmail);
            return;
        }
        Set<String> ccSet = Sets.newHashSet();
        if (StringUtils.isNotBlank(ccEmails)) {
            ccSet.addAll(Sets.newHashSet(ccEmails.split(";")));
        }
        if (auditRecord != null && auditRecord.getCentre() != null && auditRecord.getCentre().getCentreLeadEmail() != null
        		&& !auditRecord.getCentre().getCentreLeadEmail().trim().isEmpty()) {
            ccSet.add(isDebug ? replyEmail : auditRecord.getCentre().getCentreLeadEmail());
        }

        if (CollectionUtils.isNotEmpty(ccSet)) {
            message.setCc(ccSet.toArray(new String[ccSet.size()]));
        }
    }

    public String getUrl(Integer id) {
        return replyUrl + id;
    }
}
