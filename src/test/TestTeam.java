import model.Player;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void testAddWin() {
        team.addwin();
        assertEquals(team.getWin(),1);
    }

    @Test
    void testAddLoss() {
        team.addloss();
        assertEquals(team.getLoss(),1);
    }

    @Test
    void testReset() {
        team.addloss();
        team.addwin();
        team.resetRecord();
        assertEquals(team.getLoss(),0);
        assertEquals(team.getWin(),0);
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
        assertEquals(player3.getTeam(),team);
    }

    @Test
    void testRemovePlayer() {
        team.addplayer(player1);
        team.addplayer(player2);
        team.addplayer(player3);
        team.removeplayer(player1);
        assertEquals(player1.getTeam(),null);
        assertEquals(team.getTeamplayers().size(),2);
        assertFalse(team.getTeamplayers().contains(player1));
    }


    @Test
    void testSetTeamplayers(){
        ArrayList<Player> teamplayers = new ArrayList();
        teamplayers.add(player1);
        teamplayers.add(player2);
        teamplayers.add(player3);
        team.setTeamplayers(teamplayers);
        assertEquals(team.getTeamplayers(), teamplayers);
    }

    @Test
    void testToString() {
        team.setTeamname("Test");
        team.addplayer(player1);
        assertEquals(team.toString(), team.getTeamname() + "   " + team.getTeamplayers()+ "\n");
    }

    @Test
    void testTeamPower() {
        team.addplayer(player1);
        int power = (int) player1.getOverall();
        team.addplayer(player2);
        power = power + (int) player2.getOverall();
        team.addplayer(player3);
        power = power + (int) player3.getOverall();
        assertEquals(team.teamPower(),power);
    }

    @Test
    void testHashcode() {
        Team team1 = new Team();
        team1.setTeamname("Test1");
        Team team2 = new Team();
        team2.setTeamname("Test1");
        Team team3 = new Team();
        assertTrue(team1.equals(team1));
        assertTrue(team1.equals(team2));
        assertFalse(team1.equals(team3));
        assertFalse(team1.equals(null));
        assertFalse(team1.equals(player1));
        assertEquals(team1.hashCode(),-1793273279);


    }

}
