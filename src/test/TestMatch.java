import model.Player;
import model.Team;
import event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatch {
    Team team1;
    Team team2;
    ArrayList<Team> allteams;
    Match match;

    @BeforeEach
    void runBefore() {
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test 2");
        team1.addplayer(new Player(1, "Player1", 90));
        team1.addplayer(new Player(2, "Player2", 90));
        team2.addplayer(new Player(3, "Player3", 90));
        team2.addplayer(new Player(4, "Player3", 90));
        allteams = new ArrayList<>();
        allteams.add(team1);
        allteams.add(team2);
        match = new Match(allteams);
    }

    @Test
    void testMatch() {
        match = new Match(allteams);
        assertEquals(match.eventlog, "\n");
    }

    @Test
    void testallTeamsMatch() {
        match.allTeamsMatch();
        assertTrue(match.eventlog.contains(team1.getTeamname()));
        assertTrue(match.eventlog.contains(team2.getTeamname()));
    }

    @Test
    void testTwoTeamsMatch() {
        match.twoTeamsMatch(team1, team2);
        assertTrue(match.eventlog.contains(team1.getTeamname()));
        assertTrue(match.eventlog.contains(team2.getTeamname()));
    }


}
