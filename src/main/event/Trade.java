package event;

import model.Player;
import model.Team;

import java.util.ArrayList;

public class Trade extends Event {
    public Trade(ArrayList<Team> currentTeams) {
        super(currentTeams);
    }

    public void playerExchange(Team team1, int slot1, Team team2, int slot2) {
        team1.addplayer(team2.getTeamplayers().get(slot2));
        team2.removeplayer(team2.getTeamplayers().get(slot2));
        team2.addplayer(team1.getTeamplayers().get(slot1));
        team1.removeplayer(team1.getTeamplayers().get(slot1));
        eventlog = "The trade between " + team1.getTeamname() + "and " + team2.getTeamname() + " has completed";
        eventMessage();
    }
}
