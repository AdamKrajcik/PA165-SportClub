package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import org.hibernate.PersistentObjectException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.Calendar;

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

    private Team blueTeam;
    private Team redTeam;
    private Player player1;
    private Player player2;


    //@BeforeTest
    public void setUp() {
        blueTeam = new Team();
        blueTeam.setName("blue");
        blueTeam.setAgeGroup(AgeGroup.M16);
        teamDao.create(blueTeam);

        redTeam = new Team();
        redTeam.setName("red");
        redTeam.setAgeGroup(AgeGroup.M20);
        teamDao.create(redTeam);

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
    }

    @Test
    public void createEntry() {
        setUp();


        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(42);
        rosterEntryDao.create(entry1);

        RosterEntry entry2 = new RosterEntry();
        entry2.setPlayer(player1);
        entry2.setTeam(blueTeam);
        entry2.setJerseyNumber(10);
        rosterEntryDao.create(entry2);

        Assert.assertEquals(rosterEntryDao.findById(entry1.getId()), entry1);
        Assert.assertEquals(rosterEntryDao.findAll().size(), 2);
        Assert.assertTrue(rosterEntryDao.findAll().contains(entry2));

    }

    @Test
    void updateEntry() {
        setUp();

        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(42);
        rosterEntryDao.create(entry1);
        entry1.setJerseyNumber(24);
        rosterEntryDao.update(entry1);
        Assert.assertEquals(rosterEntryDao.findById(entry1.getId()).getJerseyNumber(), 24);

    }

    @Test
    public void deleteEntry() {
        setUp();

        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(42);
        rosterEntryDao.create(entry1);
        Assert.assertEquals(rosterEntryDao.findAll().size(), 1);
        rosterEntryDao.remove(entry1);
        Assert.assertEquals(rosterEntryDao.findAll().size(), 0);
        Assert.assertNull(rosterEntryDao.findById(entry1.getId()));
    }


    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithNullTeam() {
        setUp();

        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(null);
        entry1.setJerseyNumber(42);
        rosterEntryDao.create(entry1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithNullPlayer() {
        setUp();

        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(null);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(42);
        rosterEntryDao.create(entry1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void createEntryWithBigNumber() {
        setUp();

        RosterEntry entry1 = new RosterEntry();
        entry1.setPlayer(player1);
        entry1.setTeam(blueTeam);
        entry1.setJerseyNumber(999);

        rosterEntryDao.create(entry1);


    }

}
