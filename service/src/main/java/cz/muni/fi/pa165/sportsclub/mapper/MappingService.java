package cz.muni.fi.pa165.sportsclub.mapper;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

public interface MappingService {

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);

    Mapper getMapper();
}
