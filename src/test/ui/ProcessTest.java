package ui;

import exceptions.InvalidChoiceException;
import model.Process;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessTest {


    int[][] mtrx1, mtrx2, mtrx3, mtrx4;
    Process p1, p2, p3, p4;

    @BeforeEach
    public void setup() {
        mtrx1 = new int[][]{{1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1}};
        mtrx2 = new int[][]{{1, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 1, 1, 1, 0, 1}, {1, 1, 0, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 1, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 0, 1, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 1}};
        mtrx3 = new int[][]{{1, 0}, {0, 0}};
        mtrx4 = new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 1, 1}};

        p1 = new Process(); p1.initialize(mtrx1);
        p2 = new Process(); p2.initialize(mtrx2);
        p3 = new Process(); p3.initialize(mtrx3);
        p4 = new Process(); p4.initialize(mtrx4);
    }

    public boolean unit(String exp1, String exp2, Process p) {
        boolean res = true;
        try {
            String s = p.run(1);
            assertEquals(exp1, s);
        } catch (InvalidChoiceException ex) {
            res = false;
            fail();
        }
        try {
            String s = p.run(2);
            assertEquals(exp2, s);
        } catch (InvalidChoiceException ex) {
            res = false;
            fail();
        }
        try {
            String s = p.run(3);
            res = false;
            fail();
        } catch (InvalidChoiceException ex) {
        }
        return res;
    }

    @Test
    public void testRun() {
        assertTrue((unit("1 s", "2 s", p1)));
        assertTrue((unit("1 s", "2 s", p2)));
        assertTrue((unit("Maze unsolvable!", "Maze unsolvable!", p3)));
        assertTrue((unit("Maze unsolvable!", "Maze unsolvable!", p4)));
    }

}
