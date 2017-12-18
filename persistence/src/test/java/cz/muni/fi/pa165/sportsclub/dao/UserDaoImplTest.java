package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.sportsclub.entity.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User DAO Test
 *
 * @author 422636 Adam Krajcik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoImplTest extends AbstractTestNGSpringContextTests{

    @Inject
    private UserDao userDao;

    @PersistenceContext
    private EntityManager em;

    private User admin;
    private User coach;

    @BeforeMethod
    public void setUp() {
        admin = new User();
        admin.setEmail("admin@hoho.com");
        admin.setPasswordHash("coach");
        admin.setRole("ADMIN");

        coach = new User();
        coach.setEmail("coach@hoho.com");
        coach.setPasswordHash("coach");
        coach.setRole("COACH");
    }
    @Test
    public void testCreate() throws Exception {
        userDao.create(admin);
        Assert.assertEquals(userDao.findById(admin.getId()), admin);
    }

    @Test
    public void testUpdate() throws Exception {
        em.persist(coach);
        em.flush();

        coach.setRole("ADMIN");
        userDao.update(coach);

        Assert.assertEquals(userDao.findById(coach.getId()), coach);
    }

    @Test
    public void testDelete() throws Exception {
        em.persist(admin);
        em.flush();

        userDao.delete(admin);
        Assert.assertNull(userDao.findById(admin.getId()));

    }

    @Test
    public void testFindById() throws Exception {
        em.persist(coach);
        em.flush();

        User coach2 = userDao.findById(coach.getId());
        Assert.assertEquals(coach2, coach);
    }

    @Test
    public void testFindByEmail() throws Exception {
        em.persist(coach);
        em.flush();

        User coach2 = userDao.findByEmail(coach.getEmail());
        Assert.assertEquals(coach2, coach);
    }

    @Test
    public void testGetAll() throws Exception {
        em.persist(admin);
        em.persist(coach);
        em.flush();

        Assert.assertEquals(userDao.getAll().size(), 2);
    }

}