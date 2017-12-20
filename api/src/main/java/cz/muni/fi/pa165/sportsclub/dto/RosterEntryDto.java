package cz.muni.fi.pa165.sportsclub.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * DTO for roster entry
 *
 * @author 422636 Adam Krajcik
 */
public class RosterEntryDto {

    private Long id;

    @JsonManagedReference
    @NotNull
    private TeamDto team;

    @JsonBackReference
    @NotNull
    private PlayerDto player;

    @NotNull
    @Max(99)
    @Min(1)
    private int jerseyNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public PlayerDto getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDto player) {
        this.player = player;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterEntryDto that = (RosterEntryDto) o;
        return getJerseyNumber() == that.getJerseyNumber() &&
                Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getJerseyNumber());
    }
}
