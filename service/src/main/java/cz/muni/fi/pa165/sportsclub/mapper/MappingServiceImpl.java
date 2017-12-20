package cz.muni.fi.pa165.sportsclub.mapper;

import cz.muni.fi.pa165.sportsclub.dto.RosterEntryDto;
import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class MappingServiceImpl implements MappingService {

    @Inject
    private Mapper dozer;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return dozer.map(u, mapToClass);
    }


//    public TeamDto teamToDto(Team team) {
//        TeamDto dto = new TeamDto();
//        dozer.map(team, dto);
//        Set<RosterEntryDto> roster = new = dozer.map(team.getRosterEntries());
//    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}
