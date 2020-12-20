package kubiks.aoc.day19;

import java.util.*;
import java.util.stream.Collectors;

public class Rule {

    String id;
    MessageDecoder.RuleType type;
    char symbol;
    Set<String> possibleMatches;
    List<String> childRules;
    List<String> alternativeChildRules;

    Map<String, Rule> ruleSet;
    boolean reduced = false;

    int maxLength = 1;

    Rule(String s, Map<String, Rule> ruleSet) {
        this.ruleSet = ruleSet;
        String[] split = s.split(": ");
        this.id = split[0];
        if (split[1].charAt(0) == '"') {
            type = MessageDecoder.RuleType.FINAL;
            symbol = split[1].charAt(1);
        } else if (split[1].indexOf(" | ") != -1) {
            String[] cRules = split[1].split(" \\| ");
            childRules = new ArrayList<String>(Arrays.asList(cRules[0].split(" ")));
            if (childRules.contains(this.id)) {
                System.out.format("ERROR: loop in 1st optional clause. Must be in 2nd. Rule string: %s\n", s);
            }
            alternativeChildRules = new ArrayList<String>(Arrays.asList(cRules[1].split(" ")));
            type = alternativeChildRules.contains(this.id) ? MessageDecoder.RuleType.DOUBLE_LOOP : MessageDecoder.RuleType.DOUBLE;
            //type = MessageDecoder.RuleType.DOUBLE_LOOP;
        } else {
            type = MessageDecoder.RuleType.SINGLE;
            childRules = new ArrayList<String>(Arrays.asList(split[1].split(" ")));
        }
        ruleSet.put(id, this);
    }

    Set<String> matchList(String s, List<String> rules, int depth) {
        Set<String> tails = buildSetTails(s);
        for (String ruleId : rules) {
            if (this.id.equals(ruleId)) {
                if (depth-maxLength < 0) {
                    continue;
                }
            }
            Set<String> newTails = new HashSet<>();
            Rule rule = ruleSet.get(ruleId);
            for (String curTail : tails) {
                Set<String> ruleResult = rule.matches(curTail, this.id.equals(ruleId) ? depth-maxLength : depth);
                if (ruleResult == null) {
                    continue;
                }
                newTails.addAll(ruleResult);
            }
            tails = newTails;
        }
        if (tails.isEmpty()) {
            return null;
        }
        return tails;
    }

    Set<String> buildSetTails(String tail) {
        Set<String> list = new HashSet<>();
        list.add(tail);
        return list;
    }

    Set<String> matches(String s, int depth) {
        if (s.length() == 0) {
            return null;
        }
        boolean result;
        Set<String> firstResult;
        Set<String> secondResult;
        switch (type) {
            case FINAL: result = s.charAt(0) == symbol;
                return result ? buildSetTails(s.substring(1)) : null;
            case FINAL_LIST:
                for (String possibility:possibleMatches) {
                    if (s.indexOf(possibility) == 0) {
                        return buildSetTails(s.substring(possibility.length()));
                    }
                }
                return null;
            case SINGLE:
                return matchList(s, childRules, depth);
            case DOUBLE:
                firstResult = matchList(s, childRules, depth);
                if (firstResult != null) {
                    return firstResult;
                }
                secondResult = matchList(s, alternativeChildRules, depth);
                return secondResult;
            case DOUBLE_LOOP:
                firstResult = matchList(s, childRules, depth);
                secondResult = matchList(s, alternativeChildRules, depth);
                if (firstResult == null) {
                    return secondResult == null? null : secondResult;
                }
                if (secondResult != null) {
                    firstResult.addAll(secondResult);
                }
                return firstResult;
        }
        System.out.println("ERROR: Null rule type");
        return null;
    }

    public boolean matchesString(String s) {
        Set<String> result = matches(s, s.length());
        if (result == null) {
            return false;
        }
        if (result.stream().filter(e -> e.length() == 0).count() > 0) {
            return true;
        }
        return false;
    }

    Set<String> reduceList(List<String> list) {
        Set<String> values = new HashSet<>();
        for (String child: list) {
            Set<String> result = ruleSet.get(child).reduceRule();
            if (result == null) {
                return null;
            }
            Set<String> newValues = new HashSet<>();
            if (values.isEmpty()) {
                for (String r: result) {
                    newValues.add(r);
                }
            } else {
                for (String value : values) {
                    for (String r : result) {
                        newValues.add(value + r);
                    }
                }
            }
            values = newValues;
        }
        return values;
    }

    Set<String> reduceRule() {
        Set<String> values;
        switch (this.type) {
            case FINAL:
                values = new HashSet<>();
                values.add(String.valueOf(this.symbol));
                return values;
            case FINAL_LIST:
                return this.possibleMatches;
            case SINGLE:
                if (reduced) {
                    return null;
                }
                reduced = true;
                values = reduceList(childRules);
                if (values != null) {
                    this.type = MessageDecoder.RuleType.FINAL_LIST;
                    possibleMatches = new HashSet<>(values);
                }
                return values;
            case DOUBLE:
                if (reduced) {
                    return null;
                }
                reduced = true;
                values = reduceList(childRules);
                if (values == null) {
                    return null;
                }
                Set<String> altValues = reduceList(alternativeChildRules);
                if (altValues == null) {
                    return null;
                }
                for (String altValue : altValues) {
                    values.add(altValue);
                }
                this.type = MessageDecoder.RuleType.FINAL_LIST;
                possibleMatches = new HashSet<>(values);
                return values;
            case DOUBLE_LOOP:
                values = reduceList(childRules);
                if (values != null) {
                    maxLength = values.iterator().next().length();
                }
                return null;
        }
        return null;
    }

    public void reduce() {
        reduceRule();
    }

    public String toString() {
        String s = "id="+id+", type="+type+", list="+(possibleMatches==null?"null":possibleMatches.toString());
        return s;
    }

}
