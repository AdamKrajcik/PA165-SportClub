package cz.muni.fi.pa165.sportsclub.utils;

import java.util.Date;

/**
 * Interval between 2 dates
 *
 * @author PA165 Team
 */
public class TimeSpan {
    private Date from;
    private Date to;

    public TimeSpan(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSpan timeSpan = (TimeSpan) o;

        if (!from.equals(timeSpan.from)) return false;
        return to.equals(timeSpan.to);
    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        return result;
    }
}
