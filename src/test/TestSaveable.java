import data.Worldlog;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSaveable {

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
    void testLoad() throws IOException, ClassNotFoundException {
        String worldlog1 = worldlog.getCurrentTeams().toString();
        worldlog.save();
        worldlog.load();
        String worldlog2 = worldlog.getCurrentTeams().toString();
        assertEquals(worldlog1,worldlog2);
    }

}

