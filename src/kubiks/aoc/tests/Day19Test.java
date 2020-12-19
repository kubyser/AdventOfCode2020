package kubiks.aoc.tests;

import kubiks.aoc.day19.Day19;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day19Test {

    @Test
    void countStringsMatchingRule() {
        List<String> data = FileReaderUtils.readStringListFromFile("resources/day19_test_input.txt");
        Day19 day19 = new Day19(data);
        assertEquals(2, day19.countStringsMatchingRule(data, "0"));
    }
}