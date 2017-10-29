package cz.muni.fi.pa165.sportsclub.dao;

import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Implementation of RosterEntryDao
 *
 * @author 445403 Kristián Katanik
 */

@Repository
public class RosterEntryDaoImpl implements RosterEntryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(RosterEntry rosterEntry) {
        em.persist(rosterEntry);
    }

    @Override
    public RosterEntry update(RosterEntry rosterEntry) {
        return em.merge(rosterEntry);
    }

    @Override
    public void remove(RosterEntry rosterEntry) {
        em.remove(rosterEntry);
    }

    @Override
    public RosterEntry findById(Long id) {
        return em.find(RosterEntry.class,id);
    }

    @Override
    public List<RosterEntry> findAll() {
        return em.createQuery("SELECT re FROM RosterEntry re",RosterEntry.class).getResultList();
    }
}
