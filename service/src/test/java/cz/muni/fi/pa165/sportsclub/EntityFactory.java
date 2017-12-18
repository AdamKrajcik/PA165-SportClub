package cz.muni.fi.pa165.sportsclub;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.UserDto;
import cz.muni.fi.pa165.sportsclub.entity.*;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class EntityFactory {

    public static User createUser() {
        User user = new User();
        user.setEmail("admin@admin.cz");
        user.setPasswordHash("hohoho");
        user.setRole("ADMIN");
        return user;
    }

    public static UserDto createUserDto() {
        UserDto user = new UserDto();
        user.setEmail("admin@admin.cz");
        user.setPasswordHash("hohoho");
        user.setRole("ADMIN");
        return user;
    }

    public static Player createPlayer() {
        Player player = new Player();
        player.setFirstName("John");
        player.setLastName("Smith");
        player.setEmail("john.smith@smith.com");
        player.setHeight(180);
        player.setWeight(90);
        player.setDateOfBirth(get_past());
        return player;
    }

    public static Player createPlayer(String lastName, String birth) {
        Player player = new Player();
        player.setFirstName("Testie");
        player.setLastName(lastName);
        player.setEmail(lastName + "@example.com");
        player.setHeight(180);
        player.setWeight(90);

        player.setDateOfBirth(Date.from(Instant.parse(birth)));

        return player;
    }

    public static PlayerDto createPlayerDto() {
        PlayerDto player = new PlayerDto();
        player.setFirstName("John");
        player.setLastName("Smith");
        player.setEmail("john.smith@smith.com");
        player.setHeight(180);
        player.setWeight(90);
        player.setDateOfBirth(get_past());
        return player;
    }

    private static Date get_past() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1994, Calendar.APRIL, 15);
        return calendar.getTime();
    }

    public static Coach createCoach() {
        Coach coach = new Coach();
        coach.setFirstName("Richard");
        coach.setLastName("Appleseed");
        coach.setEmail("appleseed@example.com");

        return coach;
    }

    public static Team createTeam() {
        Team team = new Team();
        team.setName("Real Madrid");
        team.setAgeGroup(AgeGroup.M20);

        return team;
    }

    public static Team createTeam(String name, AgeGroup ageGroup, Coach coach) {
        Team team = new Team();
        team.setName(name);
        team.setAgeGroup(ageGroup);
        team.setCoach(coach);

        return team;
    }

    public static RosterEntry createRosterEntry() {
        RosterEntry entry = new RosterEntry(createTeam(), createPlayer(), 9);

        return entry;
    }

    public static Date getConstantCurrentTime() {
        return Date.from(Instant.parse("2016-08-27T00:00:00Z"));
    }

    public static Player createPlayerM16(String lastName) {
        return EntityFactory.createPlayer(lastName, "2004-07-25T00:00:00Z");
    }

    public static Player createPlayerM20(String lastName) {
        return EntityFactory.createPlayer(lastName, "1997-02-13T00:00:00Z");
    }

    public static Player createPlayerM24(String lastName) {
        return EntityFactory.createPlayer(lastName, "1994-03-19T00:00:00Z");
    }

    public static Player createPlayerMS(String lastName) {
        return EntityFactory.createPlayer(lastName, "1989-03-05T00:00:00Z");
    }
}
