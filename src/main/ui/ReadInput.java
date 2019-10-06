package ui;

import model.Maze;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReadInput {

    private Maze mz;
    private int row;
    private int col;
    private Boolean quit;
    private List<Integer> inputs;
    private List<String> lines;
    private String line;
    private String record;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public ReadInput() {
        System.out.println("Hi there, this is a Maze solver!");
        quit = false;
        record = "";
    }

    // EFFECTS: read in integer from the user
    private int readInt(Scanner s) {
        int res = 0;
        try {
            res = s.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please give integers only!");
            return -1;
        } catch (NoSuchElementException ex) {
            System.out.println("Error: No input received!");
            return -2;
        }
        return res;
    }

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

    // EFFECTS: responds to user save request
    private void saveResponse() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Save your maze to local storage? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            mz.save(record);
            System.out.println("Maze saved successfully!");
        }
    }

    // EFFECTS: responds to user quit request
    private Boolean quitResponse() {
        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to re-enter maze? (Y/N)");
        if (s.next().charAt(0) == 'n') { // quit
            return quit = true;
        } else { // do not quit
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: accept user input and initialize maze based on the input
    private void readInLoop() throws IOException {
        Boolean satisfied = false;
        while (!satisfied && !quit) {
            while (!readInRC(new Scanner(System.in)) || !readInEntry(new Scanner(System.in))) {
                if (quitResponse()) { // invalid input, quit?
                    return;
                }
            }
            mz = new Maze(inputs, row, col);
            satisfied = mz.isValid();
            if (!satisfied && quitResponse()) { // invalid maze, quit?
                return;
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
        System.out.println("Would you like to retrieve your former maze? (Y/N)");
        if (s.next().charAt(0) == 'y') {
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
            // stringToList(lines.get(choice - 1));
            return true;
        }
        return false;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public void readIn() throws IOException {
        mz = new Maze();
        if (select()) {
            mz.load(line);
        } else {
            readInLoop();
        }
        if (!quit) {
            mz.showMaze();
        }
    }

    // EFFECTS: returns
    public int choose() {
        System.out.println("Which method would you like to use for maze solving? (1-DFS/2-BFS)");
        int choice = readInt(new Scanner(System.in));
        while (choice != 1 && choice != 2) {
            System.out.println("Please select from these two methods only!");
            choice = readInt(new Scanner(System.in));
        }
        return choice;
    }



    // EFFECTS: returns maze
    public Maze getMz() {
        return mz;
    }

    // EFFECTS: returns number of rows
    public int getRow() {
        return row;
    }

    // EFFECTS: returns number of columns
    public int getCol() {
        return col;
    }

    // EFFECTS: returns quit
    public boolean getQuit() {
        return quit;
    }

    // REQUIRES: i is less than size of inputs
    // EFFECTS: returns ith element of inputs
    public int getInputs(int i) {
        return inputs.get(i);
    }

    // getters
    // EFFECTS: returns ith stored maze
    public String getLines(int i) {
        return lines.get(i);
    }

    // EFFECTS: returns number of all stored maze
    public int getLinesSize() {
        return lines.size();
    }

    public String getRecord() {
        return record;
    }
}