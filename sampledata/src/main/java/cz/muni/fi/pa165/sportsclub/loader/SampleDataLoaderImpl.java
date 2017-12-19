package cz.muni.fi.pa165.sportsclub.loader;

import cz.muni.fi.pa165.sportsclub.entity.*;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 445403 Kristian Katanik
 */

@Component
@Transactional
public class SampleDataLoaderImpl implements SampleDataLoader{

    @Inject
    private TeamService teamService;

    @Inject
    private PlayerService playerService;

    @Inject
    private CoachService coachService;

    @Inject
    private RosterService rosterService;

    @Inject
    private UserService userService;


    @Override
    public void loadData() throws IOException, ParseException {

        User admin = new User();
        admin.setEmail("admin@admin.cz");
        admin.setRole("ADMIN");
        admin.setPasswordHash("pass");

        User coach = new User();
        coach.setEmail("coach@coach.cz");
        coach.setRole("COACH");
        coach.setPasswordHash("pass");

        Coach coach1 = new Coach();
        coach1.setFirstName("Laco");
        coach1.setLastName("Doma");
        coach1.setEmail("coach1@gmail.com");

        Coach coach2 = new Coach();
        coach2.setFirstName("Peter");
        coach2.setLastName("Vonku");
        coach2.setEmail("coach2@gmail.com");

        Team team1 = new Team();
        team1.setName("Team1");
        team1.setAgeGroup(AgeGroup.M24);

        Team team2 = new Team();
        team2.setName("Team2");
        team2.setAgeGroup(AgeGroup.M20);

        Player player1 = new Player();
        player1.setFirstName("Jozko");
        player1.setLastName("Mrkvicka");
        player1.setWeight(80);
        player1.setHeight(160);
        player1.setEmail("player1@gmail.com");
        player1.setDateOfBirth(createDate("10/10/1995"));

        Player player2 = new Player();
        player2.setFirstName("Janko");
        player2.setLastName("Rohoz");
        player2.setWeight(90);
        player2.setHeight(170);
        player2.setEmail("player2@gmail.com");
        player2.setDateOfBirth(createDate("10/09/1997"));

        Player player3 = new Player();
        player3.setFirstName("Pavol");
        player3.setLastName("Maly");
        player3.setWeight(85);
        player3.setHeight(185);
        player3.setEmail("player3@gmail.com");
        player3.setDateOfBirth(createDate("15/10/1993"));

        Player player4 = new Player();
        player4.setFirstName("Arnold");
        player4.setLastName("Svarz");
        player4.setWeight(100);
        player4.setHeight(189);
        player4.setEmail("player4@gmail.com");
        player4.setDateOfBirth(createDate("22/01/1994"));

        Player player5 = new Player();
        player5.setFirstName("Karol");
        player5.setLastName("Rolak");
        player5.setWeight(72);
        player5.setHeight(170);
        player5.setEmail("player5@gmail.com");
        player5.setDateOfBirth(createDate("29/11/2000"));

        Player player6 = new Player();
        player6.setFirstName("Ero");
        player6.setLastName("Ore");
        player6.setWeight(60);
        player6.setHeight(150);
        player6.setEmail("player6@gmail.com");
        player6.setDateOfBirth(createDate("30/05/1999"));

        RosterEntry rosterEntry1 = new RosterEntry(team1,player1,10);
        RosterEntry rosterEntry2 = new RosterEntry(team1,player4,50);
        RosterEntry rosterEntry3 = new RosterEntry(team2,player5,44);
        RosterEntry rosterEntry4 = new RosterEntry(team2,player6,22);

        team1.setCoach(coach1);
        team2.setCoach(coach2);
        coach1.addTeam(team1);
        coach2.addTeam(team2);
        team1.addRosterEntry(rosterEntry1);
        team1.addRosterEntry(rosterEntry2);
        team2.addRosterEntry(rosterEntry3);
        team2.addRosterEntry(rosterEntry4);
        player1.addRosterEntry(rosterEntry1);
        player4.addRosterEntry(rosterEntry2);
        player5.addRosterEntry(rosterEntry3);
        player6.addRosterEntry(rosterEntry4);

        userService.registerUser(admin, "admin");
        userService.registerUser(coach, "coach");
        coachService.createCoach(coach1);
        coachService.createCoach(coach2);
        teamService.createTeam(team1);
        teamService.createTeam(team2);
        playerService.createPlayer(player1);
        playerService.createPlayer(player2);
        playerService.createPlayer(player3);
        playerService.createPlayer(player4);
        playerService.createPlayer(player5);
        playerService.createPlayer(player6);
        rosterService.createEntry(rosterEntry1);
        rosterService.createEntry(rosterEntry2);
        rosterService.createEntry(rosterEntry3);
        rosterService.createEntry(rosterEntry4);

    }

    /**
     * Creates Date instance from the given string
     *
     * @param date
     * @return
     * @throws ParseException
     */
    private Date createDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }

}
