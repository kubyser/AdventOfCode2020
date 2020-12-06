package kubiks.aoc.day6;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6Marina {

    public static void main(String[] args) throws IOException {
        List<String> forms = Arrays.asList(new Scanner(new File("resources/day6_input.txt")).useDelimiter("\\Z").next().split("\n\n"));
        List<List<String>> forms2 = forms.stream().map(g -> Arrays.asList(g.split("\n"))).collect(Collectors.toList());
        List<List<HashSet<Character>>> sets = forms2.stream().map(g ->  g.stream().map(
                        p -> new HashSet<Character>(p.chars().mapToObj(e -> (char)e).collect(Collectors.toList()))
                ).collect(Collectors.toList())).collect(Collectors.toList());
        sets.stream().forEach(g -> g.stream().forEach(s -> g.get(0).retainAll(s)));
        List<Integer> lengths = sets.stream().map(g -> g.get(0).size()).collect(Collectors.toList());
        System.out.println(lengths.stream().collect(Collectors.summingInt(Integer::intValue)));
    }
}
