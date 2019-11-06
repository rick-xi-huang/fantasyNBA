import exception.InvalidMatch;
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
    ArrayList<Team> allteams1;
    ArrayList<Team> allteams2;
    Match match1;
    Match match2;

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
        allteams1 = new ArrayList<>();
        allteams2 = new ArrayList<>();
        allteams1.add(team1);
        allteams2.add(team1);
        allteams2.add(team2);
        match1 = new Match(allteams1);
        match2 = new Match(allteams2);

    }

    @Test
    void testMatch() {
        match1 = new Match(allteams1);
        assertEquals(match1.getEvent(), match1.getDate()+"\n");
    }


    @Test
    void testNewMatchWithException() {
        match1.newMatch();
        assertFalse(match1.getEvent().contains(team1.getTeamname()));
        match2.newMatch();
        assertTrue(match2.getEvent().contains(team1.getTeamname()));
    }


    @Test
    void testAllTeamsMatchWithException() {
        try {
            match1.allTeamsMatch();
            fail();
        } catch (InvalidMatch invalidMatch) {
            invalidMatch.printStackTrace();
        }
    }

    @Test
    void testAllTeamsMatchWithoutException() {

        for (int i = 0; i < 10; i++) {
            try {
                match2.allTeamsMatch();
            } catch (InvalidMatch invalidMatch) {
                fail();
            }
            assertTrue(match2.getEvent().contains(team1.getTeamname()));
            assertTrue(match2.getEvent().contains(team2.getTeamname()));
        }
    }

    @Test
    void testTwoTeamsMatch() {
        match2.twoTeamsMatch(team1, team2);
        assertTrue(match2.getEvent().contains(team1.getTeamname()));
        assertTrue(match2.getEvent().contains(team2.getTeamname()));
    }


}
