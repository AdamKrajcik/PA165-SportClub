package cz.muni.fi.pa165.sportsclub.service;

import java.util.Date;

/**
 * Implementation for Time service
 *
 * @author PA165 Team
 */
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentTime() {
        return new Date();
    }
}
