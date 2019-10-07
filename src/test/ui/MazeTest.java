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
    // Maze mz4;
    Maze mz5;

    ArrayList<Integer> ip1;
    ArrayList<Integer> ip2;
    ArrayList<Integer> ip3;
    // ArrayList<Integer> ip4;
    ArrayList<Integer> ip5;

    @BeforeEach
    public void setup() {
        ip1 = new ArrayList<>(Arrays.asList(0, 0, 0, 1)); // invalid blocked
        ip2 = new ArrayList<>(Arrays.asList(0, 1, 1, 2)); // invalid entries
        ip3 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 0, 1)); // valid
        // ip4 = new ArrayList<>(Arrays.asList(1, 1, 1)); // incorrect size
        ip5 = new ArrayList<>(Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)); // valid

        mz1 = new Maze(ip1, 2, 2);
        mz2 = new Maze(ip2, 2, 2);
        mz3 = new Maze(ip3, 3, 2);
        // mz4 = new Maze(ip4, 2, 2);
        mz5 = new Maze(ip5, 10, 1);
    }

    @Test
    public void testLoadRC() {
        mz1.load("2 2 0 0 0 1");
        mz2.load("2 2 0 1 1 2");
        mz3.load("3 2 1 1 1 1 0 1");
        mz5.load("10 1 1 1 1 1 1 1 1 1 1 1");

        assertEquals(2, mz1.getRow());
        assertEquals(2, mz1.getCol());

        assertEquals(2, mz2.getRow());
        assertEquals(2, mz2.getCol());

        assertEquals(3, mz3.getRow());
        assertEquals(2, mz3.getCol());

        assertEquals(10, mz5.getRow());
        assertEquals(1, mz5.getCol());
    }

    @Test
    public void testLoad() {
        mz1.load("2 2 0 0 0 1");
        mz2.load("2 2 0 1 1 2");
        mz3.load("3 2 1 1 1 1 0 1");
        mz5.load("10 1 1 1 1 1 1 1 1 1 1 1");

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz1.getMatrix(i, j), ip1.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz2.getMatrix(i, j), ip2.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
                assertEquals(mz5.getMatrix(i, j), ip5.get(i * 1 + j));
            }
        }

        assertFalse(mz1.validMaze());
        assertFalse(mz2.validMaze());
        assertTrue(mz3.validMaze());
        assertTrue(mz5.validMaze());
    }

    @Test
    public void testReadInMatrix() {
        mz1.readInMatrix();
        mz2.readInMatrix();
        mz3.readInMatrix();
        // mz4.readInMatrix();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz1.getMatrix(i, j), ip1.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz2.getMatrix(i, j), ip2.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
                assertEquals(mz5.getMatrix(i, j), ip5.get(i * 1 + j));
            }
        }
    }

    @Test
    public void testValidMaze() {
        assertFalse(mz1.validMaze());
        assertFalse(mz2.validMaze());
        assertTrue(mz3.validMaze());
        assertTrue(mz5.validMaze());
    }


    @Test
    public void testGetInputs() {
        for (int i = 0; i < 4; i++){
            assertEquals(ip1.get(i), mz1.getInputs(i));
        }
        for (int i = 0; i < 4; i++){
            assertEquals(ip2.get(i), mz2.getInputs(i));
        }
        for (int i = 0; i < 6; i++){
            assertEquals(ip3.get(i), mz3.getInputs(i));
        }
        for (int i = 0; i < 10; i++){
            assertEquals(ip5.get(i), mz5.getInputs(i));
        }
    }

    @Test
    public void testGetRow() {
        assertEquals(mz1.getRow(), 2);
        assertEquals(mz3.getRow(), 3);
    }

    @Test
    public void testGetCol() {
        assertEquals(mz1.getCol(), 2);
        assertEquals(mz3.getCol(), 2);
    }

    @Test
    public void testGetMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz1.getMatrix(i, j), ip1.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(i * 2 + j));
            }
        }
    }

    @Test
    public void testGetWholeMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz1.getWholeMatrix()[i][j], ip1.get(i * 2 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getWholeMatrix()[i][j], ip3.get(i * 2 + j));
            }
        }
    }

    @Test
    public void testIsValid() {
        assertFalse(mz1.isValid());
        assertFalse(mz2.isValid());
        assertTrue(mz3.isValid());
        // assertFalse(mz4.isValid());
    }
}