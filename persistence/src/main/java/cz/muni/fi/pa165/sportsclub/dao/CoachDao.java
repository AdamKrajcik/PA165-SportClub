package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.Coach;

import java.util.List;

/**
 * DAO interface for entity {@link Coach}
 * @author Jan Cech
 */
public interface CoachDao {

    /**
     * Creates new coach
     * @param coach coach to be created
     */
    void create(Coach coach);

    /**
     * Updates coach
     * @param coach coach to be updated
     */
    void update(Coach coach);

    /**
     * Deletes a coach
     * @param coach coach to be deleted
     */
    void delete(Coach coach);

    /**
     * Finds a coach with given ID
     * @param id ID of coach
     * @return coach with given ID
     */
    Coach findCoachById(Long id);

    /**
     * Get all coaches
     * @return all coaches that are currently in db
     */
    List<Coach> findAllCoaches();

    /**
     * Finds a coach with given email
     * @param email email of coach
     * @return coach with given ID
     */
    Coach findCoachByEmail(String email);
}
