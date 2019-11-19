package ui;

import model.MazeDisplayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazeDisplayerTest {

    MazeDisplayer md;
    List<Integer> path;

    @BeforeEach
    public void setup() {
        int[][] matrix = {{1,0,1,1},{1,0,0,0}};
        md = new MazeDisplayer();
        path = new ArrayList<Integer>();
        path.add(0);
        path.add(1);
        path.add(5);
        path.add(6);
        path.add(7);
        md.load(matrix, path);
    }

    @Test
    public void testSetPath() {
        assertEquals(0, md.getPath(0));
        assertEquals(1, md.getPath(1));
        assertEquals(5, md.getPath(2));
        assertEquals(6, md.getPath(3));
        assertEquals(7, md.getPath(4));
    }

    @Test
    public void testDisplayPath() {
        md.displayPath();
//        assertFalse();
//        md.setPath(path);
//        assertTrue(md.displayPath());
    }

}
