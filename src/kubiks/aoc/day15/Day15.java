package kubiks.aoc.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day15 {
    public long solve(List<Long> input, int count) {
        Map<Long, Integer> data = new HashMap<>();
        int pos = 1;
        while (pos < input.size()) {
            data.put(input.get(pos-1), pos);
            pos++;
        }
        long lastNum = input.get(input.size()-1);
        long newLastNum;
        while (pos < count) {
            if (data.containsKey(lastNum)) {
                newLastNum = pos - data.get(lastNum);
            } else {
                newLastNum = 0L;
            }
            data.put(lastNum, pos);
            lastNum = newLastNum;
            pos++;
        }
        return lastNum;
    }

    public static void main(String[] args) {
        Day15 day15 = new Day15();
        List<Long> data = new ArrayList<>();
        data.add(8L);
        data.add(0L);
        data.add(17L);
        data.add(4L);
        data.add(1L);
        data.add(12L);
        long answer = day15.solve(data, 2020);
        System.out.format("Last number after 2020 iterations: %d\n", answer);
        long answerPart2 = day15.solve(data, 30000000);
        System.out.format("Last number after 30000000 iterations: %d\n", answerPart2);
    }
}
