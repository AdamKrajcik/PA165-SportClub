package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.Player;

import java.util.List;

/**
 * Interface for Player service
 *
 * @author 422636 Adam Krajcik
 */
public interface PlayerService {

    /**
     * Creates new player.
     *
     * @param player player to create
     */
    void createPlayer(Player player);

    /**
     * Updates player.
     *
     * @param player player with the updated data
     */
    void updatePlayer(Player player);

    /**
     * Deletes player.
     *
     * @param id ID of the player to remove
     */
    void deletePlayer(long id);

    /**
     * Finds player by specified ID.
     *
     * @param id ID of the player
     * @return return player with the given ID
     */
    Player findById(long id);

    /**
     * Returns all players.
     *
     * @return list of all players
     */
    List<Player> getAll();
}
