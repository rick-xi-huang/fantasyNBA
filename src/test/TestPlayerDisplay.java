import model.Player;
import model.PlayerDisplay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class TestPlayerDisplay {

    private PlayerDisplay playerDisplay;

    @BeforeEach
    void runBefore() {
        playerDisplay = new PlayerDisplay(new Player(1, "James", 88));
    }

    @Test
    void testGetName() {
        assertEquals(playerDisplay.getName(), "James");
    }

    @Test
    void testGetOverall() {
        assertEquals(playerDisplay.getOverall(), 88);
    }

    @Test
    void testSetName() {
        playerDisplay.setName("Scott");
        assertEquals(playerDisplay.getName(), "Scott");
    }

    @Test
    void testSetOverall() {
        playerDisplay.setOverall(90);
        assertEquals(playerDisplay.getOverall(), 90);
    }

    @Test
    void testGetId() {
        assertEquals(playerDisplay.getId(), 1);
    }

    @Test
    void testSetId() {
        playerDisplay.setId(2);
        assertEquals(playerDisplay.getId(), 2);
    }


}
