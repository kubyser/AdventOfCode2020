package kubiks.aoc.day18;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.List;

public class Day18 {
    public long calculateSum(List<String> data, boolean advancedMode) {
        long sum = 0;
        FlatCalculator calculator = new FlatCalculator(advancedMode);
        for (String s: data) {
            long value =calculator.evaluateExpression(s);
            sum += value;
        }
        return sum;
    }

    public static void main(String[] args) {
        Day18 day18 = new Day18();
        long result = day18.calculateSum(FileReaderUtils.readStringListFromFile("resources/day18_input.txt"), false);
        System.out.format("Sum of all expressions: %d\n", result);
        long resultPart2 = day18.calculateSum(FileReaderUtils.readStringListFromFile("resources/day18_input.txt"), true);
        System.out.format("Sum of all expressions in advanced mode: %d\n", resultPart2);
    }
}
