package kubiks.aoc.tests;

import kubiks.aoc.day23.Day23;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {

    @Test
    void playMove() {
        Day23 day23 = new Day23("389125467");
        assertEquals("[3, 8, 9, 1, 2, 5, 4, 6, 7]", day23.getCups().toString());
        assertEquals(3, day23.getCurrent());
        day23.playMove();
        assertEquals("[3, 2, 8, 9, 1, 5, 4, 6, 7]", day23.getCups().toString());
        assertEquals(2, day23.getCurrent());
        day23.playMove();
        assertEquals("[3, 2, 5, 4, 6, 7, 8, 9, 1]", day23.getCups().toString());
        assertEquals(5, day23.getCurrent());
        day23.playMove();
        assertEquals("[3, 4, 6, 7, 2, 5, 8, 9, 1]", day23.getCups().toString());
        assertEquals(8, day23.getCurrent());
    }

    @Test
    void getCupsAsString() {
        Day23 day23 = new Day23("389125467");
        assertEquals("25467389", day23.getCupsAsString());
    }

    @Test
    void playNMoves() {
        Day23 day23 = new Day23("389125467");
        assertEquals("92658374", day23.getOrderAfterNMoves(10));

        day23 = new Day23("389125467");
        assertEquals("67384529", day23.getOrderAfterNMoves(100));
    }

    @Test
    void day23Part1() {
        Day23 day23 = new Day23("583976241");
        assertEquals("24987653", day23.getOrderAfterNMoves(100));
    }

    @Test
    void getProductAfterNMoves() {
        Day23 day23 = new Day23("389125467", true);
        assertEquals(149245887792L, day23.getProductAfterNMoves(10000000));
    }

    @Test
    void day23Part2() {
        Day23 day23 = new Day23("583976241", true);
        assertEquals(442938711161L, day23.getProductAfterNMoves(10000000));
    }


}