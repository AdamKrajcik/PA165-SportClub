package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.User;

import java.util.List;

/**
 * Represent user dao
 *
 * @author 422636 Adam Krajcik
 */
public interface UserDao {
    /**
     * creates user
     *
     * @param user
     */
    void create(User user);

    /**
     * updates user
     *
     * @param user
     * @return
     */
    void update(User user);

    /**
     * deletes user
     * @param user
     */
    void delete(User user);

    /**
     * finds by id
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * finds by email
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * get all
     * @return
     */
    List<User> getAll();
}
