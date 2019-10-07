package event;

import model.Team;

import java.util.ArrayList;

public abstract class Event {

    protected ArrayList<Team> currentTeams;
    protected int teamNum;


    public Event(ArrayList<Team> currentTeams) {

        this.currentTeams = currentTeams;
        teamNum = currentTeams.size();
    }

    public void allTeamsEvent() {
        for (int i = 0; i < teamNum; i++) {

            for (int j = i + 1; j < teamNum; j++) {

                twoTeamsEvent(currentTeams.get(i), currentTeams.get(j));

            }

        }

    }

    abstract void twoTeamsEvent(Team team1, Team team2);

    abstract void resultMessage();

}
