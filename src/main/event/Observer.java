package event;

import model.Team;

public interface Observer {
    public void update(Team team1, int teamscore1, Team team2, int teamscore2);
}
