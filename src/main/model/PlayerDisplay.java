package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.util.Objects;

public class PlayerDisplay implements Serializable {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleDoubleProperty overall;

    //construct a player
    //MODIFIES: this
    //EFFECTS: generate a player object with ID name and overall

    public PlayerDisplay(Player player) {
        this.id = new SimpleIntegerProperty(player.getId());
        this.name = new SimpleStringProperty(player.getName());
        this.overall = new SimpleDoubleProperty(player.getOverall());
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



}
