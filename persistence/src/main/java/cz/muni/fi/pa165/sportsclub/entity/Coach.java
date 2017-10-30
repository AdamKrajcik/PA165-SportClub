package cz.muni.fi.pa165.sportsclub.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class representing a coach
 *
 * @author Jan Cech
 * @author 422636 Adam Krajcik updates
 */
@Entity
public class Coach extends Person {
    @OneToMany(mappedBy = "coach")
    private Set<Team> teams = new HashSet<Team>();


    public Set<Team> getTeams() {
        return Collections.unmodifiableSet(teams);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Player)) {
            return false;
        }

        final Player other = (Player) o;

        if ((getEmail() == null) ? (other.getEmail() != null) : !getEmail().equals(other.getEmail())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int result = 1;
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }
}
