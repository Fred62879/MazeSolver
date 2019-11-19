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

    public MazeDisplayer() {}

    public void load(int[][] matrix, List<Integer> path) {
        row = matrix.length;
        col = matrix[0].length;
        this.matrix = matrix;
        this.path = path;
        demoPath = new ArrayList<>();
    }

    public int getPath(int i) {
        return path.get(i);
    }

//    public void displayMaze() {
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(matrix[i][j] == 0 ? "x " : "o ");
//            }
//            System.out.println();
//        }
//    }

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
//        if (path == null) {
//            System.out.println("Maze not solved yet!");
//            return false;
//        }
        HashSet<Integer> pathSet = new HashSet<>(path);
        demoPath.add("\n" + "NOTE: * here represents path" + "\n");
        for (int i = 0; i < row; i++) {
            String cur = "";
            for (int j = 0; j < col; j++) {
                cur += matrix[i][j] == 0 ? "x " : (pathSet.contains(i * col + j) ? "* " : "o ");
            }
            demoPath.add(cur);
        }
//        return true;
    }

    public List<String> getDemoPath() {
        return demoPath;
    }
}
