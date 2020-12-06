package kubiks.aoc.tests;

import kubiks.aoc.day6.Day6;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    @Test
    void solve() {
        ArrayList<String> data = new ArrayList<>();
        data.add("abcxabcyabcz");
        Day6 day6 = new Day6(data);
        assertEquals(6, day6.solve());
    }

    @Test
    void solvePart2() {
        ArrayList<String> data = new ArrayList<>();
        data.add("abcx abcy abcz");
        Day6 day6 = new Day6(data);
        assertEquals(3, day6.solvePart2());
    }

    @Test
    void solveReadFromFile() {
        Day6 day6 = new Day6(FileReaderUtils.readWithDoubleCr("resources/day6_test_input.txt", false));
        assertEquals(11, day6.solve());
    }

    @Test
    void solvePart2ReadFromFile() {
        Day6 day6 = new Day6(FileReaderUtils.readWithDoubleCr("resources/day6_test_input.txt", true));
        assertEquals(6, day6.solvePart2());
    }

    @Test
    void day6Part1() {
        Day6 day6 = new Day6(FileReaderUtils.readWithDoubleCr("resources/day6_input.txt", true));
        assertEquals(7128, day6.solve());
    }
}