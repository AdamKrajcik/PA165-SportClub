package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.facade.TeamFacade;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.AgeGroupService;
import cz.muni.fi.pa165.sportsclub.service.PlayerService;
import cz.muni.fi.pa165.sportsclub.service.RosterService;
import cz.muni.fi.pa165.sportsclub.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of TeamFacade
 *
 * @author Jan Cech
 */
@Transactional
@Service
public class TeamFacadeImpl implements TeamFacade {

    @Inject
    TeamService teamService;

    @Inject
    PlayerService playerService;

    @Inject
    RosterService rosterService;

    @Inject
    AgeGroupService ageGroupService;

    @Inject
    MappingService mappingService;


    @Override
    public Long createTeam(TeamDto team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }

        Team t = mappingService.mapTo(team, Team.class);
        teamService.createTeam(t);
        return t.getId();
    }

    @Override
    public void updateTeam(TeamDto team) {

        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        if (team.getId() == null) {
            throw new IllegalArgumentException("Team must have not null ID");
        }
        Team t = teamService.findById(team.getId());
        if (t == null) {
            throw new IllegalArgumentException("Team does not exist");
        }

        t = mappingService.mapTo(team, Team.class);
        teamService.updateTeam(t);
    }

    @Override
    public void deleteTeam(TeamDto team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        Team t = teamService.findById(team.getId());
        if (t == null) {
            throw new IllegalArgumentException("Team does not exist");
        }
        teamService.deleteTeam(team.getId());
    }

    @Override
    public void addPlayer(PlayerDto player, TeamDto team, int jerseyNumber) {
        if (team == null || player == null) {
            throw new IllegalArgumentException("Neither team nor player cannot be null");
        }


        Player p = mappingService.mapTo(player, Player.class);
        Team t = mappingService.mapTo(team, Team.class);
        AgeGroup a = ageGroupService.ageGroupForBirthDate(p.getDateOfBirth());
        if (a != t.getAgeGroup() && a.oneAbove() != t.getAgeGroup()) {
            throw new IllegalArgumentException("Player cannot play for this team - bad age group");
        }
        if (t.getRosterEntries().stream().anyMatch(e -> e.getPlayer() == p)) {
            throw new IllegalArgumentException("Player already present in team");
        }
        RosterEntry entry = new RosterEntry();


        entry.setJerseyNumber(jerseyNumber);
        entry.setPlayer(p);
        entry.setTeam(t);
        rosterService.createEntry(entry);
        p.addRosterEntry(entry);
        playerService.updatePlayer(p);
        t.addRosterEntry(entry);
        teamService.updateTeam(t);
    }

    @Override
    public void removePlayer(PlayerDto player, TeamDto team) {
        if (team == null || player == null) {
            throw new IllegalArgumentException("Neither team nor player cannot be null");
        }
        Team t = teamService.findById(team.getId());
        Player p = playerService.findById(player.getId());
        Optional<RosterEntry> r =
                t.getRosterEntries()
                        .stream()
                        .filter(rosterEntry -> rosterEntry.getPlayer() == p)
                        .findAny();

        if (r.isPresent()) {
            RosterEntry rosterEntry = r.get();
            t.removeRosterEntry(rosterEntry);
            teamService.updateTeam(t);
            p.removeRosterEntry(rosterEntry);
            playerService.updatePlayer(p);
            rosterService.deleteEntry(rosterEntry.getId());
        } else {
            throw new IllegalArgumentException("Player " + player.toString() +
                    " doesn't play for team " + team.toString());
        }
    }

    @Override
    public void updateJerseyNumber(PlayerDto player, TeamDto team, int jerseyNumber) {
        if (team == null || player == null) {
            throw new IllegalArgumentException("Neither team nor player cannot be null");
        }
        Team t = teamService.findById(team.getId());
        Player p = playerService.findById(player.getId());
        Optional<RosterEntry> r =
                t.getRosterEntries()
                        .stream()
                        .filter(rosterEntry -> rosterEntry.getPlayer() == p)
                        .findAny();

        if (r.isPresent()) {
            RosterEntry rosterEntry = r.get();
            rosterEntry.setJerseyNumber(jerseyNumber);
            rosterService.updateEntry(rosterEntry);
        } else {
            throw new IllegalArgumentException("Player " + player.toString() +
                    " doesn't play for team " + team.toString());
        }
    }

    @Override
    public TeamDto getTeam(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return mappingService.mapTo(teamService.findById(id), TeamDto.class);
    }

    @Override
    public TeamDto getTeamByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return mappingService.mapTo(teamService.findByName(name), TeamDto.class);
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return mappingService.mapTo(teamService.getAll(), TeamDto.class);
    }

    @Override
    public List<PlayerDto> getAllowedPlayers(TeamDto team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }

        Team t = teamService.findById(team.getId());
        if (t == null) {
            throw new IllegalArgumentException("Team not found in system");
        }

        return mappingService.mapTo(rosterService.getAllowedPlayers(t), PlayerDto.class);
    }
}
