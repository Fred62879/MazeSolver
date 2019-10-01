package ui;

import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class MazeTest {

    Maze mz1;
    Maze mz2;
    Maze mz3;
    Maze mz4;
    ArrayList<Integer> ip1;
    ArrayList<Integer> ip2;
    ArrayList<Integer> ip3;
    ArrayList<Integer> ip4;

    @BeforeEach
    public void setup() {
        ip1 = new ArrayList<>(Arrays.asList(0, 1)); // valid
        ip2 = new ArrayList<>(Arrays.asList(0, 1, 1, 2)); // invalid entries
        ip3 = new ArrayList<>(Arrays.asList(0, 1, 1, 1, 0, 1)); // valid
        ip4 = new ArrayList<>(Arrays.asList(1, 1, 1)); // incorrect size

        mz1 = new Maze(ip1, 2, 1);
        mz2 = new Maze(ip2, 2, 2);
        mz3 = new Maze(ip3, 3, 2);
        mz4 = new Maze(ip4, 2, 2);
    }

    @Test
    public void testReadInMatrix() {
        assertTrue(mz1.readInMatrix());
        assertTrue(mz2.readInMatrix());
        assertTrue(mz3.readInMatrix());
        assertFalse(mz4.readInMatrix());
    }

    @Test
    public void testValidMaze() {
        assertTrue(mz1.validMaze());
        assertFalse(mz2.validMaze());
        assertTrue(mz3.validMaze());
    }

    @Test
    public void testGetRow() {
        assertEquals(mz1.getRow(), 2);
        assertEquals(mz3.getRow(), 3);
    }

    @Test
    public void testGetCol() {
        assertEquals(mz1.getCol(), 1);
        assertEquals(mz3.getCol(), 2);
    }

    @Test
    public void testGetMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 1; j++) {
                assertEquals(mz1.getMatrix(i, j), ip1.get(i * 1 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(i * 2 + j));
            }
        }
    }

    @Test
    public void testIsValid() {
        assertTrue(mz1.isValid());
        assertFalse(mz2.isValid());
        assertTrue(mz3.isValid());
        assertFalse(mz4.isValid());
    }
}