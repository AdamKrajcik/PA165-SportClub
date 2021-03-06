package cz.muni.fi.pa165.sportsclub.exceptions;

import org.springframework.dao.DataAccessException;

public class SportsClubServiceException extends DataAccessException {

    public SportsClubServiceException(String msg) {
        super(msg);
    }

    public SportsClubServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
