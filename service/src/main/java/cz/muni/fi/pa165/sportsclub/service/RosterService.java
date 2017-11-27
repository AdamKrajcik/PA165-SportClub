package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;

import java.util.List;

/**
 * Interface for Roster service.
 *
 * @author 422636 Adam Krajcik
 */
public interface RosterService {

    /**
     * Creates new roster entry
     *
     * @param entry entry to create
     */
    void createEntry(RosterEntry entry);

    /**
     * Updates roster entry
     *
     * @param entry entry with the updated data
     */
    void updateEntry(RosterEntry entry);

    /**
     * Removes roster entry
     *
     * @param id ID of the entry to delete
     */
    void deleteEntry(long id);

    /**
     * Finds Membership by specified id
     *
     * @param id Id of the Membership
     * @return Return Membership with the given id
     */
    RosterEntry findById(long id);

    /**
     * Returns all roster entries.
     *
     * @return list of all entries
     */
    List<RosterEntry> findAll();

    /**
     * Returns coach's teams in which the player can participate
     *
     * @param player to be queried
     * @return Teams
     */
    List<Team> getAllowedTeams(Coach coach, Player player);

    /**
     * Returns players that can be added to the team
     *
     * @param team to be queried
     * @return Players
     */
    List<Player> getAllowedPlayers(Team team);
}
