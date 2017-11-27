package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.RosterEntryDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.AgeGroupService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.RosterService;
import cz.muni.fi.pa165.sportsclub.service.TeamService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.*;

/**
 * Test for team facade
 *
 * @author Jan Cech
 */
public class TeamFacadeTest {
    @Mock
    TeamService teamService;

    @Mock
    PlayerService playerService;

    @Mock
    RosterService rosterService;

    @Mock
    MappingService mappingService;

    @Mock
    AgeGroupService ageGroupService;

    @InjectMocks
    private TeamFacade teamFacade = new TeamFacadeImpl();

    private Player player1;
    private PlayerDto player1Dto;


//    private Player player2;
//    private PlayerDto player2Dto;

    private Team team1;
    private TeamDto team1Dto;

    private Coach coach;
    private CoachDto coachDto;

    @BeforeMethod
    public void setUp() {

        coach = new Coach();
        coach.setEmail("coach1@gmail.com");
        coach.setFirstName("Jan");
        coach.setLastName("Novak");

        coachDto = new CoachDto();


        team1 = new Team();
        team1.setAgeGroup(AgeGroup.MS);
        team1.setCoach(coach);
        team1.setName("Sparta");

        //team1.setId(1);

        team1Dto = new TeamDto();
        team1Dto.setAgeCategory("MS");
        team1Dto.setCoach(coachDto);
        team1Dto.setName("Sparta");
        team1Dto.setId(1);

        player1 = new Player();
        player1.setDateOfBirth(Date.from(Instant.parse("1976-08-27T00:00:00.000Z")));
        player1Dto = new PlayerDto();
        player1Dto.setId(1L);
        player1Dto.setDateOfBirth(Date.from(Instant.parse("1976-08-27T00:00:00.000Z")));
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createTeam() {
        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        Mockito.doAnswer(answer ->
                {
                    team1.setId(1L);
                    return null;
                }
        ).when(teamService).createTeam(team1);
        teamFacade.createTeam(team1Dto);
        Mockito.verify(teamService, Mockito.times(1)).createTeam(team1);
        Assert.assertEquals(team1.getId(), 1L);
    }

    @Test
    public void deleteTeam() {
        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        Mockito.when(teamService.findById(1L)).thenReturn(team1);

        teamFacade.deleteTeam(team1Dto);
        Mockito.verify(teamService, Mockito.times(1)).deleteTeam(1L);
    }

    @Test
    public void updateTeam() {
        Mockito.when(teamService.findById(1L)).thenReturn(team1);
        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        teamFacade.updateTeam(team1Dto);
        Mockito.verify(teamService, Mockito.times(1)).updateTeam(team1);
    }

    @Test
    public void addPlayer() {

        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        Mockito.when(mappingService.mapTo(player1Dto, Player.class)).thenReturn(player1);
        Mockito.when(ageGroupService.ageGroupForBirthDate(Date.from(Instant.parse("1976-08-27T00:00:00.000Z")))).thenReturn(AgeGroup.MS);
        teamFacade.addPlayer(player1Dto, team1Dto, 42);
        Mockito.verify(teamService, Mockito.times(1)).updateTeam(team1);
        Mockito.verify(playerService, Mockito.times(1)).updatePlayer(player1);
        Mockito.verify(rosterService, Mockito.times(1)).createEntry(Mockito.any());

    }

    @Test
    public void removePlayer() {
        RosterEntry rosterEntry = new RosterEntry();
        rosterEntry.setPlayer(player1);
        rosterEntry.setTeam(team1);
        rosterEntry.setJerseyNumber(42);
        rosterEntry.setId(1L);

        RosterEntryDto rosterEntryDto = new RosterEntryDto();
        rosterEntryDto.setJerseyNumber(42);
        rosterEntryDto.setPlayer(player1Dto);
        rosterEntryDto.setTeam(team1Dto);
        rosterEntryDto.setId(1L);

        Team mockedTeam = Mockito.mock(Team.class);
        Set<RosterEntry> entries = new HashSet<>();
        entries.add(rosterEntry);
        Mockito.when(mockedTeam.getRosterEntries()).thenReturn(entries);

        Mockito.when(teamService.findById(1L)).thenReturn(mockedTeam);
        Mockito.when(playerService.findById(1L)).thenReturn(player1);
        teamFacade.removePlayer(player1Dto, team1Dto);
        Mockito.verify(teamService, Mockito.times(1)).updateTeam(Mockito.any());
        Mockito.verify(playerService, Mockito.times(1)).updatePlayer(Mockito.any());
        Mockito.verify(rosterService, Mockito.times(1)).deleteEntry(1L);
    }

    @Test
    public void changeNumber() {
        RosterEntry rosterEntry = new RosterEntry();
        rosterEntry.setPlayer(player1);
        rosterEntry.setTeam(team1);
        rosterEntry.setJerseyNumber(42);
        rosterEntry.setId(1L);

        RosterEntryDto rosterEntryDto = new RosterEntryDto();
        rosterEntryDto.setJerseyNumber(42);
        rosterEntryDto.setPlayer(player1Dto);
        rosterEntryDto.setTeam(team1Dto);
        rosterEntryDto.setId(1L);

        Team mockedTeam = Mockito.mock(Team.class);
        Set<RosterEntry> entries = new HashSet<>();
        entries.add(rosterEntry);
        Mockito.when(mockedTeam.getRosterEntries()).thenReturn(entries);

        Mockito.when(teamService.findById(1L)).thenReturn(mockedTeam);
        Mockito.when(playerService.findById(1L)).thenReturn(player1);
        teamFacade.updateJerseyNumber(player1Dto, team1Dto, 33);
        Mockito.verify(rosterService, Mockito.times(1)).updateEntry(rosterEntry);
    }

    @Test
    public void getTeamById() {
        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        Mockito.when(mappingService.mapTo(team1, TeamDto.class)).thenReturn(team1Dto);
        Mockito.when(teamService.findById(team1Dto.getId())).thenReturn(team1);
        TeamDto t = teamFacade.getTeam(1L);

        Mockito.verify(teamService, Mockito.times(1)).findById(team1Dto.getId());
        Assert.assertEquals(t, team1Dto);
    }

    @Test
    public void getTeamByName() {
        Mockito.when(mappingService.mapTo(team1Dto, Team.class)).thenReturn(team1);
        Mockito.when(mappingService.mapTo(team1, TeamDto.class)).thenReturn(team1Dto);
        Mockito.when(teamService.findByName("Sparta")).thenReturn(team1);
        TeamDto t = teamFacade.getTeamByName("Sparta");

        Mockito.verify(teamService, Mockito.times(1)).findByName("Sparta");
        Assert.assertEquals(t, team1Dto);
    }

    @Test
    public void getAll() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);

        Mockito.when(teamService.getAll()).thenReturn(teams);
        List<TeamDto> expected = new ArrayList<>();
        expected.add(team1Dto);
        Mockito.when(mappingService.mapTo(teams, TeamDto.class)).thenReturn(expected);
        List<TeamDto> actualTeams = teamFacade.getAllTeams();


        Assert.assertEquals(actualTeams.size(), expected.size());
        Assert.assertEquals(actualTeams.get(0), expected.get(0));
    }

}
