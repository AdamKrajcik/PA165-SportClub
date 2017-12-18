package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.dao.UserDao;
import cz.muni.fi.pa165.sportsclub.entity.User;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceImplTest extends AbstractTestNGSpringContextTests {

    private User admin;
    private User createdAdmin;

    @Mock
    private UserDao userDao;

    @Inject
    @InjectMocks
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        admin = EntityFactory.createUser();
        createdAdmin = EntityFactory.createUser();
        createdAdmin.setId(1L);
        createdAdmin.setPasswordHash("1000:fcba386528c0da07738778c28868d706dada639d29106445:ad8e8627db2e681debe75b750376c1b52f0f52bfb01addc1");
    }

    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testRegisterUser() throws Exception {
        userService.registerUser(admin, "123456789");
        verify(userDao, atLeastOnce()).create(admin);
    }

    @Test
    public void testUpdateUser() throws Exception {
        userService.updateUser(admin);
        verify(userDao).update(admin);
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(userDao.findById(1L)).thenReturn(createdAdmin);
        userService.deleteUser(1L);
        verify(userDao).delete(createdAdmin);
    }

    @Test
    public void testFindById() throws Exception {
        when(userDao.findById(1L)).thenReturn(createdAdmin);
        assertThat(userDao.findById(1L)).isEqualTo(createdAdmin);
    }

    @Test
    public void testFindByEmail() throws Exception {
        when(userDao.findByEmail(admin.getEmail())).thenReturn(createdAdmin);
        assertThat(userDao.findByEmail(admin.getEmail())).isEqualTo(createdAdmin);
    }

    @Test
    public void testAuthenticate() throws Exception {
        userService.registerUser(admin, "password");
        assertThat(userService.authenticate(admin, "password")).isTrue();
        assertThat(userService.authenticate(admin, "wrong password")).isFalse();
    }

    @Test
    public void testIsAdmin() throws Exception {
        assertThat(userService.isAdmin(createdAdmin)).isTrue();
    }
}