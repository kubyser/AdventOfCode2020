package kubiks.aoc.day17;

import kubiks.aoc.utils.FileReaderUtils;

public class Day17 {
    public static void main(String[] args) {
        World world = new World(FileReaderUtils.readStringListFromFile("resources/day17_input.txt"));
        world.runCycles(6);
        long answer = world.countActiveCells();
        System.out.format("Number of active cells after 6 cycles: %d\n", answer);

        world = new World(FileReaderUtils.readStringListFromFile("resources/day17_input.txt"), true);
        world.runCycles(6);
        answer = world.countActiveCells();
        System.out.format("Number of active cells after 6 cycles in 4D mode: %d\n", answer);
    }
}
