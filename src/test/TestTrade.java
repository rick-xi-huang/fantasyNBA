import ui.Playerpool;
import model.Team;
import event.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestTrade {
    Team team1;
    Team team2;
    Playerpool playerpool;
    ArrayList<Team> allteams;
    Trade trade;

    @BeforeEach
    void runBefore() throws IOException {
        playerpool = new Playerpool();
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test 2");
        team1.addplayer(playerpool.getallplayers().get(1));
        team1.addplayer(playerpool.getallplayers().get(2));
        team2.addplayer(playerpool.getallplayers().get(3));
        team2.addplayer(playerpool.getallplayers().get(4));
        allteams = new ArrayList<>();
        allteams.add(team1);
        allteams.add(team2);
        trade = new Trade(allteams);
    }

    @Test
    void testTrade(){
        trade = new Trade(allteams);
        assertEquals(trade.eventlog, "\n" );
    }

    @Test
    void testPlayerExchange(){
        trade.playerExchange(team1,1,team2, 1);
        assertTrue(trade.eventlog.contains(team1.getTeamname()));
        assertTrue(trade.eventlog.contains(team2.getTeamname()));
    }

}

