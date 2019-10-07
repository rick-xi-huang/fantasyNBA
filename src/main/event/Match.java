package event;

import model.Team;

import java.util.ArrayList;

public class Match extends Event {

    public Match(ArrayList<Team> currentTeams) {
        super(currentTeams);
    }

    @Override
    void twoTeamsEvent(Team team1, Team team2) {

        if ((team1.teamPower() * Math.random()) > (team2.teamPower() * Math.random())) {
            System.out.println(team1.getTeamname() + " won the match against " + team2.getTeamname());
        } else {
            System.out.println(team2.getTeamname() + " won the match against " + team1.getTeamname());
        }

    }

    @Override
    void resultMessage() {
        System.out.println(" All matches completed! ");
    }
}
