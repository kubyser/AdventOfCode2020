package kubiks.aoc.tests;

import kubiks.aoc.day14.FerryComputer;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FerryComputerTest {

    @Test
    void setMask() {
        FerryComputer computer = new FerryComputer();
        computer.setMask("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        assertEquals("1000000", Long.toBinaryString(computer.getOrMask()));
        assertEquals("1111111111111111111111111111111111111111111111111111111111111101", Long.toBinaryString(computer.getAndMask()));
    }

    @Test
    void runCommand() throws Exception {
        FerryComputer computer = new FerryComputer();
        computer.runCommand("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        assertEquals("1000000", Long.toBinaryString(computer.getOrMask()));
        assertEquals("1111111111111111111111111111111111111111111111111111111111111101", Long.toBinaryString(computer.getAndMask()));
        computer.runCommand("mem[8] = 11");
        assertEquals(73, computer.getMemoryAt(8));
        computer.runCommand("mem[7] = 101");
        assertEquals(101, computer.getMemoryAt(7));
        computer.runCommand("mem[8] = 0");
        assertEquals(64, computer.getMemoryAt(8));
    }

    @Test
    void getSumOfMemory() throws Exception {
        FerryComputer computer = new FerryComputer();
        List<String> program = new ArrayList<>();
        program.add("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
        program.add("mem[8] = 11");
        program.add("mem[7] = 101");
        program.add("mem[8] = 0");
        computer.runProgram(program);
        assertEquals(165, computer.getSumOfMemory());
    }
    @Test
    void day14Part1() throws Exception {
        FerryComputer computer = new FerryComputer();
        computer.runProgram(FileReaderUtils.readStringListFromFile("resources/day14_input.txt"));
        assertEquals(6386593869035L, computer.getSumOfMemory());
    }

    @Test
    void runCommandМ2() throws Exception {
        FerryComputer computer = new FerryComputer();
        computer.runCommandV2("mask = 000000000000000000000000000000X1001X");
        assertEquals("10010", Long.toBinaryString(computer.getOrMask()));
        assertEquals(2, computer.getFloatingMapBits().size());
        assertTrue(computer.getFloatingMapBits().contains(0));
        assertTrue(computer.getFloatingMapBits().contains(5));
        computer.runCommandV2("mem[42] = 100");
        assertEquals(100, computer.getMemoryAt(26));
        assertEquals(100, computer.getMemoryAt(27));
        assertEquals(100, computer.getMemoryAt(58));
        assertEquals(100, computer.getMemoryAt(59));
        computer.runCommandV2("mask = 00000000000000000000000000000000X0XX");
        computer.runCommandV2("mem[26] = 1");
        assertEquals(1, computer.getMemoryAt(16));
        assertEquals(1, computer.getMemoryAt(17));
        assertEquals(1, computer.getMemoryAt(18));
        assertEquals(1, computer.getMemoryAt(19));
        assertEquals(1, computer.getMemoryAt(24));
        assertEquals(1, computer.getMemoryAt(25));
        assertEquals(1, computer.getMemoryAt(26));
        assertEquals(1, computer.getMemoryAt(27));
        assertEquals(100, computer.getMemoryAt(58));
        assertEquals(100, computer.getMemoryAt(59));
        assertEquals(208, computer.getSumOfMemory());
    }

    @Test
    void day14Part2() throws Exception {
        FerryComputer computer = new FerryComputer();
        computer.runProgramV2(FileReaderUtils.readStringListFromFile("resources/day14_input.txt"));
        assertEquals(4288986482164L, computer.getSumOfMemory());
    }

}