import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Team class.
 */
class TestTeam {

    private Team team;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    void runBefore() {
        player1 = new Player(1,"James", 90);
        player2 = new Player(2,"Allen", 91);
        player3 = new Player(3,"Tracy", 92);
        team = new Team();
    }

    @Test
    void testGetTeamName() {
        assertEquals(team.getTeamname(),null);
    }

    @Test
    void testSetTeamName() {
        team.setTeamname("HOU");
        assertEquals(team.getTeamname(), "HOU");
    }

    @Test
    void testAddPlayer() {
        team.addplayer(player1);
        team.addplayer(player2);
        team.addplayer(player3);
        assertEquals(team.getTeamplayers().size(),3);
        assertTrue(team.getTeamplayers().contains(player3));
    }

    @Test
    void testToString() {
        assertEquals(team.toString(), team.getTeamname() + "   " + team.getTeamplayers());
    }

}
