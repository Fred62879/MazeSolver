package model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StreamProcess implements Loadable, Saveable {

    private Maze mz;
    private int row;
    private int col;
    private Boolean allInts;
    private Boolean quit;
    private List<Integer> inputs;
    private List<String> lines;
    private String record;
    private String[] response;

    private InputStream[] ipss;
    private int accu;


    // EFFECTS: instructs the user to give input, reads in and checks validity
    public StreamProcess(String[] orders) {
        response = new String[12];
        for (int i = 0; i < 12; i++) {
            response[i] = "";
        }
        System.out.println(response[0] = "Hi there, this is a Maze solver!");
        allInts = false;
        quit = false;
        record = "";

        accu = 0;
        ipss = new InputStream[orders.length];
        updateStream(orders);
    }

    private void updateStream(String[] orders) {
        for (int i = 0; i < orders.length; i++) {
            ipss[i] = new ByteArrayInputStream(orders[i].getBytes());
        }
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
    private void stringToList(String line) {
        inputs = new ArrayList<Integer>();
        row = line.charAt(0) - '0';
        col = line.charAt(2) - '0';
        for (int i = 4; i < line.length(); i += 2) {
            inputs.add(line.charAt(i) - '0');
        }
    }

    // EFFECTS: read in integer from the user
    private int readInt(Scanner s) {
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
    private Boolean readInRC(Scanner s) {
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
    private Boolean readInEntry(Scanner s) {
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

    // EFFECTS: responds to user save request
    private void saveResponse() throws IOException {
        Scanner s = new Scanner(ipss[accu++]);
        System.out.println(response[4] = "Save your maze to local storage? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            save(record);
            System.out.println(response[5] = "Maze saved successfully!");
        }
    }

    // EFFECTS: responds to user quit request
    private Boolean quitResponse() {
        Scanner s = new Scanner(ipss[accu++]);
        System.out.println(response[6] = "Would you like to re-enter maze? (Y/N)");
        if (s.next().charAt(0) == 'n') { // quit
            return quit = true;
        } else { // do not quit
            return allInts = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: accept user input and initialize maze based on the input
    private void readInLoop() throws IOException {
        Boolean satisfied = false;
        while (!satisfied && !quit) {
            // ipss[1/2]
            while (!readInRC(new Scanner(ipss[accu++])) || !readInEntry(new Scanner(ipss[accu++]))) {
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

    // MODIFIES: this
    // EFFECTS: retrieves selected stored maze if any
    //          returns true if such maze exists false otherwise
    private boolean select() throws IOException {
        Scanner s = new Scanner(ipss[accu++]); // ipss[0]
        System.out.println(response[7] = "Would you like to retrieve your former maze? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            if (!load()) {
                System.out.println(response[8] = "No previous maze found!");
                return false;
            }
            System.out.println(response[9] = "Which maze would you like to restore?");
            int choice = readInt(new Scanner(ipss[accu++]));
            System.out.println("****" + choice);
            while (choice > lines.size()) {
                System.out.println(response[10] = "Please select those available here!");
                choice = readInt(new Scanner(ipss[accu++]));
                System.out.println("****" + choice);
            }
            stringToList(lines.get(choice - 1));
            return true;
        }
        return false;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public void run() throws IOException {
        if (select()) { // ipss[0]
            mz = new Maze(inputs, row, col);
        } else { // ipss[1/2
            readInLoop();
        }
        if (!quit) {
            mz.showMaze();
        }
    }



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

