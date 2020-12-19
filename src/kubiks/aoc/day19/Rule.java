package kubiks.aoc.day19;

import java.util.*;

public class Rule {
    String id;
    MessageDecoder.RuleType type;
    char symbol;
    List<String> childRules;
    List<String> alternativeChildRules;

    Map<String, Rule> ruleSet;

    Rule(String s, Map<String, Rule> ruleSet) {
        this.ruleSet = ruleSet;
        String[] split = s.split(": ");
        this.id = split[0];
        if (split[1].charAt(0) == '"') {
            type = MessageDecoder.RuleType.FINAL;
            symbol = split[1].charAt(1);
        } else if (split[1].indexOf(" | ") != -1) {
            type = MessageDecoder.RuleType.DOUBLE;
            String[] cRules = split[1].split(" \\| ");
            childRules = new ArrayList<String>(Arrays.asList(cRules[0].split(" ")));
            alternativeChildRules = new ArrayList<String>(Arrays.asList(cRules[1].split(" ")));
        } else {
            type = MessageDecoder.RuleType.SINGLE;
            childRules = new ArrayList<String>(Arrays.asList(split[1].split(" ")));
        }
        ruleSet.put(id, this);
    }

    MatchResult matchList(String s, List<String> rules) {
        String tail = s;
        for (String ruleId : rules) {
            Rule rule = ruleSet.get(ruleId);
            MatchResult ruleResult = rule.matches(tail);
            if (!ruleResult.result) {
                return new MatchResult(false, s);
            }
            tail = ruleResult.tail;
        }
        return new MatchResult(true, tail);
    }

    MatchResult matches(String s) {
        if (s.length() == 0) {
            return new MatchResult(false, s);
        }
        boolean result;
        switch (type) {
            case FINAL: result = s.charAt(0) == symbol;
                return new MatchResult(result, result ? s.substring(1) : s);
            case SINGLE:
                return matchList(s, childRules);
            case DOUBLE:
                MatchResult firstResult = matchList(s, childRules);
                return firstResult.result ? firstResult : matchList(s, alternativeChildRules);
        }
        System.out.println("ERROR: Null rule type");
        return null;
    }

    public boolean matchesString(String s) {
        MatchResult result = matches(s);
        if (!result.result || result.tail.length() > 0) {
            return false;
        }
        return true;
    }


}
