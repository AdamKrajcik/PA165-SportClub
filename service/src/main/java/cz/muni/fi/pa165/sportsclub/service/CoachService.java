package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.Coach;

import java.util.List;

/**
 * Interface for Coach service.
 *
 * @author 422636 Adam Krajcik
 */
public interface CoachService {

    /**
     * Creates coach.
     *
     * @param coach coach to be created
     */
    void createCoach(Coach coach);

    /**
     * Updates coach.
     *
     * @param coach coach to be updated
     */
    void updateCoach(Coach coach);

    /**
     * Deletes coach.
     *
     * @param id coach's ID who to be deleted
     */
    void deleteCoach(long id);

    /**
     * Finds team manager by id.
     *
     * @param id coach's ID
     * @return coach with given id
     */
    Coach findById(long id);


    /**
     * Get all coaches.
     *
     * @return list of coaches
     */
    List<Coach> getAll();
}
