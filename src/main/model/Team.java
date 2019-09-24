package model;


import java.util.ArrayList;


public class Team {

    private ArrayList<Player> teamplayers;
    private String teamname;

    public Team() {
        teamplayers = new ArrayList<>();
    }

    public ArrayList<Player> getTeamplayers() {
        return teamplayers;
    }

    public void setTeamplayers(ArrayList<Player> teamplayers) {
        this.teamplayers = teamplayers;
    }

    public void addplayer(Player player) {
        teamplayers.add(player);
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String toString() {
        return teamname + "   " + teamplayers;
    }


}
