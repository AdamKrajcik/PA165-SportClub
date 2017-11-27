package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsclub.dao.RosterEntryDao;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Roster service
 *
 * @author 410461 Martin Skrovina
 */
public class RosterServiceImplTest {

    @Inject
    @InjectMocks
    private RosterService rosterService = new RosterServiceImpl();

    @Mock
    private RosterEntryDao rosterEntryDao;

    @Mock
    private PlayerDao playerDao;

    @Mock
    private AgeGroupService ageGroupService;

    private RosterEntry rosterEntry;
    private RosterEntry createdRosterEntry;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        rosterEntry = EntityFactory.createRosterEntry();
        createdRosterEntry = EntityFactory.createRosterEntry();
        createdRosterEntry.setId(1L);

        when(rosterEntryDao.findById(1L)).thenReturn(createdRosterEntry);
    }

    @Test
    public void testCreateRosterEntry() throws Exception {
        rosterService.createEntry(rosterEntry);
        verify(rosterEntryDao).create(rosterEntry);
    }

    @Test
    public void testUpdateRosterEntry() throws Exception {
        rosterService.updateEntry(rosterEntry);
        verify(rosterEntryDao).update(rosterEntry);
    }

    @Test
    public void testDeleteRosterEntry() throws Exception {
        rosterService.deleteEntry(1L);
        verify(rosterEntryDao).delete(createdRosterEntry);
    }

    @Test
    public void testFindById() throws Exception {
        assertThat(rosterService.findById(1L)).isEqualToComparingFieldByField(createdRosterEntry);
    }

    @Test
    public void testFindAll() throws Exception {
        when(rosterEntryDao.findAll()).thenReturn(Collections.singletonList(createdRosterEntry));

        assertThat(rosterService.findAll())
                .isEqualTo(Collections.singletonList(createdRosterEntry));
    }

    @Test
    public void testGetAllowedTeams() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);
        Team teamM20 = EntityFactory.createTeam("Test Team M20", AgeGroup.M20, coach);
        Team teamM24 = EntityFactory.createTeam("Test Team M24", AgeGroup.M24, coach);
        Team teamMS = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);

        coach.addTeam(teamM16);
        coach.addTeam(teamM20);
        coach.addTeam(teamM24);
        coach.addTeam(teamMS);

        Player playerM16 = EntityFactory.createPlayerM16("PlayerM16");
        Player playerM20 = EntityFactory.createPlayerM20("PlayerM20");
        Player playerM24 = EntityFactory.createPlayerM24("PlayerM24");
        Player playerMS = EntityFactory.createPlayerMS("PlayerMS");

//        team1.addRosterEntry(new RosterEntry(team1, player1, 1));
//        team1.addRosterEntry(new RosterEntry(team1, player1, 2));
//        team1.addRosterEntry(new RosterEntry(team1, player1, 3));
//        team1.addRosterEntry(new RosterEntry(team1, player1, 4));

        // Current time is e.g. July 27, 2016
        when(ageGroupService.ageGroupForBirthDate(playerM16.getDateOfBirth())).thenReturn(AgeGroup.M16);
        when(ageGroupService.ageGroupForBirthDate(playerM20.getDateOfBirth())).thenReturn(AgeGroup.M20);
        when(ageGroupService.ageGroupForBirthDate(playerM24.getDateOfBirth())).thenReturn(AgeGroup.M24);
        when(ageGroupService.ageGroupForBirthDate(playerMS.getDateOfBirth())).thenReturn(AgeGroup.MS);

        assertThat(rosterService.getAllowedTeams(coach, playerM16)).containsExactlyInAnyOrder(teamM16, teamM20);
        assertThat(rosterService.getAllowedTeams(coach, playerM20)).containsExactlyInAnyOrder(teamM20, teamM24);
        assertThat(rosterService.getAllowedTeams(coach, playerM24)).containsExactlyInAnyOrder(teamM24, teamMS);
        assertThat(rosterService.getAllowedTeams(coach, playerMS)).containsExactlyInAnyOrder(teamMS);
    }

    @Test
    public void testGetAllowedPlayers() throws Exception {
        Coach coach = EntityFactory.createCoach();
        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);

        Player playerM16 = EntityFactory.createPlayerM16("PlayerM16");

        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JULY, 27);
        Date date16yrs = cal.getTime();
        Date currentTime = EntityFactory.getConstantCurrentTime();

        when(ageGroupService.getTimeSpan(AgeGroup.M16)).thenReturn(new TimeSpan(date16yrs, currentTime));
        cal.set(1996, Calendar.JULY, 27);
        Date date20yrs = cal.getTime();

        when(ageGroupService.getTimeSpan(AgeGroup.M20)).thenReturn(new TimeSpan(date20yrs, date16yrs));
        when(playerDao.findByBirthDate(date20yrs, currentTime)).thenReturn(Collections.singletonList(playerM16));

        assertThat(rosterService.getAllowedPlayers(teamM16)).containsExactlyInAnyOrder(playerM16);
    }
}
