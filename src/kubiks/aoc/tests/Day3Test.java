package kubiks.aoc.tests;

import kubiks.aoc.day3.Day3;
import kubiks.aoc.utils.FileReaderUtils;
import kubiks.aoc.utils.IntVector2D;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void solve() {
        ArrayList<String> map = new ArrayList<>();
        map.add("..##.......");
        map.add("#...#...#..");
        map.add(".#....#..#.");
        map.add("..#.#...#.#");
        map.add(".#...##..#.");
        map.add("..#.##.....");
        map.add(".#.#.#....#");
        map.add(".#........#");
        map.add("#.##...#...");
        map.add("#...##....#");
        map.add(".#..#...#.#");
        Day3 day3 = new Day3(map);
        assertEquals(7, day3.solve(3, 1));
    }

    @Test
    void solveMultiply() {
        ArrayList<String> map = new ArrayList<>();
        map.add("..##.......");
        map.add("#...#...#..");
        map.add(".#....#..#.");
        map.add("..#.#...#.#");
        map.add(".#...##..#.");
        map.add("..#.##.....");
        map.add(".#.#.#....#");
        map.add(".#........#");
        map.add("#.##...#...");
        map.add("#...##....#");
        map.add(".#..#...#.#");
        Day3 day3 = new Day3(map);
        ArrayList<IntVector2D> speeds = new ArrayList<>();
        speeds.add(new IntVector2D(1, 1));
        speeds.add(new IntVector2D(3, 1));
        speeds.add(new IntVector2D(5, 1));
        speeds.add(new IntVector2D(7, 1));
        speeds.add(new IntVector2D(1, 2));
        assertEquals(336, day3.solveMultiply(speeds));
    }


    @Test
    void day3Part1() throws FileNotFoundException {
        Day3 day3 = new Day3(FileReaderUtils.readStringListFromFile("resources/day3_input.txt"));
        assertEquals(189, day3.solve(3, 1));
    }

}