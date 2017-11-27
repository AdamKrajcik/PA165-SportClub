package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Team;

import java.util.List;

/**
 * Facade for team
 *
 * @author 422636 Adam Krajcik
 */
public interface TeamFacade {

    Long createTeam(TeamDto team);

    void updateTeam(TeamDto team);

    void deleteTeam(TeamDto team);

    void addPlayer(PlayerDto player, TeamDto team, int jerseyNumber);

    void removePlayer(PlayerDto player, TeamDto Team);

    void updateJerseyNumber(PlayerDto player, TeamDto team, int jerseyNumber);

    TeamDto getTeam(Long id);

    TeamDto getTeamByName(String name);

    List<TeamDto> getAllTeams();

    List<PlayerDto> getAllowedPlayers(TeamDto team);
}
