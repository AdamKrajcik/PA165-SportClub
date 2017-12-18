package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.User;

import java.util.List;

/**
 * User service interface
 *
 * @author 422636 Adam Krajcik
 */
public interface UserService {

    /**
     * Creates new user.
     *
     * @param user user to create
     */
    void registerUser(User user, String password);

    /**
     * Updates user.
     *
     * @param user user with the updated data
     */
    void updateUser(User user);

    /**
     * Deletes user.
     *
     * @param id ID of the user to remove
     */
    void deleteUser(long id);

    /**
     * Finds user by specified ID.
     *
     * @param id ID of the user
     * @return return user with the given ID
     */
    User findById(long id);

    /**
     * Finds user by specified email.
     *
     * @param email email of the user
     * @return return user with the given email
     */
    User findByEmail(String email);

    /**
     * Returns all users.
     *
     * @return list of all users
     */
    List<User> getAll();


    /**
     * authenticate user with password
     * @param u User
     * @param passwordHash hash
     * @return
     */
    boolean authenticate(User u, String passwordHash);

    /**
     * if is admin
     * @return
     */
    boolean isAdmin(User user);

}
