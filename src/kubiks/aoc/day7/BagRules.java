package kubiks.aoc.day7;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagRules {
    Map<String, BagsRelation> rules;

    public BagRules() {
        rules = new HashMap<>();
    }

    public void addRule(String ruleString) {
        //light red bags contain 1 bright white bag, 2 muted yellow bags.
        String[] leftRight = ruleString.split(" contain ");
        String color = leftRight[0].substring(0, leftRight[0].indexOf(" bags"));
        String right = leftRight[1].replace("bags", "bag").
                replace(" bag, ", ",").
                replace(" bag.", "").
                replace("no other", "");
        List<String> contents = right.length() > 0 ? Arrays.asList(right.split(",")) : null;

        BagsRelation rule;
        if (rules.containsKey(color)) {
            rule = rules.get(color);
        } else {
            rule = new BagsRelation(color);
            rules.put(color, rule);
        }
        if (contents != null) {
            for (String child : contents) {
                int count = Integer.valueOf(child.substring(0, child.indexOf(' ')));
                String childColor = child.substring(child.indexOf(' ') + 1);
                rule.addChild(childColor, count);
                BagsRelation childRule;
                if (rules.containsKey(childColor)) {
                    childRule = rules.get(childColor);
                } else {
                    childRule = new BagsRelation(childColor);
                    rules.put(childColor, childRule);
                }
                childRule.addParent(color, count);
            }
        }
    }

    public Map<String, Integer> getParents(String color) {
        if (rules.containsKey(color)) {
            return rules.get(color).getParents();
        }
        return null;
    }

    public Map<String, Integer> getChildren(String color) {
        if (rules.containsKey(color)) {
            return rules.get(color).getChildren();
        }
        return null;
    }

    public BagsRelation getBagRelation(String color) {
        if (rules.containsKey(color)) {
            return rules.get(color);
        }
        return null;
    }

}
