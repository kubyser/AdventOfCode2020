package kubiks.aoc.day8;

import kubiks.aoc.asm.Computer;
import kubiks.aoc.asm.ComputerException;
import kubiks.aoc.utils.FileReaderUtils;

import java.util.List;

public class Day8 {

    Computer computer;

    public Day8(List<String> program) {
        computer = new Computer(program);
    }

    public int solvePart2() throws Exception {
        for (int i=0; i<computer.getProgram().size(); i++) {
            String command = computer.getProgram().get(i);
            if (command.split(" ")[0].equals("nop")) {
                computer.getProgram().set(i, "jmp "+command.split(" ")[1]);
            } else if (command.split(" ")[0].equals("jmp")) {
                computer.getProgram().set(i, "nop "+command.split(" ")[1]);
            } else {
                continue;
            }
            computer.reset();
            Computer.ExitCode exitCode = computer.run();
            if (exitCode == Computer.ExitCode.END_REACHED) {
                return computer.getRegisterValue();
            }
            computer.getProgram().set(i, command);
        }
        throw new Exception("Error: tried all replacements and still no END_REACHED");
    }

    public static void main(String[] args) throws Exception {
        Computer computer = new Computer(FileReaderUtils.readStringListFromFile("resources/day8_input.txt"));
        Computer.ExitCode result = computer.run();
        int answer = computer.getRegisterValue();
        System.out.format("Exit code: %s\nRegister value after stop: %d\n", result, answer);
        Day8 day8 = new Day8(FileReaderUtils.readStringListFromFile("resources/day8_input.txt"));
        int answerPart2 = day8.solvePart2();
        System.out.format("Register after rpart2 and END_REACHED: %d\n", answerPart2);

    }

}
