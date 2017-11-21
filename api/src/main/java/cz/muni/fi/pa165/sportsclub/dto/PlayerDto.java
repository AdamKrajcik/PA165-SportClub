package cz.muni.fi.pa165.sportsclub.dto;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for player
 *
 * @author 422636 Adam Krajcik
 */
public class PlayerDto extends PersonDto {

    private int height;

    private int weight;

    private Date dateOfBirth;

    private Set<RosterEntryDto> rosterEntries;

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

    public Set<RosterEntryDto> getRosterEntries() {
        return rosterEntries;
    }

    public void setRosterEntries(Set<RosterEntryDto> rosterEntries) {
        this.rosterEntries = rosterEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDto playerDto = (PlayerDto) o;
        return getHeight() == playerDto.getHeight() &&
                getWeight() == playerDto.getWeight() &&
                Objects.equals(getDateOfBirth(), playerDto.getDateOfBirth()) &&
                Objects.equals(getRosterEntries(), playerDto.getRosterEntries());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHeight(), getWeight(), getDateOfBirth(), getRosterEntries());
    }
}
