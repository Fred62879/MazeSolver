package model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class StreamProcess extends Process {

    private InputStream[] ipss;
    private int accu;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public StreamProcess(String[] orders) {
        accu = 0;
        ipss = new InputStream[orders.length];
        updateStream(orders);
    }

    private void updateStream(String[] orders) {
        for (int i = 0; i < orders.length; i++) {
            ipss[i] = new ByteArrayInputStream(orders[i].getBytes());
        }
    }

    // EFFECTS: responds to user save request
    protected void saveResponse() throws IOException {
        Scanner s = new Scanner(ipss[accu++]);
        System.out.println(response[4] = "Save your maze to local storage? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            save(record);
            System.out.println(response[5] = "Maze saved successfully!");
        }
    }

    // EFFECTS: responds to user quit request
    protected Boolean quitResponse() {
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
    protected void readInLoop() throws IOException {
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
    protected boolean select() throws IOException {
        Scanner s = new Scanner(ipss[accu++]); // ipss[0]
        System.out.println(response[7] = "Would you like to retrieve your former maze? (Y/N)");
        if (s.next().charAt(0) == 'y') {
            if (!load()) {
                System.out.println(response[8] = "No previous maze found!");
                return false;
            }
            System.out.println(response[9] = "Which maze would you like to restore?");
            int choice = readInt(new Scanner(ipss[accu++]));
            while (choice > lines.size()) {
                System.out.println(response[10] = "Please select those available here!");
                choice = readInt(new Scanner(ipss[accu++]));
            }
            stringToList(lines.get(choice - 1));
            return true;
        }
        return false;
    }

}

