package cz.muni.fi.pa165.sportsclub.dao;


import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.Team;

import java.util.List;

/**
 * Database operations interface for {@link Player}
 *
 * @author 422636 Adam Krajcik
 */

public interface PlayerDao {

    /**
     * Stores player into database.
     *
     * @param player who is to be stored
     */
    void create(Player player);

    /**
     * Update existing player in database.
     *
     * @param player who is to be update
     */
    void update(Player player);

    /**
     * Delete existing player from database.
     *
     * @param player who is to be deleted
     */
    void delete(Player player);

    /**
     * Retrieves player by its unique id.
     *
     * @param id of player
     * @return player object with specified id if exists, null otherwise
     */
    Player findById(Long id);

    /**
     * Retrieves all players objects
     *
     * @return all players
     */
    List<Player> findAll();

    /**
     * Retrieves player by email
     *
     * @param email of player
     * @return player with selected email
     */
    Player findByEmail(String email);

    /**
     * Retrieves player's teams
     *
     * @param player player in teams
     * @return teams of player
     */
    public List<Team> findTeamsByPlayer(Player player);
}