package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Team implements Serializable {

    private ArrayList<Player> teamplayers;
    private String teamname;
    private int teamPower;
    private int win;
    private int loss;

    //construct a new team
    //MODIFIES: this
    //instantiate a team object
    public Team() {
        teamplayers = new ArrayList<>();
        win = 0;
        loss = 0;
    }

    public void addwin() {
        win++;
    }

    public void addloss() {
        loss++;
    }

    public int getWin() {
        return win;
    }

    public int getLoss() {
        return loss;
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
    //MODIFIES: this player
    //EFFECTS: add a new player object to teamplayers list
    public void addplayer(Player player) {
        if (!teamplayers.contains(player)) {
            teamplayers.add(player);
            player.setTeam(this);
        }
    }

    //remove the player from the team
    //MODIFIES: this player
    //EFFECTS: remove the player object from teamplayers list
    public void removeplayer(Player player) {
        if (teamplayers.contains(player)) {
            teamplayers.remove(player);
            player.removeTeam();
        }
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


    //MODIFIES: this
    //EFFECTS: calculate and return teampower
    public int teamPower() {
        for (Player player : teamplayers) {
            teamPower = (int) (teamPower + player.getOverall());
        }
        return teamPower;
    }

    //override hashcode and equals method
    //EFFECTS: return true if two team objects have the same name, same teamplayers and teampowers

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Team team = (Team) o;
        return teamPower == team.teamPower
                && Objects.equals(teamplayers, team.teamplayers)
                && Objects.equals(teamname, team.teamname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamplayers, teamname, teamPower);
    }

    //MODIFIES: this
    //EFFECTS: reset win and loss back to 0
    public void resetRecord() {
        win = 0;
        loss = 0;
    }
}
