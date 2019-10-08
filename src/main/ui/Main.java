package ui;

import exceptions.InvalidChoiceException;
import model.Process;

import java.io.IOException;

public class Main {

    ReadInput ri;

    public Main() throws IOException {
        ri = new ReadInput();
        ri.readIn();
        if (ri.getQuit()) {
            System.out.println("User quit!");
        } else {
            Process p = new Process(ri.getMz().getWholeMatrix());
            while (true) {
                try {
                    p.run(ri.choose());
                    break;
                } catch (InvalidChoiceException ex) {
                    System.out.println(ex.getMessage());
                } finally {
                    System.out.println("** Maze solver terminated **");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
