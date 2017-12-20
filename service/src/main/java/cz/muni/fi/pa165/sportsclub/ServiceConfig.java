package cz.muni.fi.pa165.sportsclub;

import cz.muni.fi.pa165.sportsclub.mapper.MappingBuilder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({PersistenceSampleApplicationContext.class})
@ComponentScan(basePackages= {"cz.muni.fi.pa165.sportsclub.service", "cz.muni.fi.pa165.sportsclub.facade"})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new MappingBuilder());

        return mapper;
    }
}
