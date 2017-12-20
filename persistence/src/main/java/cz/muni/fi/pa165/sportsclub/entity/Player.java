package cz.muni.fi.pa165.sportsclub.entity;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a player
 *
 * @author 422636 Adam Krajcik
 */
@Entity
public class Player extends Person {

    @NotNull
    @Column(nullable = false)
    @Min(80)
    @Max(280)
    private int height;

    @NotNull
    @Column(nullable = false)
    @Min(30)
    @Max(150)
    private int weight;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;

    @OneToMany(mappedBy = "player",cascade = CascadeType.REMOVE)
    private Set<RosterEntry> rosterEntries = new HashSet<RosterEntry>();


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<RosterEntry> getRosterEntries() {
        return Collections.unmodifiableSet(rosterEntries);
    }

    public void addRosterEntry(RosterEntry entry) {
        rosterEntries.add(entry);
    }

    public void removeRosterEntry(RosterEntry entry) {
        rosterEntries.remove(entry);
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
