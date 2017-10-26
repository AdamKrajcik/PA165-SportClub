package Entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

/**
 * @author Jan Cech
 */
@Entity
public class Coach extends Person{
    @OneToMany
    private Set<Team> teams;

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }
}
