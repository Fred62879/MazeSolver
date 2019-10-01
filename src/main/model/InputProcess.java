package model;

import java.io.IOException;
import java.util.*;

public class InputProcess extends Process {

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public InputProcess() {
    }

    // EFFECTS: responds to user save request
    protected void saveResponse() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println(response[4] = "Save your maze to local storage? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            save(record);
            System.out.println(response[5] = "Maze saved successfully!");
        }
    }

    // EFFECTS: responds to user quit request
    protected Boolean quitResponse() {
        Scanner s = new Scanner(System.in);
        System.out.println("Would you like to re-enter maze? (Y/N)");
        if (s.next().charAt(0) == 'n') { // quit
            return quit = true;
        } else { // do not quit
            return allInts = false;
        }
    }

    // MODIFIES: this
    // EFFECTS: accept user input and initialize maze based on the input
    protected void readInLoop() throws IOException {
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

    // MODIFIES: this
    // EFFECTS: retrieves selected stored maze if any
    //          returns true if such maze exists false otherwise
    protected boolean select() throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println(response[6] = "Would you like to retrieve your former maze? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            if (!load()) {
                System.out.println(response[7] = "No previous maze found!");
                return false;
            }
            System.out.println(response[8] = "Which maze would you like to restore?");
            int choice = readInt(s);
            while (choice > lines.size()) {
                System.out.println(response[9] = "Please select those available here!");
                choice = readInt(s);
            }
            stringToList(lines.get(choice - 1));
            return true;
        }
        return false;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: restores stored maze or initializes new maze
    public void run() throws IOException {
        if (select()) {
            mz = new Maze(inputs, row, col);
        } else {
            readInLoop();
        }
        if (!quit) {
            mz.showMaze();
        }
    }
}
