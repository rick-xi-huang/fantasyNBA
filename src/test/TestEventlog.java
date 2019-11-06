import data.Eventlog;
import data.Teamlog;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestEventlog {

    Eventlog eventlog;

    @BeforeEach
    void runBefore() {
        eventlog = new Eventlog();
    }

    @Test
    void testEventlog() {
        assertEquals(eventlog.getAllEvents().size(),0);
    }


    @Test
    void testLoadWithException() throws IOException {
        ArrayList<String> log1 = eventlog.getAllEvents();
        eventlog.load();
        ArrayList<String> log2 = eventlog.getAllEvents();
        assertEquals(log1, log2);
    }

    @Test
    void testLoadWithoutException() throws IOException {
        ArrayList<String> log1 = eventlog.getAllEvents();
        eventlog.save();
        eventlog.load();
        ArrayList<String> log2 = eventlog.getAllEvents();
        assertEquals(log1, log2);
    }

    @Test
    void testAddEvent() {
        eventlog.addEvent("Test");
        assertTrue(eventlog.getAllEvents().contains("Test"));
    }


}

