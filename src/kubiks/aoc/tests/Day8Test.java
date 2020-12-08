package kubiks.aoc.tests;

import kubiks.aoc.asm.Computer;
import kubiks.aoc.asm.ComputerException;
import kubiks.aoc.day8.Day8;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    void solvePart2() throws Exception {
        Day8 day8 = new Day8(FileReaderUtils.readStringListFromFile("resources/day8_test_input.txt"));
        assertEquals(8, day8.solvePart2());
    }

    @Test
    public void day8Part1() throws ComputerException {
        Computer computer = new Computer(FileReaderUtils.readStringListFromFile("resources/day8_input.txt"));
        assertEquals(Computer.ExitCode.LOOP_DETECTED, computer.run());
        assertEquals(1262, computer.getRegisterValue());
    }

    @Test
    void day8Part2() throws Exception {
        Day8 day8 = new Day8(FileReaderUtils.readStringListFromFile("resources/day8_input.txt"));
        assertEquals(1643, day8.solvePart2());
    }

}