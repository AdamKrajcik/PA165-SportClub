package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.dao.PlayerDao;
import cz.muni.fi.pa165.sportsclub.dao.RosterEntryDao;
import cz.muni.fi.pa165.sportsclub.entity.Coach;
import cz.muni.fi.pa165.sportsclub.entity.Player;
import cz.muni.fi.pa165.sportsclub.entity.RosterEntry;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation for Roster service
 *
 * @author 410461 Martin Skrovina
 */
@Service
public class RosterServiceImpl implements RosterService {
    @Inject
    RosterEntryDao rosterEntryDao;

    @Inject
    PlayerDao playerDao;

    @Inject
    AgeGroupService ageGroupService;


    @Override
    public void createEntry(RosterEntry entry) {
        rosterEntryDao.create(entry);
    }

    @Override
    public void updateEntry(RosterEntry entry) {
        rosterEntryDao.update(entry);
    }

    @Override
    public void deleteEntry(long id) {
        RosterEntry rosterEntry = rosterEntryDao.findById(id);
        if (rosterEntry == null) {
            throw new IllegalStateException();
        }
        rosterEntryDao.delete(rosterEntry);
    }

    @Override
    public RosterEntry findById(long id) {
        return rosterEntryDao.findById(id);
    }

    @Override
    public List<RosterEntry> findAll() {
        return rosterEntryDao.findAll();
    }

    @Override
    public List<Team> getAllowedTeams(Coach coach, Player player) {
        Set<Team> teams = coach.getTeams();

        AgeGroup ageGroup = ageGroupService.ageGroupForBirthDate(player.getDateOfBirth());
        AgeGroup oneAboveAgeGroup = ageGroup.oneAbove();

        return teams
                .stream()
                .filter(team -> (team.getAgeGroup() == ageGroup
                            || (oneAboveAgeGroup != null && oneAboveAgeGroup == team.getAgeGroup()))
                        && team
                            .getRosterEntries()
                            .stream()
                            .allMatch(rosterEntry -> rosterEntry.getPlayer() != player)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> getAllowedPlayers(Team team) {
        AgeGroup base = team.getAgeGroup();
        AgeGroup oneAbove = base.oneAbove();

        TimeSpan baseSpan = ageGroupService.getTimeSpan(base);
        TimeSpan oneAboveSpan = oneAbove == null
                ? null
                : ageGroupService.getTimeSpan(oneAbove);

        Set<RosterEntry> teamRosterEntries = team.getRosterEntries();

        List<Player> players = playerDao.findByBirthDate(
                oneAboveSpan == null
                        ? baseSpan.getFrom()
                        : oneAboveSpan.getFrom(),
                baseSpan.getTo()
        )
        .stream()
        .filter(p -> teamRosterEntries.stream().allMatch(r -> r.getPlayer() != p))
        .collect(Collectors.toList());

        return players;
    }
}
