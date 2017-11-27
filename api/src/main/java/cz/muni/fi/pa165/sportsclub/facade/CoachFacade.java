package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.dto.PlayerDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;

import java.util.List;

/**
 * Facade for coach
 *
 * @author 422636 Adam Krajcik
 */
public interface CoachFacade {

    Long createCoach(CoachDto coach);

    void updateCoach(CoachDto coach);

    void deleteCoach(Long coachId);

    CoachDto getCoach(Long id);

    CoachDto getCoachByEmail(String email);

    List<CoachDto> getAllCoaches();

    List<TeamDto> getAllowedTeams(CoachDto coach, PlayerDto player);


}
