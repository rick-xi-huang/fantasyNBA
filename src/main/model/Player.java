package model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty overall;
    private Team team;

    //construct a player
    //MODIFIES: this
    //EFFECTS: generate a player object with ID name and overall

    public Player(int id, String name, double overall) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.overall = new SimpleDoubleProperty(overall);
    }


    //get the name of the player
    //EFFECTS: return the name of the player
    public String getName() {
        return name.get();
    }

    //change the name of the player
    //MODIFIES: this
    //EFFECTS: change the name of the player
    public void setName(String name) {
        this.name.set(name);
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
        return overall.get();
    }

    //change the overall of the player
    //MODIFIES: this
    //EFFECTS: change the overall of the player
    public void setOverall(double overall) {
        this.overall.set(overall);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    //covert to String output
    //EFFECTS: return String for printing statement
    public String toString() {
        return "(" + id.get() + ")" + " " + name.get() + " " + overall.get();
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
