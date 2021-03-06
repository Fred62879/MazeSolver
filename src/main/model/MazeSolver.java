package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class MazeSolver  {

    protected int[][] matrix;
    protected int row;
    protected int col;
    protected List<Integer> path;
    protected HashSet<Integer> visited;


    // EFFECTS: constructs new maze solver
    public MazeSolver(int[][] matrix) {
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
        path = new ArrayList<Integer>();
        visited = new HashSet<>();
    }

    // EFFECTS: produces true if given coor is within matrix and not visited false otherwise
    public boolean valid(int cr, int cc) {
        return cr >= 0 && cc >= 0 && cr < row && cc < col && matrix[cr][cc] == 1 && !visited.contains(cr * col + cc);
    }

    // EFFECTS: solve maze
    public abstract boolean solve(int cr, int cc);

    // REQUIRES: maze is solved and path is set
    // EFFECTS: return path
    public List<Integer> getPath() {
        return path;
    }
}
