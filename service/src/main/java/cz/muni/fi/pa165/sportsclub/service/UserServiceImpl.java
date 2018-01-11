package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.UserDao;
import cz.muni.fi.pa165.sportsclub.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

/**
 * @author 422636 Adam Krajcik
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private PasswordEncoder passwordEncoder;


    @Override
    public void registerUser(User user, String password) {
        user.setPasswordHash(passwordEncoder.encode(password));
        userDao.create(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.delete(userDao.findById(id));
    }

    @Override
    public User findById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public boolean authenticate(User u, String passwordHash) {
        return passwordEncoder.matches(passwordHash, u.getPasswordHash());
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getRole().equals("ADMIN");
    }
}
