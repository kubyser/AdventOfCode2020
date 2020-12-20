package kubiks.aoc.tests;

import kubiks.aoc.day20.TileArray;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileArrayTest {

    @Test
    void matchAll() {
        TileArray ta = new TileArray(FileReaderUtils.readStringListFromFile("resources/day20_test_input.txt"));
        assertTrue(ta.matchAll());
        assertEquals(2971, ta.getTileAt(ta.getMinX(), ta.getMaxY()).getId());
        assertEquals(1951, ta.getTileAt(ta.getMinX(), ta.getMinY()).getId());
        assertEquals(3079, ta.getTileAt(ta.getMaxX(), ta.getMinY()).getId());
        assertEquals(1171, ta.getTileAt(ta.getMaxX(), ta.getMaxY()).getId());
    }
}