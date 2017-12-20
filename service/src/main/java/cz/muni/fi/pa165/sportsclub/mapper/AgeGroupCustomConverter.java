package cz.muni.fi.pa165.sportsclub.mapper;

import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;
import org.dozer.DozerConverter;

/**
 * @author skrovina
 */
public class AgeGroupCustomConverter extends DozerConverter<AgeGroup, String> {

    public AgeGroupCustomConverter() {
        super(AgeGroup.class, String.class);
    }

    @Override
    public String convertTo(AgeGroup ageGroup, String s) {
        return ageGroup.toString();
    }

    @Override
    public AgeGroup convertFrom(String s, AgeGroup ageGroup) {
        return AgeGroup.valueOf(s);
    }
}
