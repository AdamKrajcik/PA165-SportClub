package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.dao.CoachDao;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for Coach service
 *
 * @author 445403 Kristian Katanik
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class CoachServiceTest extends AbstractTestNGSpringContextTests {

    private Coach coach1;
    private Coach coach2;
    private Coach coach3;

    @Mock
    private CoachDao coachDao;

    @Inject
    @InjectMocks
    private CoachService coachService;

    @BeforeMethod
    public void createCoaches(){

        coach1 = new Coach();
        coach1.setEmail("coach1@gmail.com");
        coach1.setFirstName("Jan");
        coach1.setLastName("Novak");

        coach2 = new Coach();
        coach2.setEmail("coach2@gmail.com");
        coach2.setFirstName("Peter");
        coach2.setLastName("Stary");

        coach3 = new Coach();
        coach3.setEmail("coach3@gmail.com");
        coach3.setFirstName("Pavol");
        coach3.setLastName("Mlady");

    }


    @BeforeClass
    public void setUpClass() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCoachTest() {
        coach1.setId(1L);
        coachService.createCoach(coach1);
        Mockito.verify(coachDao, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    public void deleteCoachTest() {
        coach1.setId(1L);
        coachService.deleteCoach(coach1);
        Mockito.verify(coachDao, Mockito.times(1)).delete(Mockito.anyObject());
    }

    @Test
    public void updateCoachTest() {
        coach1.setId(1L);
        coach1.setFirstName("Jozef");
        coachService.updateCoach(coach1);
        Mockito.verify(coachDao, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    public void findCoachByIdTest() {
        coach2.setId(1L);
        Mockito.when(coachDao.findById(Mockito.anyLong()))
                .thenReturn(coach2);

        Coach result = coachService.findById(1L);

        Mockito.verify(coachDao, Mockito.times(1)).findById(Mockito.anyLong());
        Assert.assertNotNull(result);
    }

    @Test
    public void findCoachByEmailTest() {

        Mockito.when(coachDao.findByEmail(Mockito.anyString()))
                .thenReturn(coach1);

        Coach result = coachService.findByEmail("coach1@gmail.com");

        Mockito.verify(coachDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Assert.assertNotNull(result);
    }

    @Test
    public void findAllCoachesTest() {
        List<Coach> result = new ArrayList<>();
        result.add(coach1);
        result.add(coach2);
        result.add(coach3);
        Mockito.when(coachDao.findAll()).thenReturn(result);

        List<Coach> test = coachService.getAll();

        Mockito.verify(coachDao, Mockito.times(1)).findAll();
        Assert.assertNotNull(test);
        Assert.assertEquals(3, test.size());
    }


}
