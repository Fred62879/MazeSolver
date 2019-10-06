package model;

public class SolverDFS extends MazeSolver {

    public SolverDFS(int[][] matrix) {
        super(matrix);
    }

    // EFFECTS: produces true if exists path leading to bottom-right corner from
    //          given coor, false otherwise
    private boolean helper(int cr, int cc) {
        return ((valid(cr + 1, cc) && solve(cr + 1, cc))      // down
                || (valid(cr, cc + 1) && solve(cr, cc + 1))   // right
                || (valid(cr - 1, cc) && solve(cr - 1, cc))   // left
                || (valid(cr, cc - 1) && solve(cr, cc - 1))); // up
    }

    @Override
    // EFFECTS: solves maze based on dfs
    public boolean solve(int cr, int cc) {
        int id = cr * col + cc;
        if (cr == row - 1 && cc == col - 1) {
            path.add(id);
            return true;
        }
        visited.add(id);
        path.add(id);
        if (helper(cr, cc)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
}
