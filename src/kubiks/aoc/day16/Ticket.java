package kubiks.aoc.day16;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    List<Integer> values;

    public Ticket(String s) {
        values = new ArrayList<>();
        for (String v: s.split(",")) {
            values.add(Integer.valueOf(v));
        }
    }

    public List<Integer> getValues() {
        return values;
    }
}
