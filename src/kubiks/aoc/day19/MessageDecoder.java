package kubiks.aoc.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageDecoder {
    enum RuleType {
        FINAL,
        FINAL_LIST,
        SINGLE,
        DOUBLE,
        DOUBLE_LOOP
    }

    Map<String, Rule> ruleSet;

    public MessageDecoder(List<String> rules) {
        ruleSet = new HashMap<>();
        for (String s: rules) {
            if (s.length() == 0) {
                break;
            }
            new Rule(s, ruleSet);
        }
    }

    public void putRule(String rule) {
        new Rule(rule, ruleSet);
    }

    public boolean stringMatchesRule(String s, String ruleId) {
        if (!ruleSet.containsKey(ruleId)) {
            System.out.format("Unknown rule ID %s\n", ruleId);
        }
        return ruleSet.get(ruleId).matchesString(s);
    }

    public void reduceRules() {
        for (String ruleId: ruleSet.keySet()) {
            ruleSet.get(ruleId).reduce();
            //System.out.println(ruleSet.get(ruleId).toString());
        }
    }

}
