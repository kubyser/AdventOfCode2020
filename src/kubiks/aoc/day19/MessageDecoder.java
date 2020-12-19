package kubiks.aoc.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDecoder {
    enum RuleType {
        FINAL,
        SINGLE,
        DOUBLE
    }

    Map<String, Rule> ruleSet;

    public MessageDecoder(List<String> rules) {
        ruleSet = new HashMap<>();
        for (String s: rules) {
            if (s.length() == 0) {
                break;
            }
            Rule rule = new Rule(s, ruleSet);
        }
    }

    public boolean stringMatchesRule(String s, String ruleId) {
        if (!ruleSet.containsKey(ruleId)) {
            System.out.format("Unknown rule ID %s\n", ruleId);
        }
        return ruleSet.get(ruleId).matchesString(s);
    }

}
