package uk.org.bsped.dao;

import lombok.extern.log4j.Log4j;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Audit;
import uk.org.bsped.web.AppErrorController;

@Log4j
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
@SpringBootTest
@Transactional
public class AuditDaoTest {

	static Logger log = Logger.getLogger(AuditDaoTest.class.getName());
    @Autowired
    AuditDao auditDao;

    @Test
    public void testFindOne() {
        Audit audit = auditDao.findOne(85);
        log.info("%% audit record size = " + audit.getAuditRecords().size());
//        assertNotNull(audit);
//        assertEquals("TEST", foundEmailTemplate.getName());
    }

}
