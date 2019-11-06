import data.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TestTeamlog {

    Teamlog teamlog;
    Team team1;
    Team team2;

    @BeforeEach
    void runBefore() {
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test2");
        teamlog = new Teamlog();
        teamlog.addTeam(team1);
        teamlog.addTeam(team2);
    }

    @Test
    void testTeamlog() {
        assertEquals(teamlog.getCurrentTeams().size(), 2);
    }


    @Test
    void testLoadWithException() throws IOException {
        String log1 = teamlog.getCurrentTeams().toString();
        teamlog.load();
        String log2 = teamlog.getCurrentTeams().toString();
        assertEquals(log1, log2);
    }

    @Test
    void testLoadWithoutException() throws IOException {
        String log1 = teamlog.getCurrentTeams().toString();
        teamlog.save();
        teamlog.load();
        String log2 = teamlog.getCurrentTeams().toString();
        assertEquals(log1, log2);
    }

    @Test
    void testGetTeam() {
        assertEquals(teamlog.getTeam(0),team1);
    }

    @Test
    void testRemoveTeam() {
        teamlog.removeTeam(team2);
        assertFalse(teamlog.getCurrentTeams().contains(team2));
    }

}

