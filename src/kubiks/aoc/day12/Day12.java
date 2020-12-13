package kubiks.aoc.day12;

import kubiks.aoc.utils.FileReaderUtils;

public class Day12 {
    public static void main(String[] args) throws Exception {
        FerryNavigator navigator = new FerryNavigator();
        navigator.executeProgramForShip(FileReaderUtils.readStringListFromFile("resources/day12_input.txt"));
        System.out.format("Distance for part 1: %d\n", navigator.getManhattanDistance());
        navigator = new FerryNavigator();
        navigator.executeProgramForWaypoint(FileReaderUtils.readStringListFromFile("resources/day12_input.txt"));
        System.out.format("Distance for part 2: %d\n", navigator.getManhattanDistance());
    }
}
