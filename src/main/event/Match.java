package event;

import model.Team;

import java.util.ArrayList;

public class Match extends Event {

    public Match(ArrayList<Team> currentTeams) {
        super(currentTeams);
    }


    public void allTeamsMatch() {
        for (int i = 0; i < teamNum; i++) {

            for (int j = i + 1; j < teamNum; j++) {

                twoTeamsMatch(currentTeams.get(i), currentTeams.get(j));

            }

        }

        eventMessage();

    }

    public void twoTeamsMatch(Team team1, Team team2) {

        if ((team1.teamPower() * Math.random()) > (team2.teamPower() * Math.random())) {
            eventlog = eventlog + team1.getTeamname() + " won the match against " + team2.getTeamname() + "\n";
        } else {
            eventlog = eventlog + team2.getTeamname() + " won the match against " + team1.getTeamname() + "\n";
        }

    }

}
