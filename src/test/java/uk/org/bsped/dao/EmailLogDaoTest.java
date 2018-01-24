package uk.org.bsped.dao;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.EmailLog;
import uk.org.bsped.web.AppErrorController;

import java.util.List;

@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmailLogDaoTest {
	static Logger log = Logger.getLogger(EmailLogDaoTest.class.getName());

    @Autowired
    EmailLogDao emailLogDao;

    @Test
    public void testFindLatestEmailLog() {
        List<EmailLog> emailLogList = emailLogDao.findLatestEmailLogByAuditRecordId(2704);
        log.info("%% emailLogList size = " + emailLogList.size());
        for (EmailLog el : emailLogList) {
            log.info("%% - " + el.getTimestamp().getTime() + ", id = " + el.getId());
        }
    }

    @Test
    public void testFindEmailLogByAuditId() {
        List<EmailLog> emailLogList = emailLogDao.findEmailLogsByAuditRecordId(2704);
        log.info("%% emailLog size = " + emailLogList.size());
    }

    @Test
    public void testBySubject() {
        List<EmailLog> emailLogList = emailLogDao.findBySubject("TEST");
        log.info("%% - email size = " + emailLogList.size());
        for (EmailLog el : emailLogList) {
            log.info("%% - " + el.getTimestamp().getTime() + ", id = " + el.getId());
        }
    }

}
