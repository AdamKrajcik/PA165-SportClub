package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Tests for RosterEntryDao
 *
 * @author Jan Cech
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class RosterEntryDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private RosterEntryDao rosterEntryDao;

    @Inject
    private TeamDao teamDao;

    @Inject
    private PlayerDao playerDao;

    @PersistenceContext
    private EntityManager em;

    private Team blueTeam;
    private Team redTeam;
    private Player player1;
    private Player player2;
    private RosterEntry entry1;
    private RosterEntry entry2;


    @BeforeMethod
    public void setUp() {
        blueTeam = new Team();
        blueTeam.setName("blue");
        blueTeam.setAgeGroup(AgeGroup.M16);

        redTeam = new Team();
        redTeam.setName("red");
        redTeam.setAgeGroup(AgeGroup.M20);


        player1 = new Player();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(2000, 2, 11);
        player1.setDateOfBirth(cal1.getTime());
        player1.setHeight(160);
        player1.setWeight(80);
        player1.setFirstName("Pavol");
        player1.setLastName("Mrkva");
        player1.setEmail("pavol.mrkva@gmail.com");

        player2 = new Player();
        Calendar cal2 = Calendar.getInstance();
        cal2.set(1998, 11, 20);
        player2.setDateOfBirth(cal2.getTime());
        player2.setHeight(165);
        player2.setWeight(90);
        player2.setFirstName("Peter");
        player2.setLastName("Noval");
        player2.setEmail("peter.noval@gmail.com");

        entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(42);

        entry2 = new RosterEntry();
        entry2.setPlayer(player1);
        entry2.setTeam(blueTeam);
        entry2.setJerseyNumber(10);
    }

    @Test
    public void createEntry() {

        rosterEntryDao.create(entry1);

        Assert.assertEquals(rosterEntryDao.findById(entry1.getId()), entry1);
    }

    @Test
    void updateEntry() {

        em.persist(entry1);
        em.flush();
        entry1.setJerseyNumber(24);
        rosterEntryDao.update(entry1);
        Assert.assertEquals(rosterEntryDao.findById(entry1.getId()).getJerseyNumber(), 24);
    }

    @Test
    public void deleteEntry() {

        em.persist(entry1);
        em.flush();
        Assert.assertEquals(rosterEntryDao.findAll().size(), 1);
        rosterEntryDao.delete(entry1);
        Assert.assertEquals(rosterEntryDao.findAll().size(), 0);
        Assert.assertNull(rosterEntryDao.findById(entry1.getId()));
    }

    @Test
    public void findById(){

        em.persist(entry1);
        em.flush();
        RosterEntry result = rosterEntryDao.findById(entry1.getId());
        Assert.assertEquals(result,entry1);
    }

    @Test
    public void findAll(){

        em.persist(entry1);
        em.persist(entry2);
        em.flush();
        List<RosterEntry> result = rosterEntryDao.findAll();
        List<RosterEntry> listEntries = new ArrayList<>();
        listEntries.add(entry1);
        listEntries.add(entry2);
        Assert.assertEquals(result.size(),2);
        Assert.assertEquals(result,listEntries);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findWithNullId() {

        rosterEntryDao.findById(null);

    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithNullTeam() {

        entry1.setTeam(null);
        rosterEntryDao.create(entry1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithNullPlayer() {

        entry1.setPlayer(null);
        rosterEntryDao.create(entry1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithBigNumber() {

        entry1.setJerseyNumber(999);
        rosterEntryDao.create(entry1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithLowNumber() {

        entry1.setJerseyNumber(-50);
        rosterEntryDao.create(entry1);
    }

}
