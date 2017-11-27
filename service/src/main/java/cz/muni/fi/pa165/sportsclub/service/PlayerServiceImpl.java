package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsclub.dao.RosterEntryDao;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Inject
    private PlayerDao playerDao;

    @Inject
    private RosterEntryDao rosterEntryDao;

    @Override
    public void createPlayer(Player player) {
        playerDao.create(player);
    }

    @Override
    public void updatePlayer(Player player) {
        playerDao.update(player);
    }

    @Override
    public void deletePlayer(long id) {
        Player p = playerDao.findById(id);
        if (p == null) {
            throw new IllegalStateException();
        }

        p.getRosterEntries().stream().forEach(entry -> rosterEntryDao.delete(entry));

        playerDao.delete(p);
    }

    @Override
    public Player findById(long id) {
        return playerDao.findById(id);
    }

    @Override
    public Player findByEmail(String email) {
        return playerDao.findByEmail(email);
    }

    @Override
    public List<Player> getAll() {
        return playerDao.findAll();
    }
}
