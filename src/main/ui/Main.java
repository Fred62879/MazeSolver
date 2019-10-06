package ui;

import model.Process;
import java.io.IOException;

public class Main {

    public Main() throws IOException {
        Process p = new Process();
        p.run();
        String res = "";
        String a = "1 1 0 1 0 0 0 0 0 0 0 1 1 1 1 0 0 1 1 1 1 1 0 0 1 0 0 1 0 1 1 0 1 1 1 1 1 1 0 1 1 1 0 0 0 1 0 0 0 1 0 1 0 0 0 1 0 0 1 1 1 1 0 0 0 1 1 1 0 1 1 1 1 1 1 0 1 0 1 1 1 0 0 0 0 0 1 0 0 1 1 1 1 1 1 1 1 0 1 1";
        for (int i = 0; i < a.length(); i += 2) {
            res += a.substring(i, i + 1) + ", ";
        }
        // System.out.println(res);

    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
    }
}
