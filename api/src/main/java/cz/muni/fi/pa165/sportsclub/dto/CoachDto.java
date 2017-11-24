package cz.muni.fi.pa165.sportsclub.dto;

import java.util.Objects;
import java.util.Set;

/**
 * DTO for coach
 *
 * @author 422636 Adam Krajcik
 */
public class CoachDto extends PersonDto{

    private Set<TeamDto> teams;

    public Set<TeamDto> getTeams() {
        return teams;
    }

    public void setTeams(Set<TeamDto> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachDto coachDto = (CoachDto) o;
        return Objects.equals(getTeams(), coachDto.getTeams());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeams());
    }
}