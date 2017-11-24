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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Tests for Player
 *
 * @author 445403 Kristián Katanik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PlayerDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PlayerDao playerDao;

    @Inject
    private TeamDao teamDao;

    @Inject
    private RosterEntryDao rosterEntryDao;

    @PersistenceContext
    private EntityManager em;

    private Player player1;
    private Player player2;

    @BeforeMethod
    public void setUp(){

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
    public void testFindAll() {

        em.persist(player1);
        em.persist(player2);
        em.flush();

        List<Player> result = playerDao.findAll();
        Assert.assertEquals(result.size(), 2);
    }

    @Test
    public void testDeletePlayer() {

        em.persist(player1);
        em.flush();
        Assert.assertNotNull(player1.getId());
        playerDao.delete(player1);
        Assert.assertNull(playerDao.findById(player1.getId()));
    }

    @Test
    public void testUpdatePlayer() {

        em.persist(player1);
        em.flush();
        player1.setHeight(180);
        playerDao.update(player1);
        Assert.assertEquals(playerDao.findById(player1.getId()).getHeight(), 180, 0.01);
    }

    @Test
    public void testCreatePlayer() {

        playerDao.create(player1);
        Assert.assertEquals(playerDao.findById(player1.getId()).getEmail(), "pavol.mrkva@gmail.com");
    }

    @Test
    public void testFindByEmail() {

        em.persist(player1);
        em.flush();
        Player found = playerDao.findByEmail(player1.getEmail());
        Assert.assertEquals(found, player1);
    }

    @Test
    public void testFindById() {

        em.persist(player1);
        em.flush();

        Player found = playerDao.findById(player1.getId());
        Assert.assertEquals(found, player1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findWithNullId() {

        playerDao.findById(null);

    }

    @Test(expectedExceptions = NoResultException.class)
    public void findWithNullEmail() {

        playerDao.findByEmail(null);

    }

    @Test
    public void findWithNullPlayer() {

        List<Team> result = playerDao.findTeamsByPlayer(null);
        Assert.assertEquals(result, Collections.emptyList());

    }

    @Test
    public void testFindTeamsByPlayer() {

        playerDao.create(player1);

        Team team1 = new Team();
        team1.setAgeGroup(AgeGroup.M20);
        team1.setCoach(null);
        team1.setName("Chickens");
        teamDao.create(team1);

        Team team2 = new Team();
        team2.setAgeGroup(AgeGroup.M20);
        team2.setCoach(null);
        team2.setName("Cows");
        teamDao.create(team2);

        RosterEntry rosterEntry1 = new RosterEntry();
        rosterEntry1.setPlayer(player1);
        rosterEntry1.setJerseyNumber(10);
        rosterEntry1.setTeam(team1);
        rosterEntryDao.create(rosterEntry1);

        RosterEntry rosterEntry2 = new RosterEntry();
        rosterEntry2.setPlayer(player1);
        rosterEntry2.setJerseyNumber(20);
        rosterEntry2.setTeam(team2);
        rosterEntryDao.create(rosterEntry2);

        player1.addRosterEntry(rosterEntry1);
        player1.addRosterEntry(rosterEntry2);
        playerDao.update(player1);
        team1.addRosterEntry(rosterEntry1);
        team2.addRosterEntry(rosterEntry2);
        teamDao.update(team1);
        teamDao.update(team2);

        List<Team> result = playerDao.findTeamsByPlayer(player1);
        List<Team> listTeams = new ArrayList<>();
        listTeams.add(team1);
        listTeams.add(team2);

        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result,listTeams);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullFirstName() {

        player1.setFirstName(null);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullLastName() {

        player1.setLastName(null);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNullDateOfBirth() {

        player1.setDateOfBirth(null);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testHeightNotNegative() {

        player1.setHeight(-1);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testHeightMoreThanMax() {

        player1.setHeight(300);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testHeightLessThanMin() {

        player1.setHeight(10);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testWeightNotNegative() {

        player1.setWeight(-1);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testWeightMoreThanMax() {

        player1.setWeight(300);
        playerDao.create(player1);
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testWeightLessThenMin() {

        player1.setWeight(10);
        playerDao.create(player1);
    }
}
