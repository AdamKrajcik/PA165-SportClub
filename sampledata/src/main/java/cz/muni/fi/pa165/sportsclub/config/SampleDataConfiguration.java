package cz.muni.fi.pa165.sportsclub.config;

import cz.muni.fi.pa165.sportsclub.ServiceConfig;
import cz.muni.fi.pa165.sportsclub.loader.SampleDataLoader;
import cz.muni.fi.pa165.sportsclub.loader.SampleDataLoaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author 445403 Kristian Katanik
 */
@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackageClasses = {SampleDataLoaderImpl.class})
public class SampleDataConfiguration {


    @Inject
    private SampleDataLoader loader;

    @PostConstruct
    public void dataLoading() throws IOException, ParseException {
        loader.loadData();
    }
}
