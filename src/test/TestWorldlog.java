import data.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestWorldlog {

    Worldlog worldlog;
    Team team1;
    Team team2;

    @BeforeEach
    void runBefore() {
        team1 = new Team();
        team1.setTeamname("Test1");
        team2 = new Team();
        team2.setTeamname("Test2");
        worldlog = new Worldlog();
        worldlog.addTeam(team1);
        worldlog.addTeam(team2);
    }

    @Test
    void testWorldlog() {
        assertEquals(worldlog.getCurrentTeams().size(), 2);
    }


    @Test
    void testLoadWithException() throws IOException {
        String worldlog1 = worldlog.getCurrentTeams().toString();
        worldlog.load();
        String worldlog2 = worldlog.getCurrentTeams().toString();
        assertEquals(worldlog1, worldlog2);
    }

    @Test
    void testLoadWithoutException() throws IOException {
        String worldlog1 = worldlog.getCurrentTeams().toString();
        worldlog.save();
        worldlog.load();
        String worldlog2 = worldlog.getCurrentTeams().toString();
        assertEquals(worldlog1, worldlog2);
    }

    @Test
    void testGetTeam() {
        assertEquals(worldlog.getTeam(0),team1);
    }

    @Test
    void testRemoveTeam() {
        worldlog.removeTeam(team2);
        assertFalse(worldlog.getCurrentTeams().contains(team2));
    }

}

