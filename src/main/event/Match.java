package event;

import exception.InvalidMatch;
import model.Team;

import java.util.ArrayList;

public class Match extends Event {

    public Match(ArrayList<Team> currentTeams) {
        super(currentTeams);
    }


    public void newMatch() {

        try {
            allTeamsMatch();
        } catch (InvalidMatch e) {
            System.out.println("Not enough teams");
        }
    }


    public void allTeamsMatch() throws InvalidMatch {

        if (currentTeams.size() < 2) {
            throw new InvalidMatch();
        }

        for (int i = 0; i < teamNum; i++) {

            for (int j = i + 1; j < teamNum; j++) {

                twoTeamsMatch(currentTeams.get(i), currentTeams.get(j));

            }
        }

    }

    public void twoTeamsMatch(Team team1, Team team2) {

        int teamscore1 = (int) (team1.teamPower() * Math.random());
        int teamscore2 = (int) (team2.teamPower() * Math.random());

        if (teamscore1 > teamscore2) {
            event = event + team1.getTeamname() + " won the match against " + team2.getTeamname() + "\n";
        } else {
            event = event + team2.getTeamname() + " won the match against " + team1.getTeamname() + "\n";
        }
        notifyObservers(team1,teamscore1,team2,teamscore2);

    }

}
