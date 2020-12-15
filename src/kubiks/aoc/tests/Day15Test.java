package kubiks.aoc.tests;

import kubiks.aoc.day15.Day15;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void solve() {
        Day15 day15 = new Day15();
        List<Long> data = new ArrayList<>();
        data.add(0L);
        data.add(3L);
        data.add(6L);
        assertEquals(0, day15.solve(data, 4));
        assertEquals(3, day15.solve(data, 5));
        assertEquals(3, day15.solve(data, 6));
        assertEquals(4, day15.solve(data, 9));
        assertEquals(0, day15.solve(data, 10));
        assertEquals(436, day15.solve(data, 2020));
    }

    @Test
    void solveBigNumbers() {
        Day15 day15 = new Day15();
        List<Long> data = new ArrayList<>();
        data.add(0L);
        data.add(3L);
        data.add(6L);
        assertEquals(175594, day15.solve(data, 30000000));
    }

    @Test
    void day15Part1() {
        Day15 day15 = new Day15();
        List<Long> data = new ArrayList<>();
        data.add(8L);
        data.add(0L);
        data.add(17L);
        data.add(4L);
        data.add(1L);
        data.add(12L);
        assertEquals(981, day15.solve(data, 2020));
    }

    @Test
    void day15Part2() {
        Day15 day15 = new Day15();
        List<Long> data = new ArrayList<>();
        data.add(8L);
        data.add(0L);
        data.add(17L);
        data.add(4L);
        data.add(1L);
        data.add(12L);
        assertEquals(164878, day15.solve(data, 30000000));
    }
}