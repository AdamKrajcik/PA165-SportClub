package Entities;


import javax.persistence.*;
import javax.persistence.Entity;

import java.util.Date;
import java.util.Set;

/**
 * @author Jan Cech
 * Class that represents one player
 */
@Entity
public class Player extends Person{
    private int height;

    private int weight;

    private Date dateOfBirth;

    @OneToMany
    private Set<RosterEntry> rosterEntries;

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
        return rosterEntries;
    }

    public void setRosterEntries(Set<RosterEntry> rosterEntries) {
        this.rosterEntries = rosterEntries;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
