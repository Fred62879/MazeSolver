package ui;

import model.MazeSolverDFS;
import model.Maze;
import model.MazeDisplayer;
import model.MazeSolver;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Process {

    private MazeSolver ms;
    private MazeDisplayer md;
    private ReadIn ri;
    protected HashMap<Maze, List<Integer>> solved;

    public Process() {
        ri = new ReadIn();
        solved = new HashMap<>();
    }

    public void display(Maze mz) {
        md = new MazeDisplayer(mz.getWholeMatrix());
        md.setPath(solved.get(mz));
        md.displayPath();
    }

    public void solveNow(Maze mz) {
        int choice = ri.choose();
        if (choice == 1) {
            ms = new MazeSolverDFS(mz.getWholeMatrix());
        } else if (choice == 2) {
            ms = new MazeSolverDFS(mz.getWholeMatrix());
        }
        ms.solve(0, 0);
        solved.put(mz, ms.getPath());
    }

    public void run() throws IOException {
        System.out.println("Hi there, this is a Maze solver!");
        while (true) {
            Maze mz = ri.readInMaze();
            if (solved.containsKey(mz)) {
                System.out.println("Maze solution retrieved from previous storage.");
            } else {
                solveNow(mz);
            }
            display(mz);
            if (ri.removeStorage()) {
                solved.remove(mz);
            }
            if (!ri.solveNewMaze()) {
                break;
            }
        }
    }
}
