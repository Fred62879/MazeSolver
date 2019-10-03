package ui;

import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaveableTest {

    Maze mz;

    @BeforeEach
    public void setup() {
        mz = new Maze();
    }

    @Test
    public void testSave() throws IOException {
        List<String> preStrs = Files.readAllLines(Paths.get("inputfile.txt"));
        int preSize = preStrs.size();
        mz.save("2 2 0 0 0 1 ");

        List<String> postStrs = Files.readAllLines(Paths.get("inputfile.txt"));
        int postSize = postStrs.size();
        assertEquals(preStrs.size() + 1, postStrs.size());
        for (int i = 0; i < preSize; i++) {
            assertEquals(preStrs.get(i), postStrs.get(i));
        }
        assertEquals(postStrs.get(postSize - 1), "2 2 0 0 0 1 ");
    }
}