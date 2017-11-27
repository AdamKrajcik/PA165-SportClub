package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Implementation of TeamDao
 *
 * @author 410461 Martin Skrovina
 */
@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void create(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        em.persist(team);
    }

    @Override
    public void update(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        em.merge(team);
    }

    @Override
    public void delete(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        em.remove(em.merge(team));
    }

    @Override
    public Team findById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        return em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }

    @Override
    public Team findByName(String name) {
        return em.createQuery("SELECT t FROM Team t WHERE t.name = :name", Team.class)
                .setParameter("name", name).getSingleResult();
    }
}
