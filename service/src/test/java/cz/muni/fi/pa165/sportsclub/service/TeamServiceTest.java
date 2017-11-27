package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.TeamDao;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Tests for team service
 *
 * @author Jan Cech
 */
public class TeamServiceTest {

    private Team team1;
    private Team team2;
    private Team team3;

    private Coach coach1;
    @Mock
    private TeamDao teamDao;

    @InjectMocks
    private TeamService teamService = new TeamServiceImpl();

    @BeforeMethod
    public void setUp() {

        coach1 = new Coach();
        coach1.setEmail("coach1@gmail.com");
        coach1.setFirstName("Jan");
        coach1.setLastName("Novak");

        team1 = new Team();
        team1.setAgeGroup(AgeGroup.MS);
        team1.setCoach(coach1);
        team1.setName("Sparta");
        team1.setId(1);

        team2 = new Team();
        team2.setAgeGroup(AgeGroup.MS);
        team2.setCoach(coach1);
        team2.setName("Banik");
        team2.setId(2);

        team3 = new Team();
        team3.setAgeGroup(AgeGroup.MS);
        team3.setCoach(coach1);
        team3.setName("Brno");
        team3.setId(3);
    }

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createTeam() {
        teamService.createTeam(team1);
        Mockito.verify(teamDao, Mockito.times(1)).create(team1);
    }

    @Test
    public void updateTeam() {
        teamService.updateTeam(team2);
        Mockito.verify(teamDao, Mockito.times(1)).update(team2);
    }

    @Test
    public void deleteTeam() {
        Mockito.when(teamDao.findById(2L)).thenReturn(team2);
        teamService.deleteTeam(team2.getId());

        Mockito.verify(teamDao, Mockito.times(1)).delete(team2);
    }

    @Test
    public void findByIdTest() {
        Mockito.when(teamDao.findById(team3.getId())).thenReturn(team3);
        Team actual = teamService.findById(team3.getId());
        Assert.assertEquals(actual, team3);
    }

    @Test
    public void findByName() {
        Mockito.when(teamDao.findByName(team1.getName())).thenReturn(team1);
        Team actual = teamService.findByName(team1.getName());
        Assert.assertEquals(actual, team1);
    }

    @Test
    public void findAll() {
        List<Team> result = new ArrayList<>();
        result.add(team1);
        result.add(team2);
        result.add(team3);

        Mockito.when(teamDao.findAll()).thenReturn(result);

        List<Team> actual = teamService.getAll();
        Assert.assertEquals(actual.size(), 3);
        Assert.assertTrue(actual.contains(team1));
    }
}
