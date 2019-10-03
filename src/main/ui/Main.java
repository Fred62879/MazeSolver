package ui;

import model.Process;

import java.io.IOException;

public class Main {

    public Main() throws IOException {
        Process p = new Process();
        p.run();

    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
