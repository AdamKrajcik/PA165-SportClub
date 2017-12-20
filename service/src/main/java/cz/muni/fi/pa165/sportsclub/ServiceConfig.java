package cz.muni.fi.pa165.sportsclub;

import cz.muni.fi.pa165.sportsclub.mapper.AgeGroupCustomConverter;
import cz.muni.fi.pa165.sportsclub.mapper.MappingBuilder;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Import({PersistenceSampleApplicationContext.class})
@ComponentScan(basePackages= {"cz.muni.fi.pa165.sportsclub.service", "cz.muni.fi.pa165.sportsclub.facade"})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper mapper = new DozerBeanMapper();

        Map<String, CustomConverter> converters = new HashMap<>();
        converters.put("ageGroup", new AgeGroupCustomConverter());
        mapper.setCustomConvertersWithId(converters);

        mapper.addMapping(new MappingBuilder());

        return mapper;
    }
}
