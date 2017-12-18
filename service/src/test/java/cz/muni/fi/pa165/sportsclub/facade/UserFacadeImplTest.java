package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.dto.UserDto;
import cz.muni.fi.pa165.sportsclub.entity.User;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfig.class)
public class UserFacadeImplTest extends AbstractTestNGSpringContextTests {

    private User admin;
    private UserDto adminDto;
    private String password = "password";

    @Mock
    private MappingService mappingService;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFacade userFacade = new UserFacadeImpl();

    @BeforeMethod
    public void setUp() throws Exception {
        admin = EntityFactory.createUser();
        adminDto = EntityFactory.createUserDto();
        admin.setId(1L);
        adminDto.setId(1L);
        when(mappingService.mapTo(adminDto, User.class)).thenReturn(admin);
        when(mappingService.mapTo(admin, UserDto.class)).thenReturn(adminDto);
        when(userService.authenticate(admin, password)).thenReturn(true);
    }

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser() throws Exception {
        userFacade.registerUser(adminDto, password);
        verify(userService).registerUser(admin, password);
    }

    @Test
    public void testUpdateUser() throws Exception {
        userFacade.updateUser(adminDto);
        verify(userService).updateUser(admin);
    }

    @Test
    public void testDeleteUser() throws Exception {
        userFacade.deleteUser(adminDto);
        verify(userService).deleteUser(1L);
    }

    @Test
    public void testAuthenticate() throws Exception {
        assertThat(userFacade.authenticate(adminDto, password)).isTrue();
        verify(userService).authenticate(admin, password);
    }

    @Test
    public void testGetById() throws Exception {
        when(userService.findById(1L)).thenReturn(admin);
        assertThat(userFacade.getById(1L)).isEqualTo(adminDto);
        verify(userService).findById(1L);
    }

    @Test
    public void testGetAll() throws Exception {
        when(userService.getAll()).thenReturn(Collections.singletonList(admin));
        List all = userFacade.getAll();
        assertThat(all.size());
        verify(userService).getAll();
    }

    @Test
    public void testGetByEmail() throws Exception {
        when(userService.findByEmail(admin.getEmail())).thenReturn(admin);
        assertThat(userFacade.getByEmail(adminDto.getEmail())).isEqualTo(adminDto);
        verify(userService).findByEmail(admin.getEmail());
    }

}