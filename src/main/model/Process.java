package model;

import ui.ReadInput;

import java.io.IOException;
import java.util.List;

public class Process {

    private List<String> lines;
    private String[] response;
    private ReadInput ri;
    private MazeSolver ms;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public Process() {
        ri = new ReadInput();
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public void run() throws IOException {
        ri.readIn();
        if (ri.getQuit()) {
            System.out.println("User quit!");
        }
        int choice = ri.choose();
        if (choice == 1) {
            ms = new SolverDFS(ri.getMz().getWholeMatrix());
        } else {
            ms = new SolverBFS(ri.getMz().getWholeMatrix());
        }
        boolean solvable = ms.solve(0, 0);
        if (solvable) {
            ms.demo();
        } else {
            System.out.println("Maze unsolvable");
        }
    }
}
