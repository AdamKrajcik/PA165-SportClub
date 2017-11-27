package cz.muni.fi.pa165.sportsclub.facade;

import cz.muni.fi.pa165.sportsclub.dto.CoachDto;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.mapper.MappingService;
import cz.muni.fi.pa165.sportsclub.service.CoachService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of CoachFacade
 *
 * @author 445403 Kristian Katanik
 */
@Transactional
@Service
public class CoachFacadeImpl implements CoachFacade{

    @Inject
    private MappingService mappingService;

    @Inject
    private CoachService coachService;

    @Override
    public long createCoach(CoachDto coach) {

        if(coach == null || coach.getEmail() == null || coach.getLastName() == null || coach.getFirstName() == null){
            throw new IllegalArgumentException("Coach and his attributes must not be null");
        }

        Coach mappedCoach = mappingService.mapTo(coach, Coach.class);

        coachService.createCoach(mappedCoach);
        return mappedCoach.getId();

    }

    @Override
    public void updateCoach(CoachDto coachDto) {

        Coach coach = coachService.findById(coachDto.getId());
        coach.setId(coachDto.getId());
        coach.setFirstName(coachDto.getFirstName());
        coach.setLastName(coachDto.getLastName());
        coachDto.setEmail(coachDto.getEmail());

        coachService.updateCoach(coach);

    }


    @Override
    public void deleteCoach(Long coachId) {

        if(coachId == null){
            throw new IllegalArgumentException("Argument coach cannot be null");
        }

        coachService.deleteCoach(coachService.findById(coachId));

    }

    @Override
    public CoachDto getCoach(Long id) {

        if(id == null){
            throw new IllegalArgumentException("Null id argument.");
        }

        Coach coach = coachService.findById(id);
        return (coach == null) ? null : mappingService.mapTo(coach, CoachDto.class);

    }

    @Override
    public CoachDto getCoachByEmail(String email) {

        if(email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }

        Coach coach = coachService.findByEmail(email);
        return (coach == null) ? null : mappingService.mapTo(coach, CoachDto.class);

    }

    @Override
    public List<CoachDto> getAllCoaches() {

        return mappingService.mapTo(coachService.getAll(), CoachDto.class);

    }

}
