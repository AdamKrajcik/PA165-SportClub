package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.CoachDao;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.exceptions.SportsClubServiceException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of CoachService interface
 *
 * @author 445403 Kristian Katanik
 */

@Service
public class CoachServiceImpl implements CoachService{

    @Inject
    CoachDao coachDao;

    @Override
    public void createCoach(Coach coach) {
        if(coach == null){
            throw new IllegalArgumentException("Coach acnnot be null");
        }
        coachDao.create(coach);
    }

    @Override
    public void updateCoach(Coach coach) {
        coachDao.update(coach);
    }

    @Override
    public void deleteCoach(Coach coach) {
        coachDao.delete(coach);
    }

    @Override
    public Coach findById(long id) {
        return coachDao.findById(id);
    }

    @Override
    public List<Coach> getAll() {
        return coachDao.findAll();
    }

    @Override
    public Coach findByEmail(String email) {
        return coachDao.findByEmail(email);
    }
}
