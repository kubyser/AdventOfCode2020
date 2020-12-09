package kubiks.aoc.tests;

import kubiks.aoc.day9.Day9;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    @Test
    void solvePart1() {
        Day9 day9 = new Day9(FileReaderUtils.readLongListFromFile("resources/day9_test_input.txt"));
        assertEquals(127, day9.solvePart1(5));
    }

    @Test
    void solvePart2() {
        Day9 day9 = new Day9(FileReaderUtils.readLongListFromFile("resources/day9_test_input.txt"));
        assertEquals(62, day9.solvePart2(127));
    }

    @Test
    void day9Part1and2() {
        Day9 day9 = new Day9(FileReaderUtils.readLongListFromFile("resources/day9_input.txt"));
        assertEquals(1398413738L, day9.solvePart1(25));
        assertEquals(169521051L, day9.solvePart2(1398413738L));
    }

}