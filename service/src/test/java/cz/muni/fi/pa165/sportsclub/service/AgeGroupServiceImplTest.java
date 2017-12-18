package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.EntityFactory;
import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import cz.muni.fi.pa165.sportsclub.utils.TimeSpan;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.Date;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


/**
 * Tests for AgeGroup service
 *
 * @author 410461 Martin Skrovina
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class AgeGroupServiceImplTest extends AbstractTestNGSpringContextTests {

    @Inject
    @InjectMocks
    AgeGroupService ageGroupService;

    @Mock
    TimeService timeService;

    @BeforeClass
    public void setUpClass() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setUp() throws Exception {
        when(timeService.getCurrentTime()).thenReturn(EntityFactory.getConstantCurrentTime());
    }

    @Test
    public void testAgeGroupForBirthDate() throws Exception {
        // Will get into the group in a millisec
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1995-08-27T23:59:59.999Z")))
        ).isNotEqualTo(AgeGroup.M20);

        // Just got into the group this millisec
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1995-08-28T00:00:00Z")))
        ).isEqualTo(AgeGroup.M20);

        // Will get out of the group in a millisec
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1999-08-27T23:59:59.999Z")))
        ).isEqualTo(AgeGroup.M20);

        // Just got out of the group before a millisec
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1999-08-28T00:00:00.000Z")))
        ).isNotEqualTo(AgeGroup.M20);

        // Inside the age group with a reserve
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1997-04-12T11:35:22.236Z")))
        ).isEqualTo(AgeGroup.M20);

        // Outside the age group with a reserve (much younger)
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("2006-01-03T10:22:01.003Z")))
        ).isNotEqualTo(AgeGroup.M20);

        // Outside the age group with a reserve (much older)
        assertThat(ageGroupService.ageGroupForBirthDate(
                Date.from(Instant.parse("1963-11-01T01:57:02.785Z")))
        ).isNotEqualTo(AgeGroup.M20);
    }

    @Test
    public void testGetTimeSpan() throws Exception {
        TimeSpan timeSpan = ageGroupService.getTimeSpan(AgeGroup.M20);

        assertThat(timeSpan.getFrom()).isEqualTo(Date.from(Instant.parse("1995-08-28T00:00:00Z")));
        assertThat(timeSpan.getTo()).isEqualTo(Date.from(Instant.parse("1999-08-27T23:59:59.999Z")));
    }
}