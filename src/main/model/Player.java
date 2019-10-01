package model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String name;
    private double overall;

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

    //covert to String output
    //EFFECTS: return String for printing statement
    public String toString() {
        return "(" + id + ")" + " " + name + " " + overall;
    }

}
