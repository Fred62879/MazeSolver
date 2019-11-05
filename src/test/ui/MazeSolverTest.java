package ui;

import model.MazeSolver;
import model.MazeSolverDFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeSolverTest {

    int[][] mtrx1;
    MazeSolver ms1;

    @BeforeEach
    public void setup() {
        mtrx1 = new int[][] { {1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1}};
        ms1 = new MazeSolverDFS(mtrx1);
    }

    @Test
    public void testValid() {
        assertTrue(ms1.valid(0, 0));
        assertFalse(ms1.valid(0, -1));
        assertFalse(ms1.valid(-1, 0));
        assertFalse(ms1.valid(7, 0));
        assertFalse(ms1.valid(0, 8));
        assertFalse(ms1.valid(0, 1));
        ms1.solve(0, 0);
        assertFalse(ms1.valid(0, 0));
    }

    @Test
    public void testSolve() {
        assertTrue(ms1.solve(0, 0));
    }

    @Test
    public void testGetPath() {

    }
}

