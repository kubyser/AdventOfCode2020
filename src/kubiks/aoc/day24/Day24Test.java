package kubiks.aoc.day24;

import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day24Test {

    @Test
    void pathToPos() {
        assertEquals("-1:0", Day24.pathToPos("w"));
        assertEquals("1:0", Day24.pathToPos("e"));
        assertEquals("1:1", Day24.pathToPos("ne"));
        assertEquals("0:1", Day24.pathToPos("nw"));
        assertEquals("0:-1", Day24.pathToPos("se"));
        assertEquals("-1:-1", Day24.pathToPos("sw"));
        assertEquals("3:0", Day24.pathToPos("esenee"));
        assertEquals("0:-1", Day24.pathToPos("esew"));
        assertEquals("0:0", Day24.pathToPos("nwwswee"));
    }

    @Test
    void flipTile() {
        Day24 day24 = new Day24();
        day24.flipTileByPath("esenee");
        assertTrue(day24.blacks.contains("3:0"));
        day24.flipTileByPath("nwwswee");
        assertTrue(day24.blacks.contains("0:0"));
        day24.flipTileByPath("esenee");
        assertFalse(day24.blacks.contains("3:0"));
    }

    @Test
    void flipTiles() {
        Day24 day24 = new Day24();
        assertEquals(10, day24.flipTilesByPaths(FileReaderUtils.readStringListFromFile("resources/day24_test_input.txt")));
    }

    @Test
    void day24Part1and2() {
        Day24 day24 = new Day24();
        assertEquals(523, day24.flipTilesByPaths(FileReaderUtils.readStringListFromFile("resources/day24_input.txt")));
        assertEquals(4225, day24.playNRounds(100));
    }

    @Test
    void playRound() {
        Day24 day24 = new Day24();
        day24.flipTileByPos("0:0");
        assertEquals(1, day24.getNumBlacks());
        day24.playRound();
        assertEquals(0, day24.getNumBlacks());
        day24.flipTileByPos("0:0");
        day24.flipTileByPos("1:0");
        day24.playRound();
        assertEquals(4, day24.getNumBlacks());

        day24 = new Day24();
        day24.flipTilesByPaths(FileReaderUtils.readStringListFromFile("resources/day24_test_input.txt"));
        day24.playRound();
        assertEquals(15, day24.getNumBlacks());
        day24.playRound();
        assertEquals(12, day24.getNumBlacks());
        day24.playRound();
        assertEquals(25, day24.getNumBlacks());
    }

    @Test
    void playNRounds() {
        Day24 day24 = new Day24();
        day24.flipTilesByPaths(FileReaderUtils.readStringListFromFile("resources/day24_test_input.txt"));
        assertEquals(2208, day24.playNRounds(100));
    }
}