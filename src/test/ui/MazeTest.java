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
    Maze mz5;
    ArrayList<Integer> ip1;
    ArrayList<Integer> ip2;
    ArrayList<Integer> ip3;
    ArrayList<Integer> ip4;
    ArrayList<Integer> ip5;

    @BeforeEach
    public void setup() {
        ip1 = new ArrayList<>(Arrays.asList(2, 1, 0, 1)); // valid
        ip2 = new ArrayList<>(Arrays.asList(2, 2, 0, 1, 1, 2)); // invalid entries
        ip3 = new ArrayList<>(Arrays.asList(3, 2, 0, 1, 1, 1, 0, 1)); // valid
        ip4 = new ArrayList<>(Arrays.asList(2, 2, 1, 1, 1)); // incorrect size
        ip5 = new ArrayList<>(Arrays.asList(2)); // row and col not specified completely

        mz1 = new Maze(ip1);
        mz2 = new Maze(ip2);
        mz3 = new Maze(ip3);
        mz4 = new Maze(ip4);
        mz5 = new Maze(ip5);
    }

    @Test
    public void testReadInRC() {
        assertTrue(mz1.readInRC());
        assertTrue(mz2.readInRC());
        assertTrue(mz3.readInRC());
        assertTrue(mz4.readInRC());
        assertFalse(mz5.readInRC());
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
                assertEquals(mz1.getMatrix(i, j), ip1.get(2 + i * 1 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(2 + i * 2 + j));
            }
        }
    }

     @Test
     public void testIsValid() {
        assertTrue(mz1.isValid());
        assertFalse(mz2.isValid());
        assertTrue(mz3.isValid());
         assertFalse(mz4.isValid());
         assertFalse(mz5.isValid());
     }
}
