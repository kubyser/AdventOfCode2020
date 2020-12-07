package kubiks.aoc.day7;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BagsRelation {
    String color;
    Map<String, Integer> parents;
    Map<String, Integer> children;

    public String getColor() {
        return color;
    }

    public Map<String, Integer> getParents() {
        return parents;
    }

    public Map<String, Integer> getChildren() {
        return children;
    }


    public BagsRelation(String color) {
        this.color = color;
        parents = new HashMap<>();
        children = new HashMap<>();
    }

    public void addParent(String color, int count) {
        parents.put(color, count);
    }

    public void addChild(String color, int count) {
        children.put(color, count);
    }

    public int getSumOfChildren() {
        return children.values().stream().collect(Collectors.summingInt(Integer::intValue));
    }



}
