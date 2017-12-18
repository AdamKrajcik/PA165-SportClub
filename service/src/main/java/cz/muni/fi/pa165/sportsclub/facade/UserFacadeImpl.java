package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.UserDto;
import cz.muni.fi.pa165.sportsclub.entity.User;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Inject
    private UserService userService;

    @Inject
    private MappingService mappingService;


    @Override
    public void registerUser(UserDto userDto, String password) {
        User user = mappingService.mapTo(userDto, User.class);
        userService.registerUser(user, password);
        userDto.setId(user.getId());
    }


    @Override
    public void updateUser(User userDto) {
        userService.updateUser(mappingService.mapTo(userDto, User.class));
    }

    @Override
    public void deleteUser(UserDto userDto) {
        userService.deleteUser(userDto.getId());
    }

    @Override
    public boolean authenticate(UserDto userDto) {
        return userService.authenticate(
                userService.findById(userDto.getId()), userDto.getPasswordHash());    }

    @Override
    public boolean isAdmin(UserDto userDto) {
        return userService.isAdmin(mappingService.mapTo(userDto, User.class));
    }

    @Override
    public UserDto getById(Long id) {
        User user = userService.findById(id);
        return (user == null) ? null : mappingService.mapTo(user, UserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        return mappingService.mapTo(userService.findAll(), UserDto.class);
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userService.findByEmail(email);
        return (user == null) ? null : mappingService.mapTo(user, UserDto.class);    }
}
