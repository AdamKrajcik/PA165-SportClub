package cz.muni.fi.pa165.sportsclub;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages={"cz.muni.fi.pa165.sportsclub"})
public class ServiceConfig {

    @Bean
    public Mapper dozer(){
        return new DozerBeanMapper();
    }
}
