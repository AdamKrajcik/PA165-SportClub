package cz.muni.fi.pa165.sportsclub.dto;

import java.util.Objects;

/**
 * DTO for roster entry
 *
 * @author 422636 Adam Krajcik
 */
public class RosterEntryDto {

    private Long id;

    private TeamDto team;

    private PlayerDto player;

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
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTeam(), that.getTeam()) &&
                Objects.equals(getPlayer(), that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTeam(), getPlayer(), getJerseyNumber());
    }
}
