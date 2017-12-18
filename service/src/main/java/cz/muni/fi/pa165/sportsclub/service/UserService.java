package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Creates new user.
     *
     * @param user player to create
     */
    void registerUser(User user, String password);

    /**
     * Updates user.
     *
     * @param user player with the updated data
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
    List<User> findAll();


    /**
     * authenticate
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
