package ui;

import model.InputProcess;

import java.io.IOException;

public class Main {

    public Main() throws IOException {
        InputProcess p = new InputProcess();
        p.run();

    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
