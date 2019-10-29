package model;

import exceptions.InvalidChoiceException;

import java.util.HashMap;
import java.util.List;

public class Process {

    private Maze currentMaze;
    private MazeSolver ms;
    private int[][] matrix; // for test purposes
    public HashMap<Maze, List<Integer>> solved;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public Process() {
        solved = new HashMap<>();
    }

    public void initialize(Maze mz) {
        this.currentMaze = mz;
        this.matrix = mz.getWholeMatrix();
    }

    public void initialize(int[][] matrix) {
        this.matrix = matrix;
    }


    public void removeCurMaze() {
        if (solved.containsKey(currentMaze)) {
            solved.remove(currentMaze);
            // currentMaze.removeProcess();
        }
    }

    public String retrieve() {
        String res = "";
        System.out.println(res = "Maze solved before: solution retrieved from storage!");
        ms.setPath(solved.get(currentMaze));
        ms.demo();
        return res;
    }

    public String solveNow(int choice) throws InvalidChoiceException {
        String res = "";
        if (choice == 1) {
            ms = new SolverDFS(matrix);
            res += "1 ";
        } else if (choice == 2) {
            ms = new SolverBFS(matrix);
            res += "2 ";
        } else {
            throw new InvalidChoiceException("Please select algorithms available here!");
        }

        if (ms.solve(0, 0)) {
            ms.demo();
            solved.put(currentMaze, ms.getPath());
            res += "s";
        } else {
            System.out.println(res = "Maze unsolvable!");
        }
        return res;
    }

    // REQUIRES: choice is either 1 or 2
    // EFFECTS: solve maze using algr chosen by user
    public String run(int choice) throws InvalidChoiceException {
        if (solved.containsKey(currentMaze)) {
            return retrieve();
        }
        return solveNow(choice);
    }
}
