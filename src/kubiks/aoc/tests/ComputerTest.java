package kubiks.aoc.tests;

import kubiks.aoc.asm.Computer;
import kubiks.aoc.asm.ComputerException;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Test
    public void accTest() throws ComputerException {
        List<String> program = new ArrayList<>();
        program.add("acc +3");
        Computer computer = new Computer(program);
        computer.runStep();
        assertEquals(1, computer.getPosition());
        assertEquals(3, computer.getRegisterValue());
        program = new ArrayList<>();
        program.add("acc +3");
        program.add("acc -1");
        program.add("acc +10");
        computer = new Computer(program);
        assertEquals(Computer.ExitCode.END_REACHED, computer.run());
        assertEquals(12, computer.getRegisterValue());
    }

    @Test
    public void jmpTest() throws ComputerException {
        List<String> program = new ArrayList<>();
        program.add("jmp +2");
        program.add("acc +3");
        program.add("acc +3");
        Computer computer = new Computer(program);
        computer.runStep();
        assertEquals(2, computer.getPosition());
        assertEquals(0, computer.getRegisterValue());
        assertEquals(Computer.ExitCode.END_REACHED, computer.run());
        assertEquals(3, computer.getRegisterValue());
    }

    @Test
    public void infiniteLoopFromFile() throws ComputerException {
        Computer computer = new Computer(FileReaderUtils.readStringListFromFile("resources/day8_test_input.txt"));
        assertEquals(Computer.ExitCode.LOOP_DETECTED, computer.run());
        assertEquals(5, computer.getRegisterValue());
    }


    @Test
    public void resetTest() throws ComputerException {
        List<String> program = new ArrayList<>();
        program.add("acc +3");
        program.add("acc -1");
        program.add("acc +10");
        Computer computer = new Computer(program);
        assertEquals(Computer.ExitCode.END_REACHED, computer.run());
        assertEquals(12, computer.getRegisterValue());
        computer.reset();
        assertEquals(Computer.ExitCode.END_REACHED, computer.run());
        assertEquals(12, computer.getRegisterValue());
    }


}