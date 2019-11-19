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
        trade = new Trade(team1,team2);
    }

    @Test
    void testTrade() {
        trade = new Trade(team1,team2);
    }

    @Test
    void testPlayerExchange() {
        trade.playerExchange(1, 1);
        assertTrue(trade.getMessage().contains(team1.getTeamname()));
        assertTrue(trade.getMessage().contains(team2.getTeamname()));
    }

}

