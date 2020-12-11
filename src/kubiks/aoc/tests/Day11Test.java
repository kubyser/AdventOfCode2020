package kubiks.aoc.tests;

import kubiks.aoc.day11.Day11;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void solvePart1() {
        Day11 day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_test_input.txt"));
        assertEquals(37, day11.solve(false));
    }

    @Test
    void day11Part1() {
        Day11 day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_input.txt"));
        assertEquals(2093, day11.solve(false));
    }

    @Test
    void solvePart2() {
        Day11 day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_test_input.txt"));
        assertEquals(26, day11.solve(true));
    }

    @Test
    void day11Part2() {
        Day11 day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_input.txt"));
        assertEquals(1862, day11.solve(true));
    }

}