package kubiks.aoc.tests;

import kubiks.aoc.day18.Day18;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @Test
    void day18Part1() {
        Day18 day18 = new Day18();
        assertEquals(9535936849815L, day18.calculateSum(FileReaderUtils.readStringListFromFile("resources/day18_input.txt"), false));
    }

    @Test
    void day18Part2() {
        Day18 day18 = new Day18();
        assertEquals(472171581333710L, day18.calculateSum(FileReaderUtils.readStringListFromFile("resources/day18_input.txt"), true));
    }

}