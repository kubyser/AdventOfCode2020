package kubiks.aoc.day13;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day13 {
    long earliestTime;
    Map<Integer, Long> data;
    Map<Integer, Long> runningSums;
    Map<Integer, Long> additions;
    long maxNumber;
    int maxIndex;


    public Day13(List<String> earliestTimeAndTmetable) {
        earliestTime = Long.parseLong(earliestTimeAndTmetable.get(0));
        data = new HashMap<>();
        int pos = 0;
        for (String s : earliestTimeAndTmetable.get(1).split(",")) {
           if (s.matches("[0-9]*")) {
                data.put(pos, Long.parseLong(s));
            }
           pos ++;
        }
    }

    public long solvePart1() {
        long min = Long.MAX_VALUE;
        long minN = -1;
        for (long n : data.values()) {
            if (n == -1) {
                continue;
            }
            long minTime = n - earliestTime % n;
            if (minTime < min) {
                min = minTime;
                minN = n;
            }
        }
        return min * minN;
    }

    public long solvePart2() {
        calculateMaxNumber();

        calculateAdditions();
        long timestampMaxNumber = maxNumber;
        while (true) {
            if (isTimestampAcceptable(timestampMaxNumber-maxIndex)) {
                return timestampMaxNumber-maxIndex;
            }
            timestampMaxNumber += maxNumber;
        }
    }

    boolean isTimestampAcceptable(long timestamp) {
        for (int n=0; n<data.size(); n++) {
            if (data.get(n) == -1) {
                continue;
            }
            if ((timestamp+n) % data.get(n) != 0) {
                return false;
            }
        }
        return true;
    }

    void calculateMaxNumber() {
        maxNumber = -1;
        maxIndex = -1;
        for (int n: data.keySet()) {
            long value = data.get(n);
            if (value > maxNumber) {
                maxNumber = value;
                maxIndex = n;
            }
        }
    }


    void calculateAdditions() {
        runningSums = new HashMap<>();
        additions = new HashMap<>();
        for (int n: data.keySet()) {
            long value = data.get(n);
            additions.put(n,value *  maxNumber / value);
            runningSums.put(n, 0L);
        }
    }

    public static void main(String[] args) {
        Day13 day13 = new Day13(FileReaderUtils.readStringListFromFile("resources/day13_input.txt"));
        long answer = day13.solvePart1();
        System.out.format("Answer part 1: %d\n", answer);
        long answerPart2 = day13.solvePart2();
        System.out.format("Answer part 2: %d\n", answerPart2);
    }
}
