package kubiks.aoc.day1;

import kubiks.aoc.utils.FileReaderUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {

    List<Integer> data;

    public Day1(String fileName) throws FileNotFoundException {
        data = FileReaderUtils.readIntegerListFromFile(fileName);
        System.out.println("Input: " + data.toString());
    }

    public Day1(List<Integer> data) {
        this.data = data;
    }

    public int solve(int target) throws Exception {
        return solve(target, false);
    }

    public int solve(int target, boolean triple) throws Exception {
        List<Integer> sortedData = new ArrayList<Integer>(data);
        sortedData.sort(Integer::compareTo);
        System.out.println("Sorted input: " + sortedData.toString());
        ListIterator i = sortedData.listIterator();
        int step = 0;
        while (i.hasNext()) {
            int a = (int)i.next();
            if (a > target) {
                throw new Exception("First element more that target");
            }
            ListIterator j = sortedData.listIterator(i.nextIndex());
            while (j.hasNext()) {
                int b = (int)j.next();
                int sum = a + b;
                if (!triple) {
                    step++;
                    System.out.format("%d: a=%d, b=%d, sum=%d\n", step, a, b, sum);
                    if (sum == target) {
                        return a * b;
                    }
                }
                if (sum > target) {
                    break;
                }
                if (triple) {
                    ListIterator z = sortedData.listIterator(j.nextIndex());
                    while (z.hasNext()) {
                        int c = (int) j.next();
                        int sumTriple = sum + c;
                        step++;
                        System.out.format("%d: a=%d, b=%d, c=%d, sum=%d\n", step, a, b, c, sumTriple);
                        if (sumTriple == target) {
                            return a * b * c;
                        }
                        if (sumTriple > target) {
                            break;
                        }
                    }
                }
            }
        }
        throw new Exception("Target sum not found");
    }

    public static void main(String[] args) throws Exception {
        Day1 day1 = new Day1("resources/day1_input.txt");
        // int answer = day1.solve(2020);
        int answer = day1.solve(2020, true);
        System.out.format("Answer: %d", answer);
    }
}
