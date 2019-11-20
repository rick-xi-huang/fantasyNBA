import exception.InvalidMatch;
import model.Player;
import model.Team;
import event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestSeason {
    private Team team1;
    private Team team2;
    private ArrayList<Team> allteams1;
    private ArrayList<Team> allteams2;
    private Season season1;
    private Season season2;

    @BeforeEach
    void runBefore() {
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test 2");
        team1.addplayer(new Player(1, "Player1", 90));
        team1.addplayer(new Player(2, "Player2", 90));
        team2.addplayer(new Player(3, "Player3", 90));
        team2.addplayer(new Player(4, "Player4", 90));
        allteams1 = new ArrayList<>();
        allteams2 = new ArrayList<>();
        allteams1.add(team1);
        allteams2.add(team1);
        allteams2.add(team2);
        season1 = new Season(allteams1);
        season2 = new Season(allteams2);

    }

    @Test
    void testSeason() {
        season1 = new Season(allteams1);
        assertEquals(season1.getHistory().size(), 1);
    }


    @Test
    void testNewMatchDayWithoutException() {
        season2.newMatchDay();
        assertEquals(season2.getHistory().size(),2);
    }


    @Test
    void testNewMatchDayWithException() {
        try {
            season1.allTeamsMatch();
            fail();
        } catch (InvalidMatch invalidMatch) {
            invalidMatch.printStackTrace();
        }
    }

    @Test
    void testGetData() {
        assertEquals(season2.getData().size(),2);
    }


}
