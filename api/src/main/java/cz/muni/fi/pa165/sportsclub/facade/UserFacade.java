package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.UserDto;
import cz.muni.fi.pa165.sportsclub.entity.User;

import java.util.List;

/**
 * @author 422636 Adam Krajcik
 */
public interface UserFacade {

    /**
     *
     * @param userDto
     * @param password
     */
    void registerUser(UserDto userDto, String password);


    /**
     *
     * @param userDto
     */
    void updateUser(UserDto userDto);


    /**
     *
     * @param userDto
     */
    void deleteUser(UserDto userDto);

    /**
     *
     * @param userDto
     * @return
     */
    boolean authenticate(UserDto userDto, String password);


    /**
     *
     * @return
     */
    boolean isAdmin(UserDto userDto);


  
    UserDto getById(Long id);

    List<UserDto> getAll();

    UserDto getByEmail(String email);
}
