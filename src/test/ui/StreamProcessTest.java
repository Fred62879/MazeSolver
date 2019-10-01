package ui;

import model.StreamProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class StreamProcessTest {

    StreamProcess sp1, sp2, sp3, sp4, sp5, sp6, sp7;
    String[] orders1, orders2, orders3, orders4, orders5, orders6, orders7;
    String[] all = {
            "Hi there, this is a Maze solver!",  // 0
            "Error: Please give integers only!", // 1
            "Please type in the number of rows and columns of your maze below:",       // 2
            "Please type in all entries of your maze below (1 for path, 0 for wall):", // 3
            "Save your maze to local storage? (Y/N)", // 4
            "Maze saved successfully!",               // 5
            "Would you like to re-enter maze? (Y/N)", // 6
            "Would you like to retrieve your former maze? (Y/N)", // 7
            "No previous maze found!",                // 8
            "Which maze would you like to restore?",  // 9
            "Please select those available here!",    // 10
            "Error: No input received!"               // 11
    };

    @BeforeEach
    public void setup() throws IOException {
        PrintWriter writer = new PrintWriter("inputfile.txt");
        writer.print("");
        writer.close();

        String[] orders1 = { "y", "2 2", "1 0 0", "n" }; // insufficient and quit
        String[] orders2 = { "y", "2 2", "1 '1' 0 0", "n" }; // char inout and quit
        String[] orders3 = { "y", "2 2", "1 1 0 '0'", "y", "2 1", "1 0", "n" }; // char and re-enter
        String[] orders4 = { "y", "2 2", "1 1 0 0", "y" }; // valid and store
        String[] orders5 = { "y", "2", "1" };
        String[] orders6 = { "y", "1" };

        sp1 = new StreamProcess(orders1);
        sp2 = new StreamProcess(orders2);
        sp3 = new StreamProcess(orders3);
        sp4 = new StreamProcess(orders4);
        sp5 = new StreamProcess(orders5);
        sp6 = new StreamProcess(orders6);

        sp1.run();
        sp2.run();
        sp3.run();
        sp4.run();
        sp5.run();
        sp6.run();
    }

    @Test
    public void testReadInt() {
        assertEquals("", sp1.getResponse(1));
        assertEquals(all[11], sp1.getResponse(11));

        assertEquals(all[1], sp2.getResponse(1));
        assertEquals("", sp2.getResponse(11));

        assertEquals(all[1], sp3.getResponse(1));
        assertEquals("", sp3.getResponse(11));

        assertEquals("", sp4.getResponse(1));
        assertEquals("", sp4.getResponse(11));

        assertEquals("", sp5.getResponse(1));
        assertEquals("", sp5.getResponse(11));

        assertEquals("", sp6.getResponse(1));
        assertEquals("", sp6.getResponse(11));
    }

    @Test
    public void readInEntry() {
        assertTrue(sp1.getAllInts());
        assertFalse(sp2.getAllInts());
        assertTrue(sp3.getAllInts());
        assertTrue(sp4.getAllInts());
        assertFalse(sp5.getAllInts());
        assertFalse(sp6.getAllInts());
    }

    @Test
    public void testSaveResponse() {
        assertEquals("", sp1.getResponse(5));
        assertEquals("", sp2.getResponse(5));
        assertEquals("", sp3.getResponse(5));
        assertEquals(all[5], sp4.getResponse(5));
        assertEquals("", sp5.getResponse(5));
        assertEquals("", sp6.getResponse(5));
    }

    @Test
    public void testQuitResponse() {
        assertTrue(sp1.getQuit());
        assertTrue(sp2.getQuit());
        assertFalse(sp3.getQuit());
        assertFalse(sp4.getQuit());
        assertFalse(sp5.getQuit());
        assertFalse(sp6.getQuit());
    }

    private void selectHelper(StreamProcess sp) {
        assertEquals(all[8], sp.getResponse(8));
        assertEquals("", sp.getResponse(9));
        assertEquals("", sp.getResponse(10));
    }

    @Test
    public void testSelect() {
        selectHelper(sp1);
        selectHelper(sp2);
        selectHelper(sp3);
        selectHelper(sp4);
        assertEquals("", sp5.getResponse(8));
        assertEquals(all[9], sp5.getResponse(9));
        assertEquals(all[10], sp5.getResponse(10));

        assertEquals("", sp6.getResponse(8));
        assertEquals(all[9], sp6.getResponse(9));
        assertEquals("", sp6.getResponse(10));
    }

}

