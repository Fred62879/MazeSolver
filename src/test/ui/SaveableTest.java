package ui;

import model.InputProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveableTest {

    InputProcess p;
    // String str = "2 2 0 0 0 1 ";

    @BeforeEach
    public void setup() {
        /*
        String input1 = "y1";
        String input2 = "y 2 2 0 0 0 1 y";
        String input3 = "y 1 2 2 0 0 0 1 y";
        InputStream ips = new ByteArrayInputStream(input1.getBytes());
        OutputStream ops = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(ops);
        process = new Process(ips, out);
        process.run(ips, out);
        String output = ops.toString();
        System.out.println("\n Printed: \n" + output);
        */
        p = new InputProcess();
    }

    @Test
    public void testSave() throws IOException {
        List<String> preStrs = Files.readAllLines(Paths.get("inputfile.txt"));
        int preSize = preStrs.size();
        // testSave(process, str);
        p.save("2 2 0 0 0 1 ");

        List<String> postStrs = Files.readAllLines(Paths.get("inputfile.txt"));
        int postSize = postStrs.size();
        assertEquals(preStrs.size() + 1, postStrs.size());
        for (int i = 0; i < preSize; i++) {
            assertEquals(preStrs.get(i), postStrs.get(i));
        }
        assertEquals(postStrs.get(postSize - 1), "2 2 0 0 0 1 ");
    }

    /*
    @Test
    public void testSave(Saveable sv, String str) throws IOException {
        sv.save(str);
    }
     */
}
