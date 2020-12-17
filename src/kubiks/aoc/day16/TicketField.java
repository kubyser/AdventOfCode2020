package kubiks.aoc.day16;

public class TicketField {
    String name;
    int min1, max1, min2, max2;

    public TicketField(String name, int min1, int max1, int min2, int max2) {
        this.name = name;
        this.min1 = min1;
        this.max1 = max1;
        this.min2 = min2;
        this.max2 = max2;
    }

    public TicketField(String data) {
        String[] split = data.split(": ");
        this.name = split[0];
        split = split[1].split(" or ");
        this.min1 = Integer.parseInt(split[0].split("-")[0]);
        this.max1 = Integer.parseInt(split[0].split("-")[1]);
        this.min2 = Integer.parseInt(split[1].split("-")[0]);
        this.max2 = Integer.parseInt(split[1].split("-")[1]);
    }

    public String getName() {
        return name;
    }

    public boolean valueValidForField(int value) {
        return value >= min1 && value <= max1 || value >= min2 && value <= max2;
    }

    public String toString() {
        return name;
    }
}
