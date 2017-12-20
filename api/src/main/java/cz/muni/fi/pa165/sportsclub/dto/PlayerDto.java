package cz.muni.fi.pa165.sportsclub.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for player
 *
 * @author 422636 Adam Krajcik
 */
public class PlayerDto extends PersonDto {

    @NotNull
    @Min(80)
    @Max(280)
    private int height;

    @NotNull
    @Min(30)
    @Max(150)

    private int weight;


    private Date dateOfBirth;

    @JsonManagedReference
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
        return Objects.equals(getEmail(), playerDto.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
