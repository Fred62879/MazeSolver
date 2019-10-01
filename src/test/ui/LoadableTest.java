package ui;

import model.Loadable;
import model.InputProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


public class LoadableTest {

    InputProcess p;

    @BeforeEach
    public void setup() {
        p = new InputProcess();
    }

    @Test
    public void testLoad() throws IOException {
        int preSize = Files.readAllLines(Paths.get("inputfile.txt")).size();

        FileWriter fw = new FileWriter("inputfile.txt", true);
        PrintWriter writer = new PrintWriter(fw);
        writer.println("2 2 0 0 1 0 ");
        writer.close();

        assertTrue(testLoad(p));
        assertEquals(1 + preSize, p.getLinesSize());
        assertEquals("2 2 0 0 1 0 ", p.getLines(preSize));

        fw = new FileWriter("inputfile.txt", true);
        writer = new PrintWriter(fw);
        writer.println("2 3 0 0 1 0 1 1 ");
        writer.close();

        assertTrue(testLoad(p));
        assertEquals(2 + preSize, p.getLinesSize());
        assertEquals("2 2 0 0 1 0 ", p.getLines(preSize));
        assertEquals("2 3 0 0 1 0 1 1 ", p.getLines(preSize + 1));
    }

    @Test
    public boolean testLoad(Loadable ldb) throws IOException {
        return ldb.load();
    }
}
