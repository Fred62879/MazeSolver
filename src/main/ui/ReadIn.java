package ui;

import exceptions.EntryInsufficientException;
import exceptions.MazeRecordInvalidException;
import model.Maze;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReadIn {

    private Maze mz;
    private int row;
    private int col;
    private List<Integer> inputs;
    private List<String> lines;
    private String line;
    private String record;


    // ** Helper read in methods

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public ReadIn() {
        mz = new Maze();
        record = "";
    }

    private int readInt(Scanner s) {
        int res = 0;
        try {
            res = s.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please give integers only!");
            return -1;
        }
        return res;
    }

    private int readChar(Scanner s) {
        char res = '#';
        try {
            res = s.next().charAt(0);
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please give integers only!");
        }
        return res;
    }

    // ** RESPONSES KITS **
    private void saveResponse() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Save your maze to local storage? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            mz.save(record);
            System.out.println("Maze saved successfully!");
        }
    }

    private boolean yesNoResponse(String prmpt) {
        System.out.println(prmpt + " (y/n)");
        return readChar(new Scanner(System.in)) == 'y';
    }

    public int choose() {
        System.out.println("Which method would you like to use for maze solving? (1-DFS/2-BFS)");
        int choice = readInt(new Scanner(System.in));
        while (choice != 1 && choice != 2) {
            System.out.println("Please select from these two methods only!");
            choice = readInt(new Scanner(System.in));
        }
        return choice;
    }

    public boolean solveNewMaze() {
        return yesNoResponse("Would you like to solve a new Maze?");
    }

    public boolean removeStorage() {
        return yesNoResponse("Would you like to remove mem for the Maze just solved?");
    }


    // ** MAZE READIN KITS **

    // MODIFIES: this
    // EFFECTS: reads in row and column number of the maze
    //          and stores them as maze record
    private Boolean readInRC(Scanner s) {
        System.out.println("Please type in the number of rows and columns of your maze below:");
        row = readInt(s);
        col = readInt(s);
        if (row != -1 && col != -1) {
            record += (Integer.toString(row) + " ");
            record += (Integer.toString(col) + " ");
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: read in matrix entry for the maze
    private Boolean readInEntry(Scanner s) {
        System.out.println("Please type in all entries of your maze below (1 for path, 0 for wall):");
        int total = row * col;
        inputs = new ArrayList<Integer>();
        while (total-- > 0) {
            int c = readInt(s);
            if (c < 0) { // not int or no further input
                return false;
            }
            record += (Integer.toString(c) + " ");
            inputs.add(c);
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: accept user input and initialize maze based on the input
    private void readInLoop() throws IOException {
        while (true) {
            while (!readInRC(new Scanner(System.in)) || !readInEntry(new Scanner(System.in))) {}
            mz.readMaze(inputs, row, col);
            mz.initialize();
            if (mz.isValid()) {
                break;
            }
        }
        saveResponse();
    }

    public boolean checkStorage() throws IOException {
        lines = Files.readAllLines(Paths.get("inputfile.txt"));
        if (lines.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= lines.size(); i++) {
            System.out.println(i + ". " + lines.get(i - 1));
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: retrieves selected stored maze if any
    //          returns true if such maze exists false otherwise
    private boolean select() throws IOException {
        Scanner s = new Scanner(System.in);
        if (yesNoResponse("Would you like to retrieve your former maze?")) {
            if (!checkStorage()) {
                System.out.println("No previous maze found!");
                return false;
            }
            System.out.println("Which maze would you like to restore?");
            int choice = readInt(s);
            while (choice > lines.size()) {
                System.out.println("Please select those available here!");
                choice = readInt(s);
            }
            line = lines.get(choice - 1);
            return true;
        }
        return false;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public Maze readInMaze() throws IOException {
        mz = new Maze();
        while (select()) {
            try {
                mz.load(line);
                return mz;
            } catch (MazeRecordInvalidException | EntryInsufficientException ex) {
                System.out.println(ex.getMessage());
            }
        }
        readInLoop();
        return mz;
    }
}
