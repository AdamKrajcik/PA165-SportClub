package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;

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
public class AgeGroupServiceImpl implements AgeGroupService {

    @Inject
    TimeService timeService;


    @Override
    public List<AgeGroup> getAllAscending() {
        return new ArrayList<>(Arrays.asList(AgeGroup.values()));
    }

    @Override
    public AgeGroup oneAbove(AgeGroup ageGroup) {
        List<AgeGroup> sorted = getAllAscending();

        ListIterator<AgeGroup> iterator = sorted.listIterator(sorted.indexOf(ageGroup));

        return iterator.hasNext()
                ? iterator.next()
                : null;
    }

    @Override
    public AgeGroup oneBelow(AgeGroup ageGroup) {
        List<AgeGroup> sorted = getAllAscending();

        ListIterator<AgeGroup> iterator = sorted.listIterator(sorted.indexOf(ageGroup));

        return iterator.hasPrevious()
                ? iterator.previous()
                : null;
    }

    @Override
    public AgeGroup ageGroupForBirthDate(Date birthDate) {
        ZoneId zone = ZoneId.of("UTC");

        List<AgeGroup> sorted = getAllAscending();

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

        int lowerBound = ageGroup.getUpperBoundary();
        int upperBound = ageGroup.getUpperBoundary();

        LocalDate today = timeService.getCurrentTime()
                .toInstant()
                .atZone(zone)
                .toLocalDate();

        Date lowerBoundDate = Date.from(today.minusYears(lowerBound).atStartOfDay(ZoneId.of("UTC")).toInstant());
        Date upperBoundDate = Date.from(today.minusYears(upperBound).atStartOfDay(ZoneId.of("UTC")).toInstant());

        return new TimeSpan(lowerBoundDate, upperBoundDate);
    }
}
