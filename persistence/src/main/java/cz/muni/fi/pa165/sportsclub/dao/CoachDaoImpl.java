package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.Coach;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of CoachDAO
 *
 * @author Jan Cech
 */
@Repository
@Transactional
public class CoachDaoImpl implements CoachDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Coach coach) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        em.persist(coach);
    }

    @Override
    public void update(Coach coach) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        em.merge(coach);
    }

    @Override
    public void delete(Coach coach) {
        if (coach == null) {
            throw new IllegalArgumentException("Coach cannot be null");
        }
        em.remove(em.merge(coach));
    }

    @Override
    public Coach findById(Long id) {
        return em.find(Coach.class, id);
    }

    @Override
    public List<Coach> findAll() {
        return em.createQuery("SELECT c FROM Coach c", Coach.class).getResultList();
    }

    @Override
    public Coach findByEmail(String email) {
        return em
                .createQuery("SELECT c FROM Coach c where c.email = :mail", Coach.class)
                .setParameter("mail", email)
                .getSingleResult();
    }

}
