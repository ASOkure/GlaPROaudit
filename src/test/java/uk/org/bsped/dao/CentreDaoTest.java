package uk.org.bsped.dao;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.org.bsped.model.Centre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Log4j
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
@SpringBootTest
@Transactional
public class CentreDaoTest {

    @Autowired
    CentreDao centreDao;

    @Test
    public void test() {
        Centre centre = new Centre();
        centre.setCentreName("Test Centre");
        Centre savedCentre = centreDao.save(centre);
        Centre foundCentre = centreDao.findOne(savedCentre.getCentreId());
        assertNotNull(foundCentre);
        assertEquals("Test Centre", foundCentre.getCentreName());
    }
}
