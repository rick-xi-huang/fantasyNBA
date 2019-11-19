import event.Match;
import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMatch {
    Team team1;
    Team team2;
    Match match;

    @BeforeEach
    void runBefore() {
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test2");
        team1.addplayer(new Player(1, "Player1", 90));
        team1.addplayer(new Player(2, "Player2", 90));
        team2.addplayer(new Player(3, "Player3", 90));
        team2.addplayer(new Player(4, "Player4", 90));
        match = new Match(team1,team2);

    }

    @Test
    void testMatch() {
        match = new Match(team1,team2);
    }


    @Test
    void testNewMatch() {
        match.twoTeamsMatch();
        assertTrue(match.getMessage().contains(team1.getTeamname()));
        assertTrue(match.getWinner().equals(team1)||match.getWinner().equals(team2));
        assertTrue(match.getLoser().equals(team1)||match.getLoser().equals(team2));
    }


    @Test
    void testNewMatchLoop() {

        for (int i = 0; i < 10; i++) {
            match.twoTeamsMatch();
        }

        assertTrue(match.getMessage().contains(team1.getTeamname()));
        assertTrue(match.getWinner().equals(team1)||match.getWinner().equals(team2));
        assertTrue(match.getLoser().equals(team1)||match.getLoser().equals(team2));

    }

}
