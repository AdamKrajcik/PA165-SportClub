package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager em;

    @Inject
    private CoachDao coachDao;

    private Coach coach1;
    private Coach coach2;
    private Coach coach3;

    @BeforeMethod
    public void setUp(){

        coach1 = new Coach();
        coach1.setFirstName("Firstname");
        coach1.setLastName("Lastname");
        coach1.setEmail("lastname@example.com");

        coach2 = new Coach();
        coach2.setFirstName("Vorname");
        coach2.setLastName("Nachname");
        coach2.setEmail("nachname@example.com");

        coach3 = new Coach();
        coach3.setFirstName("Prénom");
        coach3.setLastName("Nom de famille");
        coach3.setEmail("nomdefamille@example.com");


    }

    @Test
    public void testCreate() {

        coachDao.create(coach1);

        Assert.assertEquals(coachDao.findById(coach1.getId()), coach1);
    }

    @Test
    public void testUpdate() {

        em.persist(coach1);
        em.flush();
        coach1.setEmail("firstname@example.com");
        coachDao.update(coach1);

        Assert.assertEquals(coachDao.findById(coach1.getId()).getEmail(), coach1.getEmail());
    }

    @Test
    public void testDelete() {

        em.persist(coach1);
        em.flush();
        Assert.assertEquals(coachDao.findById(coach1.getId()), coach1);

        coachDao.delete(coach1);
        Assert.assertNull(coachDao.findById(coach1.getId()));
    }

    @Test
    public void testFindById() {

        em.persist(coach1);
        em.persist(coach2);
        em.flush();

        Assert.assertEquals(coachDao.findById(coach1.getId()), coach1);
        Assert.assertEquals(coachDao.findById(coach2.getId()), coach2);
    }

    @Test
    public void testFindAll() {

        em.persist(coach1);
        em.persist(coach2);
        em.persist(coach3);
        em.flush();

        Assert.assertEquals(coachDao.findAll().size(), 3);
        Assert.assertTrue(coachDao.findAll().contains(coach1));
        Assert.assertTrue(coachDao.findAll().contains(coach2));
        Assert.assertTrue(coachDao.findAll().contains(coach3));
    }

    @Test
    public void testFindByEmail() {

        em.persist(coach1);
        em.persist(coach2);
        em.persist(coach3);
        em.flush();

        Assert.assertEquals(coachDao.findByEmail(coach2.getEmail()), coach2);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createNullCoach() {

        coachDao.create(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullCoach() {

        coachDao.update(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteWithNullCoach() {

        coachDao.delete(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findWithNullId() {

        coachDao.findById(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findWithNullEmail() {

        coachDao.findByEmail(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testFirstNameNull() {

        coach1.setFirstName(null);
        coachDao.create(coach1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testLastNameNull() {

        coach1.setLastName(null);
        coachDao.create(coach1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testEmailNull() {

        coach1.setEmail(null);
        coachDao.create(coach1);
    }

}
