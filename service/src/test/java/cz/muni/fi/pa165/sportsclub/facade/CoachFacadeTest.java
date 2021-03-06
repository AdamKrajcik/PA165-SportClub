package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.CoachService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.RosterService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

/**
 * Tests for Coach facade
 *
 * @author 445403 Kristian Katanik
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class CoachFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CoachService coachService;

    @Mock
    private RosterService rosterService;

    @Mock
    private PlayerService playerService;

    @Mock
    private MappingService mappingService;

    private CoachDto coach1Dto;
    private CoachDto coach2Dto;

    private Coach coach1;
    private Coach coach2;

    @InjectMocks
    private CoachFacade coachFacade = new CoachFacadeImpl();

    @BeforeClass
    public void setupClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUpMethod() {
        coach1 = new Coach();
        coach1.setFirstName("Name");
        coach1.setLastName("Last name");
        coach1.setEmail("email@email.com");

        coach2 = new Coach();
        coach2.setFirstName("Peter");
        coach2.setLastName("Retep");
        coach2.setEmail("peter@email.com");

        coach1Dto = new CoachDto();
        coach1Dto.setFirstName("Name");
        coach1Dto.setLastName("Last name");
        coach1Dto.setEmail("email@email.com");

        coach2Dto = new CoachDto();
        coach2Dto.setFirstName("Peter");
        coach2Dto.setLastName("Retep");
        coach2Dto.setEmail("peter@email.com");
        coach2Dto.setId(2L);
    }

    @Test
    public void createCoachTest() {
        when(mappingService.mapTo(coach1Dto, Coach.class)).thenReturn(coach1);

        doAnswer(invocation -> {
            coach1.setId(1L);
            return null;
        }).when(coachService).createCoach(coach1);

        Long id = coachFacade.createCoach(coach1Dto);

        Mockito.verify(coachService, Mockito.times(1)).createCoach(coach1);
        assertThat(id).isEqualTo(1L);
    }

    @Test
    public void deleteCoachTest() {
        Long id = 1L;

        when(coachService.findById(id)).thenReturn(coach1);

        coachFacade.deleteCoach(id);
        Mockito.verify(coachService, Mockito.times(1)).deleteCoach(coach1);
    }

    @Test
    public void updateCoachTest() {
        when(coachService.findById(2L)).thenReturn(coach2);

        coach1Dto.setFirstName("Masa");

        coachFacade.updateCoach(coach2Dto);
        Mockito.verify(coachService,Mockito.times(1)).updateCoach(coach2);
    }

    @Test
    public void getCoachByIdTest() {
        Long id = 1L;

        when(coachService.findById(id)).thenReturn(coach1);
        when(mappingService.mapTo(coach1, CoachDto.class)).thenReturn(coach1Dto);

        CoachDto test = coachFacade.getCoach(id);

        assertThat(test).isEqualToComparingFieldByField(coach1Dto);
        Mockito.verify(coachService,Mockito.times(2)).findById(id);
    }

    @Test
    public void getAllCoachesTest() {
        when(coachService.getAll()).thenReturn(Arrays.asList(coach1,coach2));
        when(mappingService.mapTo(any(), eq(CoachDto.class))).thenReturn(Arrays.asList(coach1Dto, coach2Dto));

        assertThat(coachFacade.getAllCoaches()).containsOnly(coach1Dto, coach2Dto);
        Mockito.verify(coachService, Mockito.times(1)).getAll();
    }

    @Test
    public void getCoachByEmailTest(){
        String email = "email@email.com";

        when(coachService.findByEmail(email)).thenReturn(coach1);
        when(mappingService.mapTo(coach1, CoachDto.class)).thenReturn(coach1Dto);

        CoachDto result = coachFacade.getCoachByEmail(email);

        Mockito.verify(coachService,Mockito.times(1)).findByEmail(email);
        assertThat(result).isEqualToComparingFieldByField(coach1Dto);
    }

    @Test
    public void getAllowedTeamsTest(){
        Player player = EntityFactory.createPlayer();
        player.setId(3L);
        PlayerDto playerDto = EntityFactory.createPlayerDto();
        playerDto.setId(3L);
        Team team = EntityFactory.createTeam();

        TeamDto teamDto = new TeamDto();
        teamDto.setAgeCategory(team.getAgeGroup().toString());
        teamDto.setName(team.getName());

        when(coachService.findById(coach2Dto.getId())).thenReturn(coach2);
        when(playerService.findById(playerDto.getId())).thenReturn(player);

        when(rosterService.getAllowedTeams(coach2, player))
                .thenReturn(Collections.singletonList(team));
        when(mappingService.mapTo(Collections.singletonList(team), TeamDto.class))
                .thenReturn(Collections.singletonList(teamDto));
        Assert.assertEquals(coachFacade.getAllowedTeams(coach2Dto, playerDto), Collections.singletonList(teamDto));
    }
}
