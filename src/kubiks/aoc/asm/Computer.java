package kubiks.aoc.asm;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    public enum Instruction {
        NOP("nop"),
        ACC("acc"),
        JMP("jmp");

        public final String code;

        private Instruction(String code) {
            this.code = code;
        }

        public static Instruction byCode(String label) {
            for (Instruction a : values()) {
                if (a.code.equals(label)) {
                    return a;
                }
            }
            return null;
        }
    }

    public enum ExecutionMode {
        STEP_BY_STEP,
        CONTINUOUS
    }

    public enum ExitCode {
        END_REACHED,
        LOOP_DETECTED,
        STEP_COMPLETED,
        ERROR
    }

    List<String> program;
    int position;
    int register;

    List<Integer> metrics;

    public Computer(List<String> program) {
        this.program = program;
        position = 0;
        register = 0;
        metrics = new ArrayList<>();
        program.forEach(c -> metrics.add(0));
    }

    public void reset() {
        position = 0;
        register = 0;
        metrics = new ArrayList<>();
        program.forEach(c -> metrics.add(0));
    }

    void executeAcc(int param) {
        register += param;
        position ++;
    }

    void executeNop(int param) {
        position++;
    }

    void executeJmp(int param) {
        position += param;
    }

    public ExitCode runInMode(ExecutionMode executionMode) throws ComputerException {
        while (true) {
            validatePosition();
            if (addressVisited() && executionMode != ExecutionMode.STEP_BY_STEP) {
                return ExitCode.LOOP_DETECTED;
            }
            metrics.set(position, metrics.get(position) + 1);
            String command = program.get(position);
            int instructionParam = getCurrentParameter();
            Instruction instruction = getCurrentInstruction();
            switch (instruction) {
                case NOP: executeNop(instructionParam); break;
                case ACC: executeAcc(instructionParam); break;
                case JMP: executeJmp(instructionParam); break;
                default: throw new ComputerException("Unknown command ["+command+"] at pos ["+position+"]");
            }
            if (executionMode == ExecutionMode.STEP_BY_STEP) {
                return ExitCode.STEP_COMPLETED;
            }
            if (pastEndOfProgram()) {
                return ExitCode.END_REACHED;
            }
        }
    }

    public ExitCode run() throws ComputerException {
        return runInMode(ExecutionMode.CONTINUOUS);
    }

    public ExitCode runStep() throws ComputerException {
        return runInMode(ExecutionMode.STEP_BY_STEP);
    }


    void validatePosition() throws ComputerException {
        if (! (position >= 0 && position < program.size())) {
            throw new ComputerException("Execution pointer position ["+position+"] is invalid");
        }
    }

    boolean pastEndOfProgram() {
        return position == program.size();
    }

    String getInstructionCode(String command) {
        return  command.split(" ")[0];
    }

    int getInstructionParameter(String command) {
        return  Integer.parseInt(command.split(" ")[1]);
    }

    public int getRegisterValue() {
        return register;
    }

    public int getPosition() {
        return position;
    }

    public boolean addressVisited() {
        return metrics.get(position) > 0;
    }

    public Instruction getCurrentInstruction() {
        return Instruction.byCode(program.get(position).split(" ")[0]);
    }

    public int getCurrentParameter() {
        return Integer.parseInt(program.get(position).split(" ")[1]);
    }

    public List<String> getProgram() {
        return program;
    }

}
