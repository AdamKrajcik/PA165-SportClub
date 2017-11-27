package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
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
 * Tests for TeamDao
 *
 * @author 422636 Adam Krajcik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TeamDao teamDao;

    @PersistenceContext
    private EntityManager em;

    private Team blueTeam;
    private Team redTeam;
    private Team yellowTeam;

    @BeforeMethod
    public void setUp() {

        blueTeam = new Team();
        blueTeam.setName("blue");
        blueTeam.setAgeGroup(AgeGroup.M16);

        redTeam = new Team();
        redTeam.setName("red");
        redTeam.setAgeGroup(AgeGroup.M20);

        yellowTeam = new Team();
        yellowTeam.setName("yellow");
        yellowTeam.setAgeGroup(AgeGroup.M24);

    }

    @Test
    public void testCreate() {

        teamDao.create(blueTeam);
        Assert.assertEquals(teamDao.findById(blueTeam.getId()), blueTeam);
    }

    @Test
    public void testUpdate() {

        em.persist(redTeam);
        em.flush();

        redTeam.setName("yellow");
        teamDao.update(redTeam);

        Assert.assertEquals(teamDao.findById(redTeam.getId()), redTeam);
    }

    @Test
    public void testDelete() {

        em.persist(yellowTeam);
        em.flush();

        Assert.assertEquals(teamDao.findById(yellowTeam.getId()), yellowTeam);

        teamDao.delete(yellowTeam);
        Assert.assertNull(teamDao.findById(yellowTeam.getId()));
    }

    @Test
    public void testFindById() {
        Team greenTeam = new Team();
        greenTeam.setName("green");
        greenTeam.setAgeGroup(AgeGroup.M16);

        Team orangeTeam = new Team();
        orangeTeam.setName("orange");
        orangeTeam.setAgeGroup(AgeGroup.M20);

        teamDao.create(greenTeam);
        teamDao.create(orangeTeam);

        Assert.assertEquals(teamDao.findById(greenTeam.getId()), greenTeam);
        Assert.assertEquals(teamDao.findById(orangeTeam.getId()), orangeTeam);
    }

    @Test
    public void testFindAll() {

        em.persist(redTeam);
        em.persist(blueTeam);
        em.persist(yellowTeam);
        em.flush();

        Assert.assertEquals(teamDao.findAll().size(), 3);
        Assert.assertTrue(teamDao.findAll().contains(yellowTeam));
        Assert.assertTrue(teamDao.findAll().contains(blueTeam));
        Assert.assertTrue(teamDao.findAll().contains(redTeam));
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createWithNullTeam() {

        teamDao.create(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateWithNullTeam() {

        teamDao.update(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteWithNullTeam() {

        teamDao.delete(null);

    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNullId() {
        teamDao.findById(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void findByNullName() {
        teamDao.findByName(null);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNameNull() {
        yellowTeam.setName(null);
        teamDao.create(yellowTeam);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testAgeGroupNull() {

        yellowTeam.setAgeGroup(null);
        teamDao.create(yellowTeam);
    }
}
