package model;

public class Process {

    private MazeSolver ms;
    private int[][] matrix;
    private int choice;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public Process(int[][] matrix) {
        this.matrix = matrix;
    }

    // REQUIRES: choice is either 1 or 2
    // EFFECTS: solve maze using algr chosen by user
    public String run(int choice) {
        String res = "";
        if (choice == 1) {
            ms = new SolverDFS(matrix);
            res += "1 ";
        } else {
            ms = new SolverBFS(matrix);
            res += "2 ";
        }
        if (ms.solve(0, 0)) {
            ms.demo();
            res += "s";
        } else {
            System.out.println("Maze unsolvable!");
            return "Maze unsolvable!";
        }
        return res;
    }
}
