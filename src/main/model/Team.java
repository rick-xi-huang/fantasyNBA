package model;


import java.io.Serializable;
import java.util.ArrayList;


public class Team implements Serializable {

    private ArrayList<Player> teamplayers;
    private String teamname;
    private int teamPower;

    //construct a new team
    //MODIFIES: this
    //instantiate a team object
    public Team() {
        teamplayers = new ArrayList<>();
    }

    //get teamplayers from the team
    //EFFECTS: return teamplayers as an ArrayList of player objects
    public ArrayList<Player> getTeamplayers() {
        return teamplayers;
    }

    public void setTeamplayers(ArrayList<Player> teamplayers) {
        this.teamplayers = teamplayers;
    }

    //add a new player to the team
    //MODIFIES: this
    //EFFECTS: add a new player object to ArrayList teamplayers
    public void addplayer(Player player) {
        teamplayers.add(player);
    }

    //get the teamname of the team
    //EFFECTS: return the teamname of the team
    public String getTeamname() {
        return teamname;
    }

    //set the teamname of the team
    //MODIFIES: this
    //EFFECTS: change the teamname of the team
    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    //covert to String output
    //EFFECTS: return String for printing statement
    public String toString() {
        return teamname + "   " + teamplayers + "\n";
    }

    public int teamPower() {
        for (Player player : teamplayers) {
            teamPower = (int) (teamPower + player.getOverall());
        }
        return teamPower;
    }

}
