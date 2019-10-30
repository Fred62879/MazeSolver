package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class MazeSolver  {

    // protected Maze mz;
    protected int[][] matrix;
    protected int row;
    protected int col;
    protected List<Integer> path;
    protected HashSet<Integer> visited;
    // protected HashMap<Maze, List<Integer>> solved;

    // EFFECTS: constructs new maze solver
    public MazeSolver(int[][] matrix) {
        // this.mz = mz;
        this.matrix = matrix; //mz.getWholeMatrix();
        row = matrix.length;
        col = matrix[0].length;
        path = new ArrayList<Integer>();
        visited = new HashSet<>();
        // solved = new HashMap<>();
    }

    // EFFECTS: produces true if given coor is within matrix and not visited
    //          false otherwise
    public boolean valid(int cr, int cc) {
        return cr >= 0 && cc >= 0 && cr < row && cc < col && matrix[cr][cc] == 1 && !visited.contains(cr * col + cc);
    }

    public abstract boolean solve(int cr, int cc);

    public List<Integer> getPath() {
        return path;
    }
}
