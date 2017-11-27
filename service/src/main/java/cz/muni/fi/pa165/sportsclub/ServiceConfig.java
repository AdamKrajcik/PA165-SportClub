package cz.muni.fi.pa165.sportsclub;

import cz.muni.fi.pa165.sportsclub.facade.PlayerFacadeImpl;
import cz.muni.fi.pa165.sportsclub.service.PlayerServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses= {PlayerFacadeImpl.class, PlayerServiceImpl.class})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }
}
