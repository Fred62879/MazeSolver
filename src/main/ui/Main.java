package ui;

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
            p.run(ri.choose());
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
