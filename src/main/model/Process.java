package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class Process implements Saveable, Loadable {

    protected Maze mz;
    protected int row;
    protected int col;
    protected Boolean allInts;
    protected Boolean quit;
    protected String record;
    protected String[] response;
    protected List<String> lines;
    protected List<Integer> inputs;

    public Process() {
        response = new String[12];
        for (int i = 0; i < 12; i++) {
            response[i] = "";
        }
        System.out.println(response[0] = "Hi there, this is a Maze solver!");

        allInts = false;
        quit = false;
        record = "";
    }

    @Override
    // EFFECTS: retrieves local maze storage and prints out
    public boolean load() throws IOException {
        lines = Files.readAllLines(Paths.get("inputfile.txt"));
        if (lines.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= lines.size(); i++) {
            System.out.println(i + ". " + lines.get(i - 1));
        }
        return true;
    }

    @Override
    // EFFECTS: saves user input maze to local storage
    public void save(String str) throws IOException {
        FileWriter fw = new FileWriter("inputfile.txt", true);
        PrintWriter writer = new PrintWriter(fw);
        writer.println(str);
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS:
    protected void stringToList(String line) {
        inputs = new ArrayList<Integer>();
        row = line.charAt(0) - '0';
        col = line.charAt(2) - '0';
        for (int i = 4; i < line.length(); i += 2) {
            inputs.add(line.charAt(i) - '0');
        }
    }

    // EFFECTS: read in integer from the user
    protected int readInt(Scanner s) {
        int res = 0;
        try {
            res = s.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println(response[1] = "Error: Please give integers only!");
            return -1;
        } catch (NoSuchElementException ex) {
            System.out.println(response[11] = "Error: No input received!");
            return -2;
        }
        return res;
    }

    // MODIFIES: this
    // EFFECTS: reads in row and column number of the maze
    //          and stores them as maze record
    protected Boolean readInRC(Scanner s) {
        System.out.println(response[2] = "Please type in the number of rows and columns of your maze below:");
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
    protected Boolean readInEntry(Scanner s) {
        System.out.println(response[3] = "Please type in all entries of your maze below (1 for path, 0 for wall):");
        int total = row * col;
        inputs = new ArrayList<Integer>();
        while (total-- > 0) {
            int c = readInt(s);
            if (c < 0) { // not int or no further input
                allInts = c == -2;
                return false;
            }
            record += (Integer.toString(c) + " ");
            inputs.add(c);
        }
        allInts = true;
        return true;
    }

    protected abstract void saveResponse() throws IOException;

    protected abstract Boolean quitResponse();

    protected abstract void readInLoop() throws IOException;

    protected abstract boolean select() throws IOException;

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public void run() throws IOException {
        if (select()) { // ipss[0] for stream
            mz = new Maze(inputs, row, col);
        } else { // ipss[1/2 for stream
            readInLoop();
        }
        if (!quit) {
            mz.showMaze();
        }
    }


    // GETTERS
    // EFFECTS: returns number of rows
    public int getRow() {
        return row;
    }

    // EFFECTS: returns number of columns
    public int getCol() {
        return col;
    }

    // EFFECTS: returns allInts
    public Boolean getAllInts() {
        return allInts;
    }

    // REQUIRES: i is less than size of inputs
    // EFFECTS: returns ith element of inputs
    public int getInputs(int i) {
        return inputs.get(i);
    }

    public Boolean getQuit() {
        return quit;
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

    // EFFECTS: returns specific response string
    public String getResponse(int i) {
        return response[i];
    }

}
