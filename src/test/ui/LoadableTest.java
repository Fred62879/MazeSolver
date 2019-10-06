package ui;

import model.Loadable;
import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class LoadableTest {

    Maze mz;

    @BeforeEach
    public void setup() {
        mz = new Maze();
    }


    public void testLoad(Loadable ldb, String str) throws IOException {
        ldb.load(str);
    }

    @Test
    public void testLoad() throws IOException {
        testLoad(mz, "2 2 0 0 1 1 ");
        int[] arr = { 0, 0, 1, 1 };
        assertEquals(2, mz.getRow());
        assertEquals(2, mz.getCol());
        for (int i = 0; i < 4; i++) {
            assertEquals(arr[i], mz.getInputs(i));
        }
        assertFalse(mz.isValid());

        testLoad(mz, "2 3 1 0 1 0 1 1 ");
        int[] arr2 = { 1, 0, 1, 0, 1, 1 };
        assertEquals(2, mz.getRow());
        assertEquals(3, mz.getCol());
        for (int i = 0; i < 6; i++) {
            assertEquals(arr2[i], mz.getInputs(i));
        }
        assertTrue(mz.isValid());

        testLoad(mz, "10 1 1 1 1 1 1 1 1 1 1 1");
        int[] arr3 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        assertEquals(10, mz.getRow());
        assertEquals(1, mz.getCol());
        for (int i = 0; i < 10; i++) {
            assertEquals(arr3[i], mz.getInputs(i));
        }
        assertTrue(mz.isValid());
    }
}