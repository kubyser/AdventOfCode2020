package kubiks.aoc.day9;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 {
    List<Long> data;
    Map<Long, Integer> windowAndOccurrences;
    Map<Long, Map<Long, Long>> sums;


    public Day9(List<Long> data) {
        this.data = data;
        windowAndOccurrences = new HashMap<>();
        sums = new HashMap<>();
    }

    public long solvePart1(int preambleLength) {
        int pos = preambleLength;
        for (int i=0; i<preambleLength; i++) {
            Long value = data.get(i);
            if (addToOccurrencies(value)) {
                addToSums(value);
            }
        }
        while (pos < data.size()) {
            Long value = data.get(pos);
            if (!sums.values().stream().anyMatch(
                    m -> m.values().stream().anyMatch(v -> v.equals(value)))) {
                return value;
            }
            pos++;
            Long removedValue = data.get(pos - preambleLength - 1);
            if (removeFromOccurrencies(removedValue)) {
                removeFromSums(removedValue);
            }
            Long addedValue = data.get(pos - 1);
            if (addToOccurrencies(addedValue)) {
                addToSums(addedValue);
            }
        }
        return -1;
    }

    public long solvePart2(long targetNumber) {
        for (int pos=0; pos<data.size()-1; pos++) {
            long value = data.get(pos);
            long min = value;
            long max = value;
            long sum = value;
            for (int check=pos+1; check<data.size(); check++) {
                long newValue = data.get(check);
                if (newValue < min) {
                    min = newValue;
                }
                if (newValue > max) {
                    max = newValue;
                }
                sum += newValue;
                if (sum == targetNumber) {
                    return min + max;
                }
            }
        }
        return -1;
    }

    void addToSums(Long value) {
        Map<Long, Long> newSums = new HashMap<>();
        windowAndOccurrences.keySet().stream().filter(v -> !v.equals(value)).forEach(e -> {
            newSums.put(e, value+e);
            sums.get(e).put(value, value + e);
        });
        sums.put(value, newSums);
    }

    void removeFromSums(Long value) {
        sums.values().forEach(m -> m.remove(value));
        sums.remove(value);
    }

    boolean addToOccurrencies(Long value) {
        if (windowAndOccurrences.containsKey(value)) {
            windowAndOccurrences.put(value, windowAndOccurrences.get(value)+1);
            return false;
        } else {
            windowAndOccurrences.put(value, 1);
            return true;
        }
    }

    boolean removeFromOccurrencies(Long value) {
        if (windowAndOccurrences.get(value).equals(1)) {
            windowAndOccurrences.remove(value);
            return true;
        } else {
            windowAndOccurrences.put(value, windowAndOccurrences.get(value)-1);
            return false;
        }
    }

    public static void main(String[] args) {
        Day9 day9 = new Day9(FileReaderUtils.readLongListFromFile("resources/day9_input.txt"));
        long part1Answer = day9.solvePart1(25);
        System.out.format("Part 1 answer: %d\n", part1Answer);
        long part2Answer = day9.solvePart2(part1Answer);
        System.out.format("Part 2 answer: %d\n", part2Answer);
    }

}
