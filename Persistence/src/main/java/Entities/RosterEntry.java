package Entities;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * @author Jan Cech
 */
@Entity
public class RosterEntry extends Entities.Entity{

    @ManyToOne
    private Team team;

    @ManyToOne
    private Player player;

    private int jerseyNumber;

    public RosterEntry(Team team, Player player, int jerseyNumber) {
        this.team = team;
        this.player = player;
        this.jerseyNumber = jerseyNumber;
    }



    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }
}
