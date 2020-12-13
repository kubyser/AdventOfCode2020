package kubiks.aoc.tests;

import kubiks.aoc.day13.Day13;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    @Test
    void solvePart1() {
        List<String> data = new ArrayList<>();
        data.add("939");
        data.add("7,13,x,x,59,x,31,19");
        Day13 day13 = new Day13(data);
        assertEquals(295, day13.solvePart1());
    }

    @Test
    void day13Part1() {
        List<String> data = new ArrayList<>();
        data.add("1000066");
        data.add("13,x,x,41,x,x,x,37,x,x,x,x,x,659,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,409,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17");
        Day13 day13 = new Day13(data);
        assertEquals(246, day13.solvePart1());
    }

    @Test
    void solvePart2() {
        List<String> data = new ArrayList<>();
        data.add("939");
        data.add("7,13,x,x,59,x,31,19");
        Day13 day13 = new Day13(data);
        assertEquals(1068781, day13.solvePart2());
        data.remove(1);
        data.add("17,x,13,19");
        day13 = new Day13(data);
        assertEquals(3417, day13.solvePart2());
        data.remove(1);
        data.add("67,7,59,61");
        day13 = new Day13(data);
        assertEquals(754018, day13.solvePart2());
        data.remove(1);
        data.add("67,x,7,59,61");
        day13 = new Day13(data);
        assertEquals(779210, day13.solvePart2());
        data.remove(1);
        data.add("67,7,x,59,61");
        day13 = new Day13(data);
        assertEquals(1261476, day13.solvePart2());
        data.remove(1);
        data.add("1789,37,47,1889");
        day13 = new Day13(data);
        assertEquals(1202161486, day13.solvePart2());
    }


}