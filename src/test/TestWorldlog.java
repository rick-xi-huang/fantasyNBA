import data.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestWorldlog {

    ArrayList<Team> testTeams = new ArrayList<>();
    Worldlog worldlog;

    @BeforeEach
    void runBefore() {
        Team team1 = new Team();
        team1.setTeamname("Test");
        testTeams.add(team1);
        testTeams.add(team1);
        worldlog = new Worldlog(testTeams);
    }

    @Test
    void testWorldlog() {
        worldlog = new Worldlog(testTeams);
        assertEquals(worldlog.currentTeams.size(), 2);
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


}

