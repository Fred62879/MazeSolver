package ui;

import model.MazeSolverUI;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        Process process = new Process();
//        process.run();
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MazeSolverUI().initialize();
            }
        });
    }
}
