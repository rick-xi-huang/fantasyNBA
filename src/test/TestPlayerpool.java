import data.Playerpool;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Playerpool class.
 */
class TestPlayerpool {

    private Playerpool playerpool;

    @BeforeEach
    void runBefore() throws IOException {
        playerpool = new Playerpool();
    }

    @Test
    void testPlayerpool() throws IOException {
        playerpool = new Playerpool();
        assertEquals(playerpool.getallplayers().size(), 200);
    }

    @Test
    void testGetAllPlayers() {
        assertEquals(playerpool.getallplayers().size(),200);
    }

    @Test
    void testGetPlayer() {
        assertEquals(playerpool.getPlayer(0),playerpool.getallplayers().get(0));
    }

}
