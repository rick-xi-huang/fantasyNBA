import model.Player;
import model.Team;
import model.Playerpool;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    void testGetAllPlayers() {
        assertEquals(playerpool.getallplayers().size(),200);
    }

}
