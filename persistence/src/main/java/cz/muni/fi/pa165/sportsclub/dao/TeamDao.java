package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.Team;

import java.util.List;

/**
 * Database operations interface for {@link Team}
 *
 * @author 410461 Martin Skrovina
 */
public interface TeamDao {

    /**
     * Stores team into database
     *
     * @param team to be stored
     */
    void create(Team team);

    /**
     * Update existing team in database
     *
     * @param team to be updated
     */
    void update(Team team);

    /**
     * Delete existing team from database
     *
     * @param team to be deleted
     */
    void delete(Team team);

    /**
     * Retrieves team by its unique id
     *
     * @param id of team
     * @return team object with specified id if exists, null otherwise
     */
    Team findById(Long id);

    /**
     * Retrieves all team objects
     *
     * @return all teams
     */
    List<Team> findAll();
}
