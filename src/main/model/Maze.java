package model;

public class Maze {
    public int[][] matrix;
    public int numRow;
    public int numCol;

    public Maze(int[][] matrix) {
        numRow = matrix.length;
        numCol = matrix[0].length;
        this.matrix = matrix;
        if (!validMaze()) {
            System.out.println("Maze invalid!");
        }
    }

    public Boolean validMaze() {
        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numCol; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}