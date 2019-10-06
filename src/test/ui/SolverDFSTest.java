package ui;

import model.Maze;
import model.SolverDFS;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SolverDFSTest {

    Maze mz1, mz2, mz3, mz4;
    ArrayList<Integer> ip1, ip2, ip3, ip4;
    SolverDFS sdfs;

    @BeforeEach
    public void setup() {
        ip1 = new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1));
        ip2 = new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1));
        System.out.println(ip2.size());
        ip3 = new ArrayList<>(Arrays.asList(1, 0, 0, 0));
        ip4 = new ArrayList<>(Arrays.asList(1, 1, 0, 1, 0, 1, 0, 1, 1));

        mz1 = new Maze(ip1, 6, 7);
        mz2 = new Maze(ip2, 10, 10);
        mz3 = new Maze(ip3, 2, 2);
        mz4 = new Maze(ip4, 3, 3);
    }

    @Test
    public void testSolve() {
        assertTrue()
    }

}
