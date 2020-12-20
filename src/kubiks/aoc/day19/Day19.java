package kubiks.aoc.day19;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.List;

public class Day19 {
    MessageDecoder decoder;

    public Day19(List<String> data) {
        decoder = new MessageDecoder(data);
        //decoder.reduceRules();
    }

    public int countStringsMatchingRule(List<String> data, String ruleId) {
        boolean rulesStarted = false;
        int sum = 0;
        for (String s: data) {
            if (s.length() == 0) {
                rulesStarted = true;
                continue;
            }
            if (!rulesStarted) {
                continue;
            }
            if (decoder.stringMatchesRule(s, ruleId)) {
                sum++;
            }
        }
        return sum;
    }

    public void preparePart2() {
        decoder.putRule("8: 42 | 42 8");
        decoder.putRule("11: 42 31 | 42 11 31");
        decoder.reduceRules();
    }

    public static void main(String[] args) {
        List<String> data = FileReaderUtils.readStringListFromFile("resources/day19_input.txt");
        Day19 day19 = new Day19(data);
        int answer = day19.countStringsMatchingRule(data, "0");
        System.out.format("Number of messages matching rule 0: %d\n", answer);
        day19.preparePart2();
        int answer2 = day19.countStringsMatchingRule(data, "0");
        System.out.format("Part2: Number of messages matching rule 0: %d\n", answer2);
    }
}
