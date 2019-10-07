package event;

import model.Team;

import java.util.ArrayList;

public class Trade extends Event {
    public Trade(ArrayList<Team> currentTeams) {
        super(currentTeams);
    }

    @Override
    void twoTeamsEvent(Team team1, Team team2) {

    }

    @Override
    void resultMessage() {

    }
}
