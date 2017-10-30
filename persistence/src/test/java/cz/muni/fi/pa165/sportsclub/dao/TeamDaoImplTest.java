package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;


@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class TeamDaoImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TeamDao teamDao;

    @Test
    public void testCreate() {
        Team blueTeem = new Team();
        blueTeem.setName("blue");
        blueTeem.setAgeGroup(AgeGroup.M16);

        teamDao.create(blueTeem);
        Assert.assertEquals(teamDao.getTeamById(blueTeem.getId()), blueTeem);
    }

    @Test
    public void testUpdate() {
        Team redTeam = new Team();
        redTeam.setName("red");
        redTeam.setAgeGroup(AgeGroup.M20);

        teamDao.create(redTeam);
        redTeam.setName("yellow");
        teamDao.update(redTeam);

        Assert.assertEquals(teamDao.getTeamById(redTeam.getId()), redTeam);
    }

    @Test
    public void testDelete() {
        Team yellowTeam = new Team();
        yellowTeam.setName("yellow");
        yellowTeam.setAgeGroup(AgeGroup.M24);

        teamDao.create(yellowTeam);
        Assert.assertEquals(teamDao.getTeamById(yellowTeam.getId()), yellowTeam);

        teamDao.delete(yellowTeam);
        Assert.assertNull(teamDao.getTeamById(yellowTeam.getId()));
    }

    @Test
    public void testGetTeamById()  {
        Team greenTeam = new Team();
        greenTeam.setName("green");
        greenTeam.setAgeGroup(AgeGroup.M16);

        Team orangeTeam = new Team();
        orangeTeam.setName("orange");
        orangeTeam.setAgeGroup(AgeGroup.M20);

        teamDao.create(greenTeam);
        teamDao.create(orangeTeam);

        Assert.assertEquals(teamDao.getTeamById(greenTeam.getId()), greenTeam);
        Assert.assertEquals(teamDao.getTeamById(orangeTeam.getId()), orangeTeam);
    }

    @Test
    public void testGetAllTeams()  {
        Team greenTeam = new Team();
        greenTeam.setName("green");
        greenTeam.setAgeGroup(AgeGroup.M16);

        Team orangeTeam = new Team();
        orangeTeam.setName("orange");
        orangeTeam.setAgeGroup(AgeGroup.M20);

        teamDao.create(greenTeam);
        teamDao.create(orangeTeam);

        Assert.assertEquals(teamDao.getAllTeams().size(), 2);
        Assert.assertTrue(teamDao.getAllTeams().contains(greenTeam));
        Assert.assertTrue(teamDao.getAllTeams().contains(orangeTeam));
    }

    @Test(expectedExceptions = ConstraintViolationException.class)
    public void testNameNull() {
        Team team = new Team();
        team.setAgeGroup(AgeGroup.M16);
        teamDao.create(team);
    }
}
