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
    public Team getTeamById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public List<Team> getAllTeams() {
        return em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
    }
}
