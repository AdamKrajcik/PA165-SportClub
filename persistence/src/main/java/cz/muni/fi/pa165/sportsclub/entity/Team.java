package cz.muni.fi.pa165.sportsclub.entity;

import cz.muni.fi.pa165.sportsclub.enums.AgeGroup;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Class that represents one team
 *
 * @author Jan Cech
 * @author Martin Skrovina 410461 Add/remove RosterEntry
 */
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //business key
    @NotNull
    @Size(min=3, max = 25)
    @Column(length = 25, unique = true, nullable = false)
    private String name;

    @ManyToOne
    private Coach coach;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;

    @OneToMany(mappedBy = "team")
    private Set<RosterEntry> rosterEntries = new HashSet<>();


    public Team() {
    }

    public Team(String name, Coach coach, AgeGroup ageGroup, Set<RosterEntry> rosterEntries) {
        this.name = name;
        this.coach = coach;
        this.ageGroup = ageGroup;
        this.rosterEntries = rosterEntries;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return Collections.unmodifiableSet(rosterEntries);
    }

    public void addRosterEntry(RosterEntry rosterEntry) {
        this.rosterEntries.add(rosterEntry);
    }

    public void removeRosterEntry(RosterEntry rosterEntry) {
        this.rosterEntries.remove(rosterEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Team)) {
            return false;
        }

        final Team other = (Team) o;

        if ((getName() == null)
                ? (other.getName() != null)
                : !getName().equals(other.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 23;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}
