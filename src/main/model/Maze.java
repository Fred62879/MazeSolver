package model;

import exceptions.EntryBlockedException;
import exceptions.EntryInsufficientException;
import exceptions.EntryInvalidException;
import exceptions.MazeRecordInvalidException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Saveable, Loadable {

    private List<Integer> inputs;
    private int row;
    private int col;
    private int[][] matrix;
    private Boolean valid;

    // EFFECTS: initializes an empty maze
    public Maze() {    }

    // EFFECTS: constructs the maze from user input and validates maze
    public Maze(List<Integer> ip, int row, int col) {
        inputs = ip;
        this.row = row;
        this.col = col;
        initialize();
    }

    // EFFECTS: converts inputs into matrix representation
    //          if entries other than 0 and 1 given throws exception
    //          if start (top-left) or end (bottom-right) entry being 0 throws exception
    public void initialize() {
        readInMatrix();
        try {
            validMaze();
            valid = true;
        } catch (EntryInvalidException ex) {
            System.out.println(ex.getMessage());
        } catch (EntryBlockedException ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("Maze read in terminated");
        }
    }

    // REQUIRES: input contains at least two entries being row and column numbers
    // MODIFIES: this
    // EFFECTS: updates matrix using inputs or cast error if matrix size incorrect
    //          returns true if no error, false otherwise
    public void readInMatrix() {
        this.matrix = new int[row][col];
        for (int i = 0, count = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = inputs.get(count++);
            }
        }
    }

    // REQUIRES: maze is of the specified size
    // EFFECTS: returns true if maze entries being only 0s and 1s
    //          and maze is of correct size, false otherwise
    public void validMaze() throws EntryBlockedException, EntryInvalidException {
        if (matrix[0][0] == 0 || matrix[row - 1][col - 1] == 0) {
            throw new EntryBlockedException("Warning: Maze start or end are blocked!");
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != 1) {
                    throw new EntryInvalidException("Warning: Maze contains elements other than 0 and 1");
                }
            }
        }
    }

    // REQUIRES: maze is valid
    // EFFECTS: prints the maze
    public void showMaze() {
        System.out.println();
        System.out.println("This is how your maze looks like:");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(matrix[i][j] == 1 ? "o " : "x ");
            }
            System.out.println();
        }
    }

    @Override
    // EFFECTS: saves user input maze to local storage
    public void save(String str) throws IOException {
        FileWriter fw = new FileWriter("inputfile.txt", true);
        PrintWriter writer = new PrintWriter(fw);
        writer.println(str);
        writer.close();
    }

    // Load kits
    @Override
    // EFFECTS: retrieves local maze storage and prints out
    public void load(String line) throws EntryInsufficientException, MazeRecordInvalidException {
        if (!loadPreCheck(line)) {
            throw new MazeRecordInvalidException("Selected record is not uniformly single-space separated");
        }
        loadInputs(line, loadRC(line));
        initialize();
    }

    // EFFECTS: returns true if int tokens in line is single-space
    //          separated, false otherwise
    private boolean loadPreCheck(String line) {
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) == ' ' && line.charAt(i - 1) == ' ') {
                return false;
            }
        }
        return true;
    }

    // EFFECTS: reads in row and column number from record
    //          and returns the start index of maze entry
    private int loadRC(String line) {
        int prev = 0;
        int[] res = new int[2];
        for (int i = 0, ct = 0; ct < 2; i++) {
            if (line.charAt(i) == ' ') {
            // if (Character.isWhitespace(line.charAt(i))) {
                res[ct++] = Integer.parseInt(line.substring(prev, i));
                prev = i + 1;
            }
        }
        row = res[0];
        col = res[1];
        return prev;
    }

    // EFFECTS: reads in tokens in line as int maze entries
    //          throws exception if # of entries does not fit
    //          matrix with specificed row and column number
    private void loadInputs(String line, int start) throws EntryInsufficientException {
        inputs = new ArrayList<Integer>();
        for (int i = start; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                inputs.add(line.charAt(i) - '0');
            }
        }
        if (inputs.size() != row * col) {
            throw new EntryInsufficientException("Not enough entries for the given maze size");
        }
    }


    // Getters
    // EFFECTS: returns the ith input entry
    public int getInputs(int i) {
        return inputs.get(i);
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

    // EFFECTS: returns the matrix
    public int[][] getWholeMatrix() {
        return matrix;
    }

    public Boolean isValid() {
        return valid;
    }
}