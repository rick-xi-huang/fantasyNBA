import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Player class.
 */
class TestPlayer {

    private Player player;

    @BeforeEach
    void runBefore() {
        player = new Player(1, "James", 88);
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
    void testSetOverall() {
        player.setOverall(90);
        assertEquals(player.getOverall(), 90);
    }

}
