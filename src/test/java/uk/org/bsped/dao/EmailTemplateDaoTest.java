package uk.org.bsped.dao;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.EmailTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Log4j
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
@SpringBootTest
@Transactional
public class EmailTemplateDaoTest {

    @Autowired
    EmailTemplateDao emailTemplateDao;

    @Test
    public void testFindOne() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setName("TEST");

        EmailTemplate savedEmailTemplate = emailTemplateDao.save(emailTemplate);
        EmailTemplate foundEmailTemplate = emailTemplateDao.findOne(emailTemplate.getId());
        assertNotNull(foundEmailTemplate);
        assertEquals("TEST", foundEmailTemplate.getName());
    }

    @Test
    public void testFindByName() {
        EmailTemplate emailTemplate = new EmailTemplate();
        emailTemplate.setName("TEST_NAME");

        EmailTemplate savedEmailTemplate = emailTemplateDao.save(emailTemplate);
        EmailTemplate foundEmailTemplate = emailTemplateDao.findEmailTemplateByName("TEST_NAME");
        assertNotNull(foundEmailTemplate);
        assertEquals(savedEmailTemplate.getId(), foundEmailTemplate.getId());
    }
}
