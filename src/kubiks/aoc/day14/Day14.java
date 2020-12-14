package kubiks.aoc.day14;

import kubiks.aoc.utils.FileReaderUtils;

public class Day14 {

    public static void main(String[] args) throws Exception {
        FerryComputer computer = new FerryComputer();
        computer.runProgram(FileReaderUtils.readStringListFromFile("resources/day14_input.txt"));
        long answer = computer.getSumOfMemory();
        System.out.format("Sum of memory after part1: %d\n", answer);
        computer = new FerryComputer();
        computer.runProgramV2(FileReaderUtils.readStringListFromFile("resources/day14_input.txt"));
        answer = computer.getSumOfMemory();
        System.out.format("Sum of memory after part2: %d\n", answer);
    }
}
