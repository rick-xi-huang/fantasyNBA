package event;

import model.Team;

public class Match extends Subject {

    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;
    private String message;
    private Team winner;
    private Team loser;

    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        observerList.add(new BroadCast());
    }

    //Start a match between two teams
    //MODIFIES: this
    //EFFECTS: simulate the result of the match, update scores, winner and loser, and notify the observers

    public void twoTeamsMatch() {

        team1Score = (int) (team1.teamPower() * Math.random());
        team2Score = (int) (team2.teamPower() * Math.random());

        if (team1Score > team2Score) {
            winner = team1;
            loser = team2;
            message = team1.getTeamname() + " won the match against " + team2.getTeamname() + "\n";
        } else {
            message = team2.getTeamname() + " won the match against " + team1.getTeamname() + "\n";
            winner = team2;
            loser = team1;
        }
        notifyObservers(this);

    }

    public String getMessage() {
        return message;
    }

    public Team getWinner() {
        return winner;
    }

    public Team getLoser() {
        return loser;
    }
}
