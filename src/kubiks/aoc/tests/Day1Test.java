package kubiks.aoc.tests;

import kubiks.aoc.Day1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @org.junit.jupiter.api.Test
    void solve() throws Exception {
        Day1 day1 = new Day1("resources/day1_test_input.txt");
        int answer = day1.solve(2981);
        assertEquals(1446*1535, answer);
    }

    @org.junit.jupiter.api.Test
    void solveTriple() throws Exception {
        Day1 day1 = new Day1("resources/day1_test_input.txt");
        int answer = day1.solve(4587, true);
        assertEquals(1446*1535*1606, answer);
    }

    @org.junit.jupiter.api.Test
    void readListFromFile() throws FileNotFoundException {
        List<Integer> data = Day1.readListFromFile("resources/day1_test_input.txt");
        assertEquals(10, data.size());
        assertEquals(1975, data.get(0));
        assertEquals(1933, data.get(9));
    }

    @org.junit.jupiter.api.Test
    void day1Part1() throws Exception {
        Day1 day1 = new Day1("resources/day1_input.txt");
        int answer = day1.solve(2020);
        assertEquals(299299, answer);
    }

    @org.junit.jupiter.api.Test
    void day1Part2() throws Exception {
        Day1 day1 = new Day1("resources/day1_input.txt");
        int answer = day1.solve(2020, true);
        assertEquals(287730716, answer);
    }


}