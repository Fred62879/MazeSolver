package demos;

import exceptions.EntryInsufficientException;
import exceptions.MazeRecordInvalidException;
import model.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadMaze {

    List<String> lines;
    List<Integer> inputs;
    Interactions interactions;
    int row;
    int col;
    String record;
    String line;
    Maze mz;

    public ReadMaze() {
        interactions = new Interactions();
        mz = new Maze();
    }

    // MODIFIES: this
    // EFFECTS: reads in row and column number of the maze
    //          and stores them as maze record
    private Boolean readInRC(Scanner s) {
        System.out.println("Please type in the number of rows and columns of your maze below:");
        row = interactions.readInt(s);
        col = interactions.readInt(s);
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
            int c = interactions.readInt(s);
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
        Boolean satisfied = false;
        while (!satisfied) {
            while (!readInRC(new Scanner(System.in)) || !readInEntry(new Scanner(System.in))) { }
            mz.readMaze(inputs, row, col);
            satisfied = mz.isValid();
            if (!satisfied) { // invalid maze, quit?
                return;
            }
        }
        interactions.saveResponse();
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
        System.out.println("Would you like to retrieve your former maze? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            if (!checkStorage()) {
                System.out.println("No previous maze found!");
                return false;
            }
            System.out.println("Which maze would you like to restore?");
            int choice = interactions.readInt(s);
            while (choice > lines.size()) {
                System.out.println("Please select those available here!");
                choice = interactions.readInt(s);
            }
            line = lines.get(choice - 1);
            // stringToList(lines.get(choice - 1));
            return true;
        }
        return false;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public Maze readInMaze() throws IOException {
        Maze mz = new Maze();
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
