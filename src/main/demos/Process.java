package demos;

import model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Process {

    private MazeSolver ms;
    private MazeDisplayer md;
    private Interactions interactions;
    private ReadMaze readMaze;
    protected HashMap<Maze, List<Integer>> solved;

    public Process() {
        interactions = new Interactions();
        readMaze = new ReadMaze();
        solved = new HashMap<>();
    }

    public void display(Maze mz) {
        md = new MazeDisplayer();
        md.load(mz.getWholeMatrix());
        md.setPath(solved.get(mz));
        md.displayPath();
    }

    public void solveNow(Maze mz) {
        int choice = interactions.choose();
        if (choice == 1) {
            ms = new MazeSolverDFS(mz.getWholeMatrix());
        } else if (choice == 2) {
            ms = new MazeSolverBFS(mz.getWholeMatrix());
        }
        ms.solve(0, 0);
        solved.put(mz, ms.getPath());
    }

    public void run() throws IOException {
        System.out.println("Hi there, this is a Maze solver!");
        while (true) {
            Maze mz = readMaze.readInMaze();
            if (solved.containsKey(mz)) {
                System.out.println("Maze solution retrieved from previous storage.");
            } else {
                solveNow(mz);
            }
            display(mz);
            if (interactions.removeStorage()) {
                solved.remove(mz);
            }
            if (!interactions.solveNewMaze()) {
                break;
            }
        }
    }
}
