package kubiks.aoc.tests;

import kubiks.aoc.day20.Day20;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day20Test {

    @Test
    void solvePart1and2() {
        Day20 day20 = new Day20(FileReaderUtils.readStringListFromFile("resources/day20_test_input.txt"));
        assertEquals(20899048083289L, day20.solvePart1());
        assertEquals(273, day20.solvePart2());
    }

    @Test
    void day20Part1and2() {
        Day20 day20 = new Day20(FileReaderUtils.readStringListFromFile("resources/day20_input.txt"));
        assertEquals(111936085519519L, day20.solvePart1());
        assertEquals(1792, day20.solvePart2());
    }


}