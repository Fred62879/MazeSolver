package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MazeDisplayer {

    private int row;
    private int col;
    private int[][] matrix;
    private List<Integer> path;
    private List<String> demoPath;
    private List<String> demoMaze;

    public MazeDisplayer() {}

    public void load(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        this.matrix = matrix;
        demoPath = new ArrayList<>();
        demoMaze = new ArrayList<>();
    }

    public int getPath(int i) {
        return path.get(i);
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public void displayMaze() {
        for (int i = 0; i < row; i++) {
            String cur = "";
            for (int j = 0; j < col; j++) {
                // System.out.print(matrix[i][j] == 0 ? "x " : "o ");
                cur += matrix[i][j] == 0 ? "x " : "o ";
            }
            // System.out.println();
            demoMaze.add(cur);
        }
    }

//    public boolean displayPath() {
//        if (path == null) {
//            System.out.println("Maze not solved yet!");
//            return false;
//        }
//        HashSet<Integer> pathSet = new HashSet<>(path);
//        System.out.println("\n" + "NOTE: * here represents path");
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(matrix[i][j] == 0 ? "x " : (pathSet.contains(i * col + j) ? "* " : "o "));
//            }
//            System.out.println();
//        }
//        return true;
//    }

    public void displayPath() {
        HashSet<Integer> pathSet = new HashSet<>(path);
        // demoPath.add("\n" + "NOTE: * here represents path" + "\n");
        for (int i = 0; i < row; i++) {
            String cur = "";
            for (int j = 0; j < col; j++) {
                cur += matrix[i][j] == 0 ? "x " : (pathSet.contains(i * col + j) ? "* " : "o ");
            }
            demoPath.add(cur);
        }
    }

    public List<String> getDemoPath() {
        return demoPath;
    }

    public List<String> getDemoMaze() {
        return demoMaze;
    }
}
