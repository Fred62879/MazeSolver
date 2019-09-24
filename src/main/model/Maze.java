package model;

import java.io.PrintStream;
import java.util.List;

public class Maze {

    private List<Integer> inputs;
    private int[][] matrix;
    private int row;
    private int col;
    private Boolean valid;

    // EFFECTS: constructs the maze from user input and validates maze
    public Maze(List<Integer> ip) {
        inputs = ip;
        valid = readInRC() && readInMatrix() && validMaze();
    }

    // MODIFIES: this
    // EFFECTS: update this.row and this.col and check whether input is of correct format
    //          returns true if no error, false otherwise
    public Boolean readInRC() {
        if (inputs.size() < 2) {
            System.out.println("Warning: row and column numbers are not received");
            return false;
        }
        row = inputs.get(0);
        col = inputs.get(1);
        return true;
    }

    // REQUIRES: input contains at least two entries being row and column numbers
    // MODIFIES: this
    // EFFECTS: updates matrix using inputs or cast error if matrix size incorrect
    //          returns true if no error, false otherwise
    public Boolean readInMatrix() {
        if (row * col != inputs.size() - 2) {
            System.out.println("Warning: maze is not of the specified size");
            return false;
        }
        this.matrix = new int[row][col];
        int count = 2;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = inputs.get(count++);
            }
        }
        return true;
    }

    // REQUIRES: maze is of the specified size
    // EFFECTS: returns true if maze entries being only 0s and 1s
    //          and maze is of correct size, false otherwise
    public Boolean validMaze() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    System.out.println("Warning: Maze contains elements other than 0 and 1");
                    return false;
                }
            }
        }
        return true;
    }

    // EFFECTS: solves given maze providing one possible path
    public void solve() {

    }

    // REQUIRES: maze is valid
    // EFFECTS: prints the maze
    public void showMaze() {
        System.out.println();
        System.out.println("This is how your maze looks like:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] == 1 ? "1 " : "0 ");
            }
            System.out.println();
        }
    }

    // REQUIRES: maze is valid
    // EFFECTS: prints the maze
    public void showMaze(PrintStream out) {
        out.println();
        out.println("This is how your maze looks like:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                out.print(matrix[i][j] == 1 ? "1 " : "0 ");
            }
            out.println();
        }
    }

    // EFFECTS: returns the row number of matrix
    public int getRow() {
        return row;
    }

    // EFFECTS: returns the column number of matrix
    public int getCol() {
        return col;
    }

    // REQUIRES: i < row, j < col
    // EFFECTS: returns the specified maze matrix entry
    public int getMatrix(int i, int j) {
        return matrix[i][j];
    }

    public Boolean isValid() {
        return valid;
    }
}