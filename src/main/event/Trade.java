package event;

import model.Team;

import java.util.ArrayList;

public class Trade {

    private Team team1;
    private Team team2;
    private String message;

    public Trade(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void playerExchange(int slot1, int slot2) {
        team1.addplayer(team2.getTeamplayers().get(slot2));
        team2.removeplayer(team2.getTeamplayers().get(slot2));
        team2.addplayer(team1.getTeamplayers().get(slot1));
        team1.removeplayer(team1.getTeamplayers().get(slot1));
        message = "The trade between " + team1.getTeamname() + "and " + team2.getTeamname() + " has completed";
    }

    public String getMessage() {
        return message;
    }
}
