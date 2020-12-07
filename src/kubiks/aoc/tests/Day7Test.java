package kubiks.aoc.tests;

import kubiks.aoc.day7.BagRules;
import kubiks.aoc.day7.Day7;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {

    @Test
    void countAllAncestors() {
        Day7 day7 = new Day7();
        day7.addRule("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        day7.addRule("bright white bags contain 1 shiny gold bag.");
        day7.addRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        day7.addRule("muted yellow bags contain no other bags.");
        assertEquals(3, day7.countAllAncestors("shiny gold"));
        assertEquals(2, day7.countAllAncestors("muted yellow"));
    }

    @Test
    void countAllAncestorsReadFromFile() {
        Day7 day7 = new Day7(FileReaderUtils.readStringListFromFile("resources/day7_test_input.txt"));
        assertEquals(4, day7.countAllAncestors("shiny gold"));
    }

    @Test
    void countAllDescendants() {
        Day7 day7 = new Day7();
        day7.addRule("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        day7.addRule("bright white bags contain 1 shiny gold bag.");
        day7.addRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        day7.addRule("muted yellow bags contain no other bags.");
        day7.addRule("shiny gold bags contain no other bags.");
        assertEquals(4, day7.countAllDescendants("light red"));
        assertEquals(10, day7.countAllDescendants("dark orange"));
        day7 = new Day7();
        day7.addRule("shiny gold bags contain 2 dark red bags.");
        day7.addRule("dark red bags contain 2 dark orange bags.");
        day7.addRule("dark orange bags contain 2 dark yellow bags.");
        day7.addRule("dark yellow bags contain 2 dark green bags.");
        day7.addRule("dark green bags contain 2 dark blue bags.");
        day7.addRule("dark blue bags contain 2 dark violet bags.");
        day7.addRule("dark violet bags contain no other bags.");
        assertEquals(126, day7.countAllDescendants("shiny gold"));
    }

    @Test
    void countAllDescendantsReadFromFile() {
        Day7 day7 = new Day7(FileReaderUtils.readStringListFromFile("resources/day7_test_input.txt"));
        assertEquals(32, day7.countAllDescendants("shiny gold"));
    }
    @Test
    void day7() {
        Day7 day7 = new Day7(FileReaderUtils.readStringListFromFile("resources/day7_input.txt"));
        assertEquals(148, day7.countAllAncestors("shiny gold"));
        assertEquals(24867, day7.countAllDescendants("shiny gold"));
    }


}