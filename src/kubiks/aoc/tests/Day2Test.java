package kubiks.aoc.tests;

import kubiks.aoc.day1.Day1;
import kubiks.aoc.day2.Day2;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {


    @Test
    void solve() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("1-3 a: abcde");
        data.add("1-3 b: cdefg");
        data.add("2-9 c: ccccccccc");
        Day2 day2 = new Day2(data);
        int answer = day2.solve();
        assertEquals(2, answer);
    }

    @Test
    void solveNewPolicy() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("1-3 a: abcde");
        data.add("1-3 b: cdefg");
        data.add("2-9 c: ccccccccc");
        Day2 day2 = new Day2(data);
        int answer = day2.solve(true);
        assertEquals(1, answer);
    }

    @org.junit.jupiter.api.Test
    void day2Part1() throws Exception {
        Day2 day2 = new Day2(FileReaderUtils.readStringListFromFile("resources/day2_input.txt"));
        int answer = day2.solve();
        assertEquals(548, answer);
    }

    @org.junit.jupiter.api.Test
    void day2Part2() throws Exception {
        Day2 day2 = new Day2(FileReaderUtils.readStringListFromFile("resources/day2_input.txt"));
        int answer = day2.solve(true);
        assertEquals(502, answer);
    }

}