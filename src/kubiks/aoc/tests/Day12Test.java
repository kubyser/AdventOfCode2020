package kubiks.aoc.tests;

import kubiks.aoc.day12.FerryNavigator;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void day12Part1() throws Exception {
        FerryNavigator navigator = new FerryNavigator();
        navigator.executeProgramForShip(FileReaderUtils.readStringListFromFile("resources/day12_input.txt"));
        assertEquals(938, navigator.getManhattanDistance());
    }

    @Test
    void day12Part2() throws Exception {
        FerryNavigator navigator = new FerryNavigator();
        navigator.executeProgramForWaypoint(FileReaderUtils.readStringListFromFile("resources/day12_input.txt"));
        assertEquals(54404, navigator.getManhattanDistance());
    }

}