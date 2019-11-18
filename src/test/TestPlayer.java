import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Player class.
 */
class TestPlayer {

    private Player player;
    private Team team;

    @BeforeEach
    void runBefore() {
        player = new Player(1, "James", 88);
        team = new Team();
    }

    @Test
    void testGetName() {
        assertEquals(player.getName(), "James");
    }

    @Test
    void testGetOverall() {
        assertEquals(player.getOverall(), 88);
    }

    @Test
    void testSetName() {
        player.setName("Scott");
        assertEquals(player.getName(), "Scott");
    }

    @Test
    void testSetTeam() {
        player.setTeam(team);
        assertEquals(player.getTeam(),team);
        assertTrue(team.getTeamplayers().contains(player));
    }

    @Test
    void removeTeam() {
        player.setTeam(team);
        assertEquals(player.getTeam(),team);
        player.removeTeam();
        assertEquals(player.getTeam(),null);
        assertFalse(team.getTeamplayers().contains(player));
    }


    @Test
    void testSetOverall() {
        player.setOverall(90);
        assertEquals(player.getOverall(), 90);
    }

    @Test
    void testToString() {
        assertEquals(player.toString(), "(1) James 88.0");
    }

    @Test
    void testHashcode() {
        Player player1 = new Player(1, "James", 88);
        Player player2 = new Player(1, "James", 88);
        Player player3 = new Player(1, "James", 90);
        assertTrue(player1.equals(player1));
        //assertTrue(player1.equals(player2));
        assertFalse(player1.equals(player3));
        assertFalse(player1.equals(null));
        assertFalse(player1.equals(team));
        assertEquals(player1.hashCode(), 1969767662);
    }

}
