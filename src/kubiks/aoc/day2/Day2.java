package kubiks.aoc.day2;

import kubiks.aoc.day1.Day1;
import kubiks.aoc.utils.FileReaderUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Day2 {
    HashMap<String, PasswordPolicy> policies = new HashMap<>();
    List<String> data;

    public Day2(List<String> data) {
        this.data = data;
    }

    public int solve() {
        return solve(false);
    }

    public int solve(boolean useNewPolicy) {
        int answer = 0;
        Iterator<String> stringIterator = data.iterator();
        while (stringIterator.hasNext()) {
            String s = stringIterator.next();
            PasswordPolicy policy = getPolicy(s);
            String password = extractPasswordString(s);
            if (useNewPolicy ?  policy.stringMatchesNewPolicy(password) : policy.stringMatchesOldPolicy(password)) {
                answer++;
                System.out.format("OK  Match: %s, total=%d\n", s, answer);
            } else {
                System.out.format("NOT Match: %s, total=%d\n", s, answer);
            }
        }
        return answer;
    }

    PasswordPolicy getPolicy(String inputString) {
        String policyPattern = inputString.replaceFirst("([^:]*):.*", "$1");
        if (policies.containsKey(policyPattern)) {
            return policies.get(policyPattern);
        }
        int minOccurrencies = Integer.parseInt(policyPattern.replaceFirst("([^-]*)-.*", "$1"));
        int maxOccurrencies = Integer.parseInt(policyPattern.replaceFirst("[^-]*-([^ ]*) .*", "$1"));
        char c = policyPattern.replaceFirst("[^ ]* (.*)", "$1").charAt(0);
        PasswordPolicy policy = new PasswordPolicy(minOccurrencies, maxOccurrencies, c);
        policies.put(policyPattern, policy);
        return policy;
    }

    String extractPasswordString(String inputString) {
        return inputString.split(": ")[1];
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day2 day2 = new Day2(FileReaderUtils.readStringListFromFile("resources/day2_input.txt"));
        int answer = day2.solve(true);
        System.out.format("Answer: %d\n", answer);
    }
}
