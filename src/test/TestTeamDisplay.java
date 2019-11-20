import model.Team;
import model.TeamDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTeamDisplay {

    Team team;
    TeamDisplay teamDisplay;

    @BeforeEach
    void setUp() {
        team = new Team();
        team.setTeamname("Test");
        teamDisplay = new TeamDisplay(team);
    }

    @Test
    void getTeamname() {
        assertEquals(teamDisplay.getTeamname(),"Test");
    }

    @Test
    void setTeamname() {
        teamDisplay.setTeamname("Test2");
        assertEquals(teamDisplay.getTeamname(),"Test2");
    }

    @Test
    void getWin() {
        assertEquals(teamDisplay.getWin(),0);
    }

    @Test
    void setWin() {
        teamDisplay.setWin(1);
        assertEquals(teamDisplay.getWin(),1);
    }

    @Test
    void getLoss() {
        assertEquals(teamDisplay.getLoss(),0);
    }

    @Test
    void setLoss() {
        teamDisplay.setLoss(1);
        assertEquals(teamDisplay.getLoss(),1);
    }
}