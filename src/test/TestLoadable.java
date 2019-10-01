import ui.*;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TestLoadable {

    World world;

    @BeforeEach
    void runBefore() throws IOException {
        world = new World();
        Team team1 = new Team();
        team1.setTeamname("Test");
        world.worldlog.add(team1);
        world.worldlog.add(team1);
    }

    @Test
    void testLoad() throws IOException, ClassNotFoundException {
        String worldlog1 = world.worldlog.toString();
        world.save();
        world.load();
        String worldlog2 = world.worldlog.toString();
        assertEquals(worldlog1,worldlog2);
    }

}

