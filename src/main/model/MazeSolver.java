package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class MazeSolver {

    protected int[][] matrix;
    protected int row;
    protected int col;
    protected int cur;
    protected List<Integer> path;
    protected HashSet<Integer> visited;

    public MazeSolver() {    }

    // EFFECTS: constructs new maze solver
    public MazeSolver(int[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
        cur = 0;
        path = new ArrayList<Integer>();
        visited = new HashSet<>();
    }

    // EFFECTS: produces true if given coor is within matrix and not visited
    //          false otherwise
    protected boolean valid(int cr, int cc) {
        return cr >= 0 && cc >= 0 && cr < row && cc < col && matrix[cr][cc] == 1 && !visited.contains(cr * col + cc);
    }

    protected abstract boolean solve(int cr, int cc);

    // REQUIRES: maze solvable
    // EFFECTS: prints one possible maze path
    public void demo() {
        HashSet<Integer> pathSet = new HashSet<>(path);
        System.out.println("\n" + "NOTE: * here represents path");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] == 0 ? "x " : (pathSet.contains(i * col + j) ? "* " : "o "));
            }
            System.out.println();
        }
    }

}