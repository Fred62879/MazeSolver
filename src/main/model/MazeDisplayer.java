package model;

import java.util.HashSet;
import java.util.List;

public class MazeDisplayer {

    private int row;
    private int col;
    private int[][] matrix;
    private List<Integer> path;

    public MazeDisplayer(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        this.matrix = matrix;
        path = null;
    }

    public int getPath(int i) {
        return path.get(i);
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

//    public void displayMaze() {
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < col; j++) {
//                System.out.print(matrix[i][j] == 0 ? "x " : "o ");
//            }
//            System.out.println();
//        }
//    }

    public boolean displayPath() {
        if (path == null) {
            System.out.println("Maze not solved yet!");
            return false;
        }
        HashSet<Integer> pathSet = new HashSet<>(path);
        System.out.println("\n" + "NOTE: * here represents path");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] == 0 ? "x " : (pathSet.contains(i * col + j) ? "* " : "o "));
            }
            System.out.println();
        }
        return true;
    }
}
