package cz.muni.fi.pa165.sportsclub.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    @JsonManagedReference
    private Set<RosterEntryDto> rosterEntries;

    private int age;

    private String localDate;

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

    public int getAge() {
        return Period.between(dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();
    }

    public String getLocalDate() {
        return dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
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
