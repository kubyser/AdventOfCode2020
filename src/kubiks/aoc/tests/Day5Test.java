package kubiks.aoc.tests;

import kubiks.aoc.day5.Day5;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    @Test
    void getSeatId() throws Exception {
        assertEquals(567, Day5.getSeatId("BFFFBBFRRR"));
        assertEquals(119, Day5.getSeatId("FFFBBBFRRR"));
        assertEquals(820, Day5.getSeatId("BBFFBBFRLL"));
    }

    @Test
    void day5Part1and2() throws Exception {
        Day5 day5 = new Day5(FileReaderUtils.readStringListFromFile("resources/day5_input.txt"));
        assertEquals(955, day5.findMaxSeatId());
        assertEquals(569, day5.findEmptySeat());
    }

}