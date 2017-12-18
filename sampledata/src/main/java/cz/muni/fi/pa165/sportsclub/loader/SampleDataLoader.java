package cz.muni.fi.pa165.sportsclub.loader;

import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author 445403 Kristian Katanik
 */
public interface SampleDataLoader {
    /**
     * Loads sample data to the database.
     *
     * @throws IOException
     */
    void loadData() throws IOException, ParseException;

}
