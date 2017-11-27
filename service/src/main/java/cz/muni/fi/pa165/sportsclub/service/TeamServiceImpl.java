package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.TeamDao;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of TeamService
 *
 * @author Jan Cech
 */
@Service
public class TeamServiceImpl implements TeamService {


    @Inject
    TeamDao teamDao;

    @Override
    public void createTeam(Team team) {
        teamDao.create(team);
    }

    @Override
    public void updateTeam(Team team) {
        teamDao.update(team);
    }

    @Override
    public void deleteTeam(long id) {
        Team t = teamDao.findById(id);
        teamDao.delete(t);
    }

    @Override
    public Team findById(long id) {
        return teamDao.findById(id);
    }


    @Override
    public List<Team> getAll() {
        return teamDao.findAll();
    }

    @Override
    public Team findByName(String name) {
        return teamDao.findByName(name);
    }
}
