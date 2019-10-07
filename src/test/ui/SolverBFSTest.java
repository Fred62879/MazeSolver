package ui;

import model.SolverBFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverBFSTest {

    int[][] mtrx1, mtrx2, mtrx3, mtrx4;
    SolverBFS sbfs1, sbfs2, sbfs3, sbfs4;

    @BeforeEach
    public void setup() {
        mtrx1 = new int[][] { {1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1}};
        mtrx2 = new int[][] { {1, 1, 0, 1, 0, 0, 0, 0, 0, 0},{0, 1, 1, 1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 0, 1, 0, 0, 1, 0, 1},{1, 0, 1, 1, 1, 1, 1, 1, 0, 1},{1, 1, 0, 0, 0, 1, 0, 0, 0, 1},{0, 1, 0, 0, 0, 1, 0, 0, 1, 1},{1, 1, 0, 0, 0, 1, 1, 1, 0, 1},{1, 1, 1, 1, 1, 0, 1, 0, 1, 1},{1, 0, 0, 0, 0, 0, 1, 0, 0, 1},{1, 1, 1, 1, 1, 1, 1, 0, 1, 1}};
        mtrx3 = new int[][] { {1, 0}, {0, 0} };
        mtrx4 = new int[][] { {1, 1, 0}, {1, 0, 1}, {0, 1, 1} };
        sbfs1 = new SolverBFS(mtrx1);
        sbfs2 = new SolverBFS(mtrx2);
        sbfs3 = new SolverBFS(mtrx3);
        sbfs4 = new SolverBFS(mtrx4);
    }

    @Test
    public void testSolve() {
        assertTrue(sbfs1.solve(0, 0));
        assertTrue(sbfs2.solve(0, 0));
        assertFalse(sbfs3.solve(0, 0));
        assertFalse(sbfs4.solve(0, 0));
    }
}
