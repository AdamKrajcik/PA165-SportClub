package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.*;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * Implementation for AgeGroup service
 *
 * @author 410461 Martin Skrovina
 */
@Service
public class AgeGroupServiceImpl implements AgeGroupService {

    @Inject
    TimeService timeService;


    @Override
    public AgeGroup ageGroupForBirthDate(Date birthDate) {
        ZoneId zone = ZoneId.of("UTC");

        List<AgeGroup> sorted = AgeGroup.getAllAscending();

        LocalDate today = timeService.getCurrentTime()
                .toInstant()
                .atZone(zone)
                .toLocalDate();
        LocalDate birthLocalDate = birthDate
                .toInstant()
                .atZone(zone)
                .toLocalDate();

        int years = (int) ChronoUnit.YEARS.between(birthLocalDate, today);

        Optional<AgeGroup> ageGroup = sorted.stream()
                .filter(group -> years >= group.getLowerBoundary()
                        && years <= group.getUpperBoundary())
                .findFirst();

        return ageGroup.isPresent()
                ? ageGroup.get()
                : null;
    }

    @Override
    public TimeSpan getTimeSpan(AgeGroup ageGroup) {
        ZoneId zone = ZoneId.of("UTC");

        int lowerBound = ageGroup.getLowerBoundary();
        int upperBound = ageGroup.getUpperBoundary();

        LocalDate today = timeService.getCurrentTime()
                .toInstant()
                .atZone(zone)
                .toLocalDate();

        Date youngerDate = Date.from(today
                .minusYears(lowerBound)
                .plusDays(1)
                .atStartOfDay()
                .minus(1, ChronoUnit.MILLIS).toInstant(ZoneOffset.UTC));
        Date olderDate = Date.from(today
                .minusYears(upperBound + 1)
                .plusDays(1)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC));

        return new TimeSpan(olderDate, youngerDate);
    }
}
