package model;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    private List<Integer> inputs;
    private Boolean allInts;
    private Maze mz;

    private InputStream ips;
    private PrintStream out;

    // EFFECTS: instructs the user to give input, reads in and checks validity
    public ToDoList() {
        instructions();
        readIn();
    }

    // FFECTS: instructs the user to give input, reads in and checks validity
    public ToDoList(InputStream ips, PrintStream out) {
        instructions();
        this.ips = ips;
        this.out = out;
        readIn(ips, out);
    }


    public Boolean readIn() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        inputs = new ArrayList<Integer>();
        while (n-- > 0) {
            int c;
            try {
                c = s.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Error: Please give integers only!");
                allInts = false;
                return false;
            }
            inputs.add(c);
        }
        allInts = true;
        return true;
    }

    // MODIFIES: this
    // EFFECTS: read in user input about maze and updatas inputs
    //          returns true if no error, false otherwise
    public Boolean readIn(InputStream ips, PrintStream out) {
        Scanner s = new Scanner(ips);
        int n = s.nextInt();
        inputs = new ArrayList<Integer>();
        while (n-- > 0) {
            int c;
            try {
                c = s.nextInt();
            } catch (InputMismatchException ex) {
                out.print("Error: Please give integers only!");
                allInts = false;
                return false;
            }
            inputs.add(c);
        }
        allInts = true;
        return true;
    }

    // REQUIRES: user input consists of only integers
    // EFFECTS: initializes maze object
    public void run() {
        if (allInts) {
            mz = new Maze(inputs);
            if (mz.isValid()) {
                mz.showMaze();
            }
        }
    }

    // REQUIRES: user input consists of only integers
    // EFFETCS: initializes maze object
    public void run(InputStream ips, PrintStream out) {
        if (allInts) {
            mz = new Maze(inputs);
            if (mz.isValid()) {
                mz.showMaze(out);
            }
        }
    }

    // REQUIRES: i is less than size of inputs
    // EFFECTS: returns ith element of inputs
    public int getInputs(int i) {
        return inputs.get(i);
    }

    // EFFECTS: returns allInts
    public Boolean getAllInts() {
        return allInts;
    }

    // EFFECTS: returns ips
    public InputStream getIps() {
        return ips;
    }

    // EFFECTS: returns out
    public PrintStream getOut() {
        return out;
    }

    // EFFECTS: gives instructions to the user
    public void instructions() {
        System.out.println("Hi there, this is a Maze solver!");
        System.out.println("Please input your maze as a list of integers in the order of:");
        System.out.println("-1- total number of integers following this first integer;");
        System.out.println("-2/3- row number, column number;");
        System.out.println("-4~- maze entries, use 1 to represent path and 0 represents wall.");
    }

}
