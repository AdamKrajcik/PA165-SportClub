package cz.muni.fi.pa165.sportsclub.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author
 */
@Entity
public class Coach extends Person {
    @OneToMany
    private Set<Team> teams = new HashSet<Team>();

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(teams, coach.teams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teams);
    }
}
