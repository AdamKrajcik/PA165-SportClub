package cz.muni.fi.pa165.sportsclub;

import cz.muni.fi.pa165.sportsclub.config.SampleDataConfiguration;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.service.CoachService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.RosterService;
import cz.muni.fi.pa165.sportsclub.service.TeamService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author 445403 Kristian Katanik
 */
@ContextConfiguration(classes = {SampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoaderTest extends AbstractTestNGSpringContextTests {

    @Inject
    private TeamService teamService;

    @Inject
    private PlayerService playerService;

    @Inject
    private CoachService coachService;

    @Inject
    private RosterService rosterService;



    @Test
    public void testLoad() throws IOException, ParseException {

        List<Team> teams = teamService.getAll();
        List<Coach> coaches = coachService.getAll();
        List<RosterEntry> entries = rosterService.findAll();
        List<Player> players = playerService.getAll();

        assertThat(teams).isNotNull();
        assertThat(teams.size()).isEqualTo(2);

        assertThat(coaches).isNotNull();
        assertThat(coaches.size()).isEqualTo(2);

        assertThat(entries).isNotNull();
        assertThat(entries.size()).isEqualTo(4);

        assertThat(players).isNotNull();
        assertThat(players.size()).isEqualTo(6);

    }

}
