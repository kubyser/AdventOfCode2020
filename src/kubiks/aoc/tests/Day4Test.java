package kubiks.aoc.tests;

import kubiks.aoc.day4.Day4;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void countValidPart1() {
        Day4 day4 = new Day4(FileReaderUtils.readWithDoubleCr("resources/day4_test_input.txt"));
        assertEquals(2, day4.countValid(false));
    }

    @Test
    void day4Part1() {
        Day4 day4 = new Day4(FileReaderUtils.readWithDoubleCr("resources/day4_input.txt"));
        assertEquals(210, day4.countValid(false));
    }

    @Test
    void day4Part2() {
        Day4 day4 = new Day4(FileReaderUtils.readWithDoubleCr("resources/day4_input.txt"));
        assertEquals(131, day4.countValid(true));
    }
}