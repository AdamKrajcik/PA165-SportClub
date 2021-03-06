package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.Team;

import java.util.List;

/**
 * Interface for Team service.
 *
 * @author 422636 Adam Krajcik
 */
public interface TeamService {

    /**
     * Creates new team.
     *
     * @param team team to create
     */
    void createTeam(Team team);

    /**
     * Updates team.
     *
     * @param team team with the updated data
     */
    void updateTeam(Team team);

    /**
     * Deletes team.
     *
     * @param id ID of the team to remove
     */
    void deleteTeam(long id);

    /**
     * Finds team by specified id
     *
     * @param id Id of the team
     * @return Return team with the given id
     */
    Team findById(long id);

    /**
     * Find team by given name
     *
     * @param name name of the team
     * @return team with given name
     */
    Team findByName(String name);

    /**
     * Returns all teams.
     *
     * @return List of all teams
     */
    List<Team> getAll();
}
