import model.Player;
import model.Team;
import event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrade {
    Team team1;
    Team team2;
    ArrayList<Team> allteams;
    Trade trade;

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
        trade = new Trade(allteams);
    }

    @Test
    void testTrade() {
        trade = new Trade(allteams);
        assertEquals(trade.event, "\n");
    }

    @Test
    void testPlayerExchange() {
        trade.playerExchange(team1, 1, team2, 1);
        assertTrue(trade.event.contains(team1.getTeamname()));
        assertTrue(trade.event.contains(team2.getTeamname()));
    }

}

