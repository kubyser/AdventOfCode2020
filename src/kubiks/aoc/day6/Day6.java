package kubiks.aoc.day6;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day6 {
    List<String> data;

    public Day6(List<String> data) {
        this.data =  data;
    }

    public int solve() {
        int sum = 0;
        for (String line : data) {
            HashSet<Character> chars = new HashSet<>();
            for (int i=0; i<line.length(); i++) {
                if (line.charAt(i) >= 'a' && line.charAt(i) <= 'z') {
                    chars.add(new Character(line.charAt(i)));
                }
            }
            sum += chars.size();
        }
        return sum;
    }

    public int solvePart2() {
        int sum = 0;
        for (String line : data) {
            ArrayList<String> persons = new ArrayList<>(Arrays.asList(line.split(" ")));
            HashMap<Character, Integer> positives = new HashMap<>();
            for (String personAnswers : persons) {
                HashSet<Character> processed = new HashSet<>();
                for (int i=0; i<personAnswers.length(); i++) {
                    Character c = Character.valueOf(personAnswers.charAt(i));
                    if (processed.contains(c) || c < 'a' || c > 'z') {
                        break;
                    }
                    processed.add(c);
                    if (positives.containsKey(c)) {
                        positives.replace(c, positives.get(c) + 1);
                    } else {
                        positives.put(c, 1);
                    }
                }
            }
            sum += positives.values().stream().filter(a -> a == persons.size()).count();
        }
        return sum;
    }


    public static void main(String[] args) {
        Day6 day6 = new Day6(FileReaderUtils.readWithDoubleCr("resources/day6_input.txt", true));
        int answer = day6.solve();
        System.out.format("Sum in all groups: %d\n", answer);
        answer = day6.solvePart2();
        System.out.format("sum unanimous (part2) in all groups: %d\n", answer);
    }

}
