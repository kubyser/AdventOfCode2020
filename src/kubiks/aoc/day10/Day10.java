package kubiks.aoc.day10;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day10 {
    List<Integer> data;
    int maxJoltage;
    Map<Integer, Long> cache;

    public Day10(List<Integer> data) {
        this.data = data;
        Collections.sort(this.data);
        maxJoltage = this.data.get(this.data.size()-1) + 3;
    }

    public LinkedList<Integer> solveFullChain() {
        LinkedList<Integer> available = new LinkedList<>(data);
        available.add(maxJoltage);
        LinkedList<Integer> zeroList = new LinkedList<Integer>();
        zeroList.add(0);
        LinkedList<Integer> solution = findFullChain(zeroList, available);
        if (solution == null) {
            return null;
        }
        solution.removeFirst();
        return solution;
    }

    public int solvePart1() {
        LinkedList<Integer> solution = solveFullChain();
        if (solution == null) {
            return -1;
        }
        int countDifference1 = countNumberOfJoltDifferences(solution, 1);
        int countDifference3 = countNumberOfJoltDifferences(solution, 3);
        return countDifference1 * countDifference3;
    }

    public long solvePart2() {
        cache = new HashMap<>();
        LinkedList<Integer> available = new LinkedList<>(data);
        available.add(maxJoltage);
        LinkedList<Integer> zeroList = new LinkedList<Integer>();
        zeroList.add(0);
        long solution = findAnyChain(zeroList, available);
        return solution;
    }


    LinkedList<Integer> findFullChain(LinkedList<Integer> currentChain, LinkedList<Integer> available) {
        if (available.isEmpty()) {
            return currentChain;
        }
        for (Integer next : available) {
            if (!canBeFitted(next, currentChain.getLast())) {
                return null;
            }
            LinkedList<Integer> newAvailable = new LinkedList<>(available);
            newAvailable.remove(next);
            LinkedList<Integer> newChain = new LinkedList<>(currentChain);
            newChain.add(next);
            LinkedList<Integer> result = findFullChain(newChain, newAvailable);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    long findAnyChain(LinkedList<Integer> currentChain, LinkedList<Integer> available) {
        if (available.isEmpty()) {
            return 1;
        }
        long countResults = 0;
        LinkedList<Integer> newAvailable = new LinkedList<>(available);
        LinkedList<Integer> newChain = new LinkedList<>(currentChain);
        while (!newAvailable.isEmpty()) {
            Integer next = newAvailable.removeFirst();
            if (!canBeFitted(next, currentChain.getLast())) {
                return countResults;
            }
            if (cache.containsKey(next.intValue())) {
                long cacheResult = cache.get(next.intValue());
//                System.out.format("=== Cache hit: [%d]=%d\n", next.intValue(),cacheResult);
                countResults += cacheResult;
            } else {
                newChain.add(next);
                long result = findAnyChain(newChain, newAvailable);
                newChain.removeLast();
                cache.put(next.intValue(), result);
//                System.out.print("CACHE UPDATED: "); System.out.format("[%d]=%d\n",next.intValue(), result);
                countResults += result;
            }
        }
        return countResults;
    }


    boolean canBeFitted(int high, int low) {
        return low >= high-3 && low <= high-1;
    }

    int countNumberOfJoltDifferences(LinkedList<Integer> chain, int difference) {
        int previous = 0;
        int count = 0;
        for (Integer adapter : chain) {
            if (adapter - previous == difference) {
                count++;
            }
            previous = adapter;
        }
        return count;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10(FileReaderUtils.readIntegerListFromFile("resources/day10_input.txt"));
        int answer = day10.solvePart1();
        System.out.format("1-diff * 3-diff: %d\n", answer);
        long answerPart2 = day10.solvePart2();
        System.out.format("Number of different ways: %d\n", answerPart2);
    }

}
