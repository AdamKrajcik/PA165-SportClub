package cz.muni.fi.pa165.sportsclub.mapper;

import cz.muni.fi.pa165.sportsclub.dto.TeamDto;
import cz.muni.fi.pa165.sportsclub.entity.Team;
import org.dozer.loader.api.BeanMappingBuilder;

import static org.dozer.loader.api.FieldsMappingOptions.customConverterId;

/**
 * @author skrovina
 */
public class MappingBuilder extends BeanMappingBuilder {
    protected void configure() {
        mapping(Team.class,
                TeamDto.class)
                .fields("rosterEntries", "roster")
                .fields("ageGroup",
                        "ageCategory",
                        customConverterId("ageGroup"));
    }
}
