package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Maze {

    private List<Integer> inputs;
    private int[][] matrix;
    private int row;
    private int col;

    // MODIFIES: this.inputs
    // EFFECTS: construct the maze as a matrix abstraction, validate, and show the maze
    public Maze() {
        Boolean allInts = readIn();
        if (allInts) {
            construct();
        }
    }

    // MODIFIES: this.inputs
    // EFFECTS: construct the maze as a matrix abstraction, validate, and show the maze
    public Maze(List<Integer> ip) {
        inputs = ip;
        construct();
    }

    // MODIFIES: this.row, this.col, this.allInts, this.matrix
    // EFFECTS: updates row, col, allInts, and matrix
    public void construct() {
        Boolean error = castInputError(readInRC())
                || castInputError(readInMatrix())
                || castInputError(validMaze());
        if (!error) {
            show();
        }
    }

    // REQUIRES: the user input being a sequence of space-separated integers
    // MODIFIES: this.inputs
    // EFFECTS: read in user input about maze and update this.inputs
    public Boolean readIn() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        inputs = new ArrayList<Integer>();
        while (n-- > 0) {
            int c;
            try {
                c = s.nextInt();
            } catch (InputMismatchException ex) {
                castIntegerError();
                return false;
            }
            inputs.add(c);
        }
        return true;
    }

    // REQUIRES: input size is at least 2
    // MODIFIES: this.row and this.col
    // EFFECTS: update this.row and this.col and check whether maze is of correct size
    public Boolean readInRC() {
        if (inputs.size() < 3) {
            castSizeError();
            return false;
        }
        row = inputs.get(1);
        col = inputs.get(2);
        return true;
    }

    // REQUIRES: input size is 3 larger than the specified maze size
    // MODIFIES: this.matrix
    // EFFECTS: updates this.matrix
    public Boolean readInMatrix() {
        if (row * col != inputs.size() - 3) {
            castSizeError();
            return false;
        }
        this.matrix = new int[row][col];
        int count = 3;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = inputs.get(count++);
            }
        }
        return true;
    }

    // EFFECTS: returns true if maze entries being only 0s and 1s
    //          and maze is of correct size
    public Boolean validMaze() {
        if (row * col != inputs.size() - 3) {
            castSizeError();
            return false;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // EFFECTS: visualizes the maze to the user
    public void show() {
        System.out.println();
        System.out.println("This is how your maze looks like:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("1 ");
                } else if (matrix[i][j] == 0) {
                    System.out.print("0 ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
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
    // EFFECTS: returns the maze matrix
    public int getMatrix(int i, int j) {
        return matrix[i][j];
    }

    // EFFECTS: produces error message, warning the user to give only ints
    public Boolean castIntegerError() {
        System.out.println("Error: Please give integers only!");
        return false;
    }

    // EFFECTS: produce error message, warning the user the given maze is not of correct size
    public Boolean castSizeError() {
        System.out.println("Error: maze is not of the specified size. Please double check!");
        return false;
    }

    public Boolean castInputError(Boolean pass) {
        if (!pass) {
            System.out.println("Error: Input not valid. Please double check!");
            return true;
        }
        return false;
    }
}