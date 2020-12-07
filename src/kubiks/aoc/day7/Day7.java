package kubiks.aoc.day7;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    BagRules rules = new BagRules();

    public Day7() {}


    public Day7(List<String> data) {
        for (String ruleString : data) {
            rules.addRule(ruleString);
        }
    }

    public void addRule(String ruleString) {
        rules.addRule(ruleString);
    }

    public int countAllAncestors(String color) {
        Set<String> ancestors = new HashSet<>();
        ancestors.addAll(rules.getParents(color).keySet());
        int previousSize = 0;
        while (previousSize != ancestors.size()) {
            previousSize = ancestors.size();
            Set<String> newParents = new HashSet<>();
            for (String parentColor : ancestors) {
                newParents.addAll(rules.getParents(parentColor).keySet());
            }
            ancestors.addAll(newParents);
        }
        return ancestors.size();
    }

    public int countAllDescendants(String color) {
        if (rules.getChildren(color).size() == 0) {
            return 0;
        }
        List<Integer> counts = new ArrayList<>();
        rules.getBagRelation(color).getChildren().forEach((childColor, childCount) -> counts.add(countAllDescendants(childColor) * childCount + childCount));
        int totalDescendants = counts.stream().collect(Collectors.summingInt(Integer::intValue));
        return totalDescendants;
    }

    public static void main(String[] args) {
        Day7 day7 = new Day7(FileReaderUtils.readStringListFromFile("resources/day7_input.txt"));
        int answer = day7.countAllAncestors("shiny gold");
        System.out.format("Ancestors: %d\n", answer);
        int answerPart2 = day7.countAllDescendants("shiny gold");
        System.out.format("Descendants: %d\n", answerPart2);
    }
}
