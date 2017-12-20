package cz.muni.fi.pa165.sportsclub.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Objects;
import java.util.Set;

/**
 * DTO fot team
 *
 * @author 422636 Adam Krajcik
 */
public class TeamDto {

    private Long id;

    private String name;

    @JsonManagedReference
    private CoachDto coach;

    @JsonBackReference
    private Set<RosterEntryDto> roster;

    // move enum
    private String ageCategory;

    public Long getId() {
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

    public CoachDto getCoach() {
        return coach;
    }

    public void setCoach(CoachDto coach) {
        this.coach = coach;
    }

    public Set<RosterEntryDto> getRoster() {
        return roster;
    }

    public void setRoster(Set<RosterEntryDto> roster) {
        this.roster = roster;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return Objects.equals(getId(), teamDto.getId()) &&
                Objects.equals(getName(), teamDto.getName()) &&
                Objects.equals(getCoach(), teamDto.getCoach()) &&
                Objects.equals(getRoster(), teamDto.getRoster()) &&
                Objects.equals(getAgeCategory(), teamDto.getAgeCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAgeCategory());
    }
}
