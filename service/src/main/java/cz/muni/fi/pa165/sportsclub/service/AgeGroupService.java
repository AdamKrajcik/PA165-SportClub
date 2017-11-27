package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;

import java.util.Date;
import java.util.List;

/**
 * Interface for AgeGroup service
 *
 * @author 410461 Martin Skrovina
 */
public interface AgeGroupService {

    AgeGroup ageGroupForBirthDate(Date birthDate);

    TimeSpan getTimeSpan(AgeGroup ageGroup);
}
