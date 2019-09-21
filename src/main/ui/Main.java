package ui;

import model.Maze;

public class Main {

    private Maze mz;

    // EFFECTS: interacts with the user and constructs a maze
    public Main() {
        greeting();
        askForInput();
        mz = new Maze();
    }

    // EFFECTS: gives instructions to the user
    public void askForInput() {
        System.out.println("Please input your maze below (in the order of:");
        System.out.println("row number, column number, and maze abstraction):");
    }

    // EFFECTS: greets the user
    public void greeting() {
        System.out.println("Hi there, this is a Maze solver!");
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
