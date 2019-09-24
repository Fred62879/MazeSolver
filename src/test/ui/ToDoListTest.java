package ui;

import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {

    int[] stoi1 = { 2, 2, 0, 1, 0, 1 };
    int[] stoi2 = { 2, 3, 0, 0, 0, 1, 1, 2 };
    int[] stoi4 = { 2, 3, 0, 0, 0, 1, 1, 0 };

    ToDoList tdl1;
    ToDoList tdl2;
    ToDoList tdl3;
    ToDoList tdl4;

    OutputStream ops1;
    OutputStream ops2;
    OutputStream ops3;
    OutputStream ops4;

    public ToDoList setupUnit(String input, OutputStream ops) {
        InputStream ips = new ByteArrayInputStream(input.getBytes());
        PrintStream out = new PrintStream(ops);
        ToDoList tdl = new ToDoList(ips, out);
        return tdl;
    }

    @BeforeEach
    public void setup() {
        ops1 = new ByteArrayOutputStream();
        ops2 = new ByteArrayOutputStream();
        ops3 = new ByteArrayOutputStream();
        ops4 = new ByteArrayOutputStream();

        tdl1 = setupUnit("6 2 2 0 1 0 1", ops1);
        tdl2 = setupUnit("8 2 3 0 0 0 1 1 2", ops2);
        tdl3 = setupUnit("6 '2' 1 0 0 1 0", ops3);
        tdl4 = setupUnit("8 2 3 0 0 0 1 1 0", ops4);
    }

    @Test
    public void testReadIn() {
        assertTrue(tdl1.getAllInts());
        assertTrue(tdl2.getAllInts());
        assertFalse(tdl3.getAllInts());

        assertEquals(ops1.toString(), "");
        assertEquals(ops2.toString(), "");
        assertEquals(ops3.toString(), "Error: Please give integers only!");

        for (int i = 0; i < 6; i++) {
            assertEquals(tdl1.getInputs(i), stoi1[i]);
        }
        for (int i = 0; i < 6; i++) {
            assertEquals(tdl2.getInputs(i), stoi2[i]);
        }
    }

    @Test
    public void testRun() {
        String sep = System.lineSeparator();
        String pre = sep + "This is how your maze looks like:" + sep;
        String exp1 = pre + "0 1 " + System.lineSeparator() + "0 1 " + sep;
        String exp4 = pre + "0 0 0 " + System.lineSeparator() + "1 1 0 " + sep;

        tdl1.run(tdl1.getIps(), tdl1.getOut());
        tdl2.run(tdl2.getIps(), tdl2.getOut());
        tdl3.run(tdl3.getIps(), tdl3.getOut());
        tdl4.run(tdl4.getIps(), tdl4.getOut());

        assertEquals(exp1, ops1.toString());
        assertEquals("", ops2.toString());
        assertEquals("Error: Please give integers only!", ops3.toString());
        assertEquals(exp4, ops4.toString());
    }

    @Test
    public void testGetInputs() {
        for (int i = 0; i < 6; i++) {
            assertEquals(tdl1.getInputs(i), stoi1[i]);
        }
        for (int i = 0; i < 8; i++) {
            assertEquals(tdl2.getInputs(i), stoi2[i]);
        }
    }

    @Test
    public void testGetAllInts() {
        assertTrue(tdl1.getAllInts());
        assertTrue(tdl2.getAllInts());
        assertFalse(tdl3.getAllInts());
    }
}
