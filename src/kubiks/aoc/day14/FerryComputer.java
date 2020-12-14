package kubiks.aoc.day14;

import java.util.*;

public class FerryComputer {
    public enum CommandSet {
        MASK("mask"),
        MEM("mem\\[[0-9]+\\]");

        public final String code;

        CommandSet(String code) {
            this.code = code;
        }

        public static FerryComputer.CommandSet byCode(String label) {
            for (FerryComputer.CommandSet a : values()) {
                if (a.code.equals(label)) {
                    return a;
                }
            }
            return null;
        }
    }

    Map<Long, Long> memory= new HashMap<>();
    LinkedList<Integer> floatingMapBits = new LinkedList<>();
    long andMask=Long.MAX_VALUE;
    long orMask = 0L;

    public void setMask(String mask) {
        andMask=0L;
        orMask = 0L;
        for (char c: mask.toCharArray()) {
            orMask = c == '1' ? orMask*2+1 : orMask * 2;
            andMask = c == '0' ? andMask*2+1 : andMask * 2;
        }
        andMask = ~andMask;
    }

    public void setMaskV2(String mask) {
        setMask(mask);
        floatingMapBits.clear();
        for (int pos = 0; pos<mask.length(); pos++) {
            if (mask.charAt(pos) == 'X') {
                floatingMapBits.add(mask.length()-pos-1);
            }
        }
    }

    public long getAndMask() {
        return andMask;
    }

    public long getOrMask() {
        return orMask;
    }

    public void runCommand(String programLine) throws Exception {
        String command = programLine.split(" = ")[0];
        String value = programLine.split(" = ")[1];
        if (command.matches(CommandSet.MASK.code)) {
            setMask(value);
        } else if (command.matches(CommandSet.MEM.code)) {
            String pos = command.substring(command.indexOf('[')+1);
            pos = pos.substring(0, pos.indexOf(']'));
            long memPos = Long.parseLong(pos);
            long maskedValue = Long.valueOf(value);
            maskedValue = maskedValue & andMask;
            maskedValue = maskedValue | orMask;
            memory.put(memPos, maskedValue);
        } else {
            throw new Exception("Illegal command " + programLine);
        }
    }

    public void runProgram(List<String> program) throws Exception {
        for (String s: program) {
            runCommand(s);
        }
    }

    public long getMemoryAt(long index) {
        return memory.get(index);
    }

    public long getSumOfMemory() {
        long sum = 0;
        for (long i: memory.values()) {
            sum += i;
        }
        return sum;
    }

    void buildCombinations(LinkedList<Integer> remainingFloatingMapBits, long currentAddress, long value) {
        if (remainingFloatingMapBits.isEmpty()) {
            memory.put(currentAddress, value);
            //System.out.format("Written to address [%d] value %d\n", currentAddress, value);
            return;
        }
        LinkedList<Integer> newList = new LinkedList<>(remainingFloatingMapBits);
        int pos = newList.remove();
        long currentAddressTail = currentAddress % (1L<<pos);
        long addressWithZero = currentAddress >> (pos+1);
        addressWithZero = addressWithZero * 2;
        addressWithZero = addressWithZero << pos;
        addressWithZero = addressWithZero + currentAddressTail;
        buildCombinations(newList, addressWithZero, value);
        long addressWithOne = currentAddress >> (pos+1);
        addressWithOne = addressWithOne * 2 + 1;
        addressWithOne = addressWithOne << pos;
        addressWithOne = addressWithOne + currentAddressTail;
        buildCombinations(newList, addressWithOne, value);
    }

    public void runCommandV2(String programLine) throws Exception {
        String command = programLine.split(" = ")[0];
        String value = programLine.split(" = ")[1];
        if (command.matches(CommandSet.MASK.code)) {
            setMaskV2(value);
        } else if (command.matches(CommandSet.MEM.code)) {
            String pos = command.substring(command.indexOf('[')+1);
            pos = pos.substring(0, pos.indexOf(']'));
            long memPos = Long.parseLong(pos);
            memPos = memPos | orMask;
            buildCombinations(floatingMapBits, memPos, Long.valueOf(value));
        } else {
            throw new Exception("Illegal command " + programLine);
        }
    }

    public void runProgramV2(List<String> program) throws Exception {
        for (String s: program) {
            runCommandV2(s);
        }
    }

    public LinkedList<Integer> getFloatingMapBits() {
        return floatingMapBits;
    }
}
