package ui;

import model.SolverDFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SolverDFSTest {

    int[][] mtrx1, mtrx2, mtrx3, mtrx4;
    SolverDFS sdfs1, sdfs2, sdfs3, sdfs4;

    @BeforeEach
    public void setup() {
        mtrx1 = new int[][] { {1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1}};
        mtrx2 = new int[][] { {1, 1, 0, 1, 0, 0, 0, 0, 0, 0},{0, 1, 1, 1, 1, 0, 0, 1, 1, 1},{1, 1, 0, 0, 1, 0, 0, 1, 0, 1},{1, 0, 1, 1, 1, 1, 1, 1, 0, 1},{1, 1, 0, 0, 0, 1, 0, 0, 0, 1},{0, 1, 0, 0, 0, 1, 0, 0, 1, 1},{1, 1, 0, 0, 0, 1, 1, 1, 0, 1},{1, 1, 1, 1, 1, 0, 1, 0, 1, 1},{1, 0, 0, 0, 0, 0, 1, 0, 0, 1},{1, 1, 1, 1, 1, 1, 1, 0, 1, 1}};
        mtrx3 = new int[][] { {1, 0}, {0, 0} };
        mtrx4 = new int[][] { {1, 1, 0}, {1, 0, 1}, {0, 1, 1} };
        sdfs1 = new SolverDFS(mtrx1);
        sdfs2 = new SolverDFS(mtrx2);
        sdfs3 = new SolverDFS(mtrx3);
        sdfs4 = new SolverDFS(mtrx4);
    }

    @Test
    public void testSolve() {
        assertTrue(sdfs1.solve(0, 0));
        assertTrue(sdfs2.solve(0, 0));
        assertFalse(sdfs3.solve(0, 0));
        assertFalse(sdfs4.solve(0, 0));
    }
}
