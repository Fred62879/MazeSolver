package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MazeSolverBFS extends MazeSolver {

    private LinkedList<Integer> queue;
    private LinkedList<List<Integer>> pathCol;

    public MazeSolverBFS(int[][] matrix) {
        super(matrix);
        queue = new LinkedList<>();
        pathCol = new LinkedList<>();
    }

    private void helper(int cr, int cc, List<Integer> curPath) {
        int[][] pairs = { {cr + 1, cc}, {cr, cc + 1}, {cr - 1, cc}, {cr, cc - 1} };
        for (int[] pair : pairs) {
            if (valid(pair[0], pair[1])) {
                int cur = pair[0] * col + pair[1];
                queue.add(cur);

                List<Integer> cppath = new ArrayList<>(curPath);
                cppath.add(cur);
                visited.add(cur);
                pathCol.add(cppath);
            }
        }
    }

    @Override
    public boolean solve(int cr, int cc) {
        queue.add(0);
        visited.add(0);
        List<Integer> ini = new ArrayList<>();
        ini.add(0);
        pathCol.add(ini);
        for (int i = 0; i < queue.size(); i++) {
            int cur = queue.get(i);
            List<Integer> curPath = pathCol.get(i);
            if (cur / col == row - 1 && cur % col == col - 1) {
                path = curPath;
                return true;
            }
            helper(cur / col, cur % col, curPath);
        }
        return false;
    }
}