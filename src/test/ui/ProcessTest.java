package ui;

import model.Process;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessTest {


    int[][] mtrx1, mtrx2, mtrx3, mtrx4;
    Process p1, p2, p3, p4;

    @BeforeEach
    public void setup() {
        mtrx1 = new int[][]{{1, 0, 0, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1}, {1, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 1, 1, 0, 0}, {0, 1, 0, 1, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 1}};
        mtrx2 = new int[][]{{1, 1, 0, 1, 0, 0, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 1, 0, 0, 1, 0, 1}, {1, 0, 1, 1, 1, 1, 1, 1, 0, 1}, {1, 1, 0, 0, 0, 1, 0, 0, 0, 1}, {0, 1, 0, 0, 0, 1, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 1, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 0, 1, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 1, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0, 1, 1}};
        mtrx3 = new int[][]{{1, 0}, {0, 0}};
        mtrx4 = new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 1, 1}};

        p1 = new Process(mtrx1);
        p2 = new Process(mtrx2);
        p3 = new Process(mtrx3);
        p4 = new Process(mtrx4);
    }

    @Test
    public void testRun() {
        assertEquals("1 s", p1.run(1));
        //assertEquals("2 s", p1.run(2));
        assertEquals("1 s", p2.run(1));
        assertEquals("2 s", p2.run(2));
        assertEquals("Maze unsolvable!", p3.run(1));
        assertEquals("Maze unsolvable!", p3.run(2));
        assertEquals("Maze unsolvable!", p4.run(1));
        assertEquals("Maze unsolvable!", p4.run(2));
    }

}
