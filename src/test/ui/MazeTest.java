package ui;

import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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
        ip1 = new ArrayList<>(Arrays.asList(4, 2, 1, 0, 1));
        ip2 = new ArrayList<>(Arrays.asList(6, 2, 2, 0, 1, 1, 2));
        ip3 = new ArrayList<>(Arrays.asList(8, 3, 2, 0, 1, 1, 1, 0, 1));
        ip4 = new ArrayList<>(Arrays.asList(5, 2, 2, 1, 1, 1));

        mz1 = new Maze(ip1);
        mz2 = new Maze(ip2);
        mz3 = new Maze(ip3);
        mz4 = new Maze(ip4);
    }

    @Test
    public void testReadInRC() {
        assertTrue(mz1.readInRC());
        assertTrue(mz2.readInRC());
        assertTrue(mz3.readInRC());
        assertTrue(mz4.readInRC());
    }

    @Test
    public void testReadInMatrix() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz2.getMatrix(i, j), ip2.get(3 + i * 2 + j));
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(mz3.getMatrix(i, j), ip3.get(3 + i * 2 + j));
            }
        }
    }

    @Test
    public void testValidMaze() {
        assertTrue(mz1.validMaze());
        assertFalse(mz2.validMaze());
        assertTrue(mz3.validMaze());
        assertFalse(mz4.validMaze());
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
        assertEquals(mz1.getMatrix(0, 0), 0);
        assertEquals(mz3.getMatrix(2, 1), 1);
    }
}
