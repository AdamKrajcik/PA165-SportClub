package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;


/**
 * Tests for CoachDaoImpl
 *
 * @author 410461 Martin Skrovina
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CoachDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private CoachDao coachDao;


    @Test
    public void testCreate() {
        Coach coach = new Coach();
        coach.setFirstName("Firstname");
        coach.setLastName("Lastname");
        coach.setEmail("lastname@example.com");

        coachDao.create(coach);

        Assert.assertEquals(coachDao.findById(coach.getId()), coach);
    }

    @Test
    public void testUpdate() {
        Coach coach = new Coach();
        coach.setFirstName("Firstname");
        coach.setLastName("Lastname");
        coach.setEmail("lastname@example.com");

        coachDao.create(coach);
        coach.setEmail("firstname@example.com");
        coachDao.update(coach);

        Assert.assertEquals(coachDao.findById(coach.getId()).getEmail(), coach.getEmail());
    }

    @Test
    public void testDelete() {
        Coach coach = new Coach();
        coach.setFirstName("Firstname");
        coach.setLastName("Lastname");
        coach.setEmail("lastname@example.com");

        coachDao.create(coach);
        Assert.assertEquals(coachDao.findById(coach.getId()), coach);

        coachDao.delete(coach);
        Assert.assertNull(coachDao.findById(coach.getId()));
    }

    @Test
    public void testFindById() {
        Coach coach1 = new Coach();
        coach1.setFirstName("Firstname");
        coach1.setLastName("Lastname");
        coach1.setEmail("lastname@example.com");

        Coach coach2 = new Coach();
        coach2.setFirstName("Vorname");
        coach2.setLastName("Nachname");
        coach2.setEmail("nachname@example.com");

        coachDao.create(coach1);
        coachDao.create(coach2);

        Assert.assertEquals(coachDao.findById(coach1.getId()), coach1);
        Assert.assertEquals(coachDao.findById(coach2.getId()), coach2);
    }

    @Test
    public void testFindAll() {
        Coach coach1 = new Coach();
        coach1.setFirstName("Firstname");
        coach1.setLastName("Lastname");
        coach1.setEmail("lastname@example.com");

        Coach coach2 = new Coach();
        coach2.setFirstName("Vorname");
        coach2.setLastName("Nachname");
        coach2.setEmail("nachname@example.com");

        Coach coach3 = new Coach();
        coach3.setFirstName("Prénom");
        coach3.setLastName("Nom de famille");
        coach3.setEmail("nomdefamille@example.com");

        coachDao.create(coach1);
        coachDao.create(coach2);
        coachDao.create(coach3);

        Assert.assertEquals(coachDao.findAll().size(), 3);
        Assert.assertTrue(coachDao.findAll().contains(coach1));
        Assert.assertTrue(coachDao.findAll().contains(coach2));
        Assert.assertTrue(coachDao.findAll().contains(coach3));
    }

    @Test
    public void testFindByEmail() {
        Coach coach1 = new Coach();
        coach1.setFirstName("Firstname");
        coach1.setLastName("Lastname");
        coach1.setEmail("lastname@example.com");

        Coach coach2 = new Coach();
        coach2.setFirstName("Vorname");
        coach2.setLastName("Nachname");
        coach2.setEmail("nachname@example.com");

        Coach coach3 = new Coach();
        coach3.setFirstName("Prénom");
        coach3.setLastName("Nom de famille");
        coach3.setEmail("nomdefamille@example.com");

        coachDao.create(coach1);
        coachDao.create(coach2);
        coachDao.create(coach3);

        Assert.assertEquals(coachDao.findByEmail(coach2.getEmail()), coach2);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testFirstNameNull() {
        Coach coach = new Coach();
        coach.setLastName("Lastname");
        coach.setEmail("lastname@example.com");
        coachDao.create(coach);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testLastNameNull() {
        Coach coach = new Coach();
        coach.setFirstName("Firstname");
        coach.setEmail("lastname@example.com");
        coachDao.create(coach);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testEmailNull() {
        Coach coach = new Coach();
        coach.setFirstName("Firstname");
        coach.setLastName("Lastname");
        coachDao.create(coach);
    }
}
