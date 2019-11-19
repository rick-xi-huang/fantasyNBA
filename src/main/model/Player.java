package model;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private int id;
    private String name;
    private double overall;
    private Team team;

    //construct a player
    //MODIFIES: this
    //EFFECTS: generate a player object with ID name and overall

    public Player(int id, String name, double overall) {
        this.id = id;
        this.name = name;
        this.overall = overall;
    }


    //get the name of the player
    //EFFECTS: return the name of the player
    public String getName() {
        return name;
    }

    //change the name of the player
    //MODIFIES: this
    //EFFECTS: change the name of the player
    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        if (!(this.team == team)) {
            this.team = team;
            team.addplayer(this);
        }
    }

    public void removeTeam() {
        if (!(this.team == null)) {
            this.team.removeplayer(this);
            this.team = null;
        }
    }

    public Team getTeam() {
        return team;
    }

    //get the overall of the player
    //EFFECTS: return the name of the player
    public double getOverall() {
        return overall;
    }

    //change the overall of the player
    //MODIFIES: this
    //EFFECTS: change the overall of the player
    public void setOverall(double overall) {
        this.overall = overall;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //covert to String output
    //EFFECTS: return String for printing statement
    public String toString() {
        return "(" + id + ")" + " " + name + " " + overall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(id, player.id)
                && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
