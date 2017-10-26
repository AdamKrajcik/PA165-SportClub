package Entities;


import org.hibernate.annotations.Generated;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Set;

/**
 * @author Jan Cech
 * Class that represents one team
 */
@Entity
public class Team extends Entities.Entity{


    @Column(length = 25)
    private String name;

    @ManyToOne
    private Coach coach;

    private AgeGroup ageGroup;

    @OneToMany
    private Set<RosterEntry> rosterEntries;

    public Team(String name, Coach coach, AgeGroup ageGroup, Set<RosterEntry> rosterEntries) {
        this.name = name;
        this.coach = coach;
        this.ageGroup = ageGroup;
        this.rosterEntries = rosterEntries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Set<RosterEntry> getRosterEntries() {
        return rosterEntries;
    }

    public void setRosterEntries(Set<RosterEntry> rosterEntries) {
        this.rosterEntries = rosterEntries;
    }
}
