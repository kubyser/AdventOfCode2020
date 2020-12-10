package kubiks.aoc.tests;

import kubiks.aoc.day10.Day10;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    @Test
    void solvePart1() {
        List<Integer> data = new ArrayList<>();
        data.add(16);
        data.add(10);
        data.add(15);
        data.add(5);
        data.add(1);
        data.add(11);
        data.add(7);
        data.add(19);
        data.add(6);
        data.add(12);
        data.add(4);
        Day10 day10 = new Day10(data);
        LinkedList<Integer> solution = day10.solveFullChain();
        int answer = day10.solvePart1();
        assertEquals(35, answer);
    }

    @Test
    void solvePart1FromFile() {
        Day10 day10 = new Day10(FileReaderUtils.readIntegerListFromFile("resources/day10_test_input.txt"));
        assertEquals(220, day10.solvePart1());
    }

    @Test
    void day10Part1() {
        Day10 day10 = new Day10(FileReaderUtils.readIntegerListFromFile("resources/day10_input.txt"));
        assertEquals(2775, day10.solvePart1());
    }

    @Test
    void solvePart2() {
        List<Integer> data = new ArrayList<>();
        data.add(16);
        data.add(10);
        data.add(15);
        data.add(5);
        data.add(1);
        data.add(11);
        data.add(7);
        data.add(19);
        data.add(6);
        data.add(12);
        data.add(4);
        Day10 day10 = new Day10(data);
        long answer = day10.solvePart2();
        assertEquals(8, answer);
    }

    @Test
    void solvePart2FromFile() {
        Day10 day10 = new Day10(FileReaderUtils.readIntegerListFromFile("resources/day10_test_input.txt"));
        assertEquals(19208L, day10.solvePart2());
    }

    @Test
    void day10Part2() {
        Day10 day10 = new Day10(FileReaderUtils.readIntegerListFromFile("resources/day10_input.txt"));
        assertEquals(518344341716992L, day10.solvePart2());
    }

}