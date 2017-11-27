package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
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

import java.time.Instant;
import java.util.*;

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

    private Player playerM16;
    private Player playerM20;
    private Player playerM24;
    private Player playerMS;


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

        // Current time is July 28, 2016
        playerM16 = EntityFactory.createPlayerM16("PlayerM16");
        playerM20 = EntityFactory.createPlayerM20("PlayerM20");
        playerM24 = EntityFactory.createPlayerM24("PlayerM24");
        playerMS = EntityFactory.createPlayerMS("PlayerMS");

        when(ageGroupService.ageGroupForBirthDate(playerM16.getDateOfBirth())).thenReturn(AgeGroup.M16);
        when(ageGroupService.ageGroupForBirthDate(playerM20.getDateOfBirth())).thenReturn(AgeGroup.M20);
        when(ageGroupService.ageGroupForBirthDate(playerM24.getDateOfBirth())).thenReturn(AgeGroup.M24);
        when(ageGroupService.ageGroupForBirthDate(playerMS.getDateOfBirth())).thenReturn(AgeGroup.MS);
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
    public void testGetAllowedTeamsSingleNoDuplicates() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);
        Team teamM20 = EntityFactory.createTeam("Test Team M20", AgeGroup.M20, coach);
        Team teamM24 = EntityFactory.createTeam("Test Team M24", AgeGroup.M24, coach);
        Team teamMS = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);

        coach.addTeam(teamM16);
        coach.addTeam(teamM20);
        coach.addTeam(teamM24);
        coach.addTeam(teamMS);

        assertThat(rosterService.getAllowedTeams(coach, playerM16)).containsExactlyInAnyOrder(teamM16, teamM20);
        assertThat(rosterService.getAllowedTeams(coach, playerM20)).containsExactlyInAnyOrder(teamM20, teamM24);
        assertThat(rosterService.getAllowedTeams(coach, playerM24)).containsExactlyInAnyOrder(teamM24, teamMS);
        assertThat(rosterService.getAllowedTeams(coach, playerMS)).containsExactlyInAnyOrder(teamMS);
    }

    @Test
    public void testGetAllowedTeamsMultipleNoDuplicates() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);
        Team teamM20_1 = EntityFactory.createTeam("Test Team M20 1", AgeGroup.M20, coach);
        Team teamM20_2 = EntityFactory.createTeam("Test Team M20 2", AgeGroup.M20, coach);
        Team teamM20_3 = EntityFactory.createTeam("Test Team M20 3", AgeGroup.M20, coach);
        Team teamM24_1 = EntityFactory.createTeam("Test Team M24 1", AgeGroup.M24, coach);
        Team teamM24_2 = EntityFactory.createTeam("Test Team M24 2", AgeGroup.M24, coach);
        Team teamMS_1 = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);
        Team teamMS_2 = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);

        coach.addTeam(teamM16);
        coach.addTeam(teamM20_1);
        coach.addTeam(teamM20_2);
        coach.addTeam(teamM20_3);
        coach.addTeam(teamM24_1);
        coach.addTeam(teamM24_2);
        coach.addTeam(teamMS_1);
        coach.addTeam(teamMS_2);

        assertThat(rosterService.getAllowedTeams(coach, playerM20))
                .containsExactlyInAnyOrder(teamM20_1, teamM20_2, teamM20_3, teamM24_1, teamM24_2);
    }

    @Test
    public void testGetAllowedTeamsSingleDuplicate() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM20 = EntityFactory.createTeam("Test Team M20", AgeGroup.M20, coach);
        Team teamM24 = EntityFactory.createTeam("Test Team M24", AgeGroup.M24, coach);

        coach.addTeam(teamM20);
        coach.addTeam(teamM24);

        teamM20.addRosterEntry(new RosterEntry(teamM20, playerM20, 1));

        List<Team> teams = rosterService.getAllowedTeams(coach, playerM20);
        assertThat(teams).doesNotContain(teamM20);
        assertThat(teams).containsExactlyInAnyOrder(teamM24);
    }

    @Test
    public void testGetAllowedTeamsMultipleDuplicates() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);
        Team teamM20_1 = EntityFactory.createTeam("Test Team M20 1", AgeGroup.M20, coach);
        Team teamM20_2 = EntityFactory.createTeam("Test Team M20 2", AgeGroup.M20, coach);
        Team teamM20_3 = EntityFactory.createTeam("Test Team M20 3", AgeGroup.M20, coach);
        Team teamM24_1 = EntityFactory.createTeam("Test Team M24 1", AgeGroup.M24, coach);
        Team teamM24_2 = EntityFactory.createTeam("Test Team M24 2", AgeGroup.M24, coach);
        Team teamMS_1 = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);
        Team teamMS_2 = EntityFactory.createTeam("Test Team MS", AgeGroup.MS, coach);

        coach.addTeam(teamM16);
        coach.addTeam(teamM20_1);
        coach.addTeam(teamM20_2);
        coach.addTeam(teamM20_3);
        coach.addTeam(teamM24_1);
        coach.addTeam(teamM24_2);
        coach.addTeam(teamMS_1);
        coach.addTeam(teamMS_2);

        teamM20_2.addRosterEntry(new RosterEntry(teamM20_2, playerM20, 1));
        teamM24_1.addRosterEntry(new RosterEntry(teamM24_1, playerM20, 1));

        List<Team> teams = rosterService.getAllowedTeams(coach, playerM20);

        assertThat(teams).doesNotContain(teamM20_2);
        assertThat(teams).doesNotContain(teamM24_1);
        assertThat(teams).containsExactlyInAnyOrder(teamM20_1, teamM20_3, teamM24_2);
    }

    @Test
    public void testGetAllowedTeamsNoneOfGroup() throws Exception {
        Coach coach = EntityFactory.createCoach();

        Team teamM20 = EntityFactory.createTeam("Test Team M20", AgeGroup.M20, coach);
        Team teamM24 = EntityFactory.createTeam("Test Team M24", AgeGroup.M24, coach);

        coach.addTeam(teamM20);
        coach.addTeam(teamM24);

        assertThat(rosterService.getAllowedTeams(coach, playerMS)).isEmpty();
    }

    @Test
    public void testGetAllowedTeamsNoTeams() throws Exception {
        Coach coach = EntityFactory.createCoach();

        assertThat(rosterService.getAllowedTeams(coach, playerM20)).isEmpty();
    }

    @Test
    public void testGetAllowedPlayers() throws Exception {
        Coach coach = EntityFactory.createCoach();
        Team teamM16 = EntityFactory.createTeam("Test Team M16", AgeGroup.M16, coach);

        Date date20yrs = Date.from(Instant.parse("1995-07-27T23:59:59.999Z"));
        Date date16yrsOld = Date.from(Instant.parse("1999-07-27T23:59:59.999Z"));
        Date currentTime = EntityFactory.getConstantCurrentTime();

        when(ageGroupService.getTimeSpan(AgeGroup.M16))
                .thenReturn(new TimeSpan(date16yrsOld, currentTime));

        when(ageGroupService.getTimeSpan(AgeGroup.M20))
                .thenReturn(new TimeSpan(date20yrs, date16yrsOld));


        // Single player
        when(playerDao.findByBirthDate(date20yrs, currentTime))
                .thenReturn(Collections.singletonList(playerM16));
        assertThat(rosterService.getAllowedPlayers(teamM16)).containsExactlyInAnyOrder(playerM16);

        // Two players
        when(playerDao.findByBirthDate(date20yrs, currentTime))
                .thenReturn(new ArrayList<>(Arrays.asList(playerM16, playerM20)));
        assertThat(rosterService.getAllowedPlayers(teamM16)).containsExactlyInAnyOrder(playerM16, playerM20);

        // No players
        when(playerDao.findByBirthDate(date20yrs, currentTime))
                .thenReturn(new ArrayList<>());
        assertThat(rosterService.getAllowedPlayers(teamM16)).isEmpty();
    }
}
