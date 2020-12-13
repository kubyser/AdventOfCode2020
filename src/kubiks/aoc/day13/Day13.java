package kubiks.aoc.day13;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day13 {
    long earliestTime;
    Map<Integer, Long> data;

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
        Iterator<Integer> iterator = data.keySet().iterator();
        int curN = iterator.next();
        long start = data.get(curN);
        long step = data.get(curN);
        while (iterator.hasNext()) {
            curN = iterator.next();
            long curValue = data.get(curN);
            while ((start + curN) % curValue != 0) {
                start += step;
            }
            step *= curValue;
            //System.out.format("Completed step %d, new start=%d, new step=%d\n", curN, start, step);
        }
        return start;
    }

    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        data.add("1000066");
        data.add("13,x,x,41,x,x,x,37,x,x,x,x,x,659,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,409,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17");
        Day13 day13 = new Day13(data);

        long answer = day13.solvePart1();
        System.out.format("Answer part 1: %d\n", answer);
        long answerPart2 = day13.solvePart2();
        System.out.format("Answer part 2: %d\n", answerPart2);
    }
}
