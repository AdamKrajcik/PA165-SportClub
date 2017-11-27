package cz.muni.fi.pa165.sportsclub.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation for Time service
 *
 * @author PA165 Team
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
