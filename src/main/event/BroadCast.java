package event;

import model.Team;

public class BroadCast implements Observer {

    @Override
    public void update(Team team1, int teamscore1, Team team2, int teamscore2) {
        if (teamscore1 > teamscore2) {
            System.out.println(team1.getTeamname() + " won " + team2.getTeamname() + " "
                    + teamscore1 + " : " + teamscore2);
        } else {
            System.out.println(team2.getTeamname() + " won " + team1.getTeamname() + " "
                    + teamscore2 + " : " + teamscore1);
        }
    }
}
