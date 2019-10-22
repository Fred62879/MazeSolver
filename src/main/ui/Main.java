package ui;

import exceptions.InvalidChoiceException;
import model.Process;

import java.io.IOException;

public class Main {

    ReadInput ri;
    Process prcs;

    public Main() throws IOException {
        prcs = new Process();
        while (true) {
            ri = new ReadInput();
            ri.readIn();
            if (ri.getQuit()) {
                System.out.println("User quit!");
            } else {
                prcs.initialize(ri.getMz());
                while (true) {
                    try {
                        prcs.run(ri.choose());
                        break;
                    } catch (InvalidChoiceException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            if (!ri.solveNewMaze()) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
