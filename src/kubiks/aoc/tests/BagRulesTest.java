package kubiks.aoc.tests;

import kubiks.aoc.day7.BagRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagRulesTest {

    @Test
    void addRule() {
        BagRules rules = new BagRules();
        rules.addRule("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        assertEquals(0, rules.getParents("light red").size());
        assertEquals(2, rules.getChildren("light red").size());
        assertEquals(1, rules.getChildren("light red").get("bright white"));
        assertEquals(2, rules.getChildren("light red").get("muted yellow"));
        assertEquals(1, rules.getParents("bright white").size());
        assertEquals(1, rules.getParents("bright white").get("light red"));
        assertEquals(1, rules.getParents("muted yellow").size());
        assertEquals(2, rules.getParents("muted yellow").get("light red"));

        rules.addRule("bright white bags contain 1 shiny gold bag.");
        assertEquals(0, rules.getParents("light red").size());
        assertEquals(2, rules.getChildren("light red").size());
        assertEquals(1, rules.getChildren("light red").get("bright white"));
        assertEquals(2, rules.getChildren("light red").get("muted yellow"));
        assertEquals(1, rules.getChildren("bright white").size());
        assertEquals(1, rules.getChildren("bright white").get("shiny gold"));
        assertEquals(1, rules.getParents("shiny gold").get("bright white"));

        rules.addRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        assertEquals(2, rules.getParents("bright white").size());
        assertEquals(3, rules.getParents("bright white").get("dark orange"));

        rules.addRule("muted yellow bags contain no other bags.");
        assertEquals(2, rules.getParents("muted yellow").size());
        assertEquals(0, rules.getChildren("muted yellow").size());
    }

    @Test
    void getSumOfChildren() {
        BagRules rules = new BagRules();
        rules.addRule("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        rules.addRule("bright white bags contain 1 shiny gold bag.");
        rules.addRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.");
        rules.addRule("muted yellow bags contain no other bags.");
        assertEquals(3, rules.getBagRelation("light red").getSumOfChildren());
        assertEquals(1, rules.getBagRelation("bright white").getSumOfChildren());
        assertEquals(0, rules.getBagRelation("muted yellow").getSumOfChildren());
    }

}