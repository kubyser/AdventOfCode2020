package kubiks.aoc.day23;

import kubiks.aoc.day4.Passport;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Day23 {
    CircularSet<Integer> cups;
    int current;
    int min, max;

    public Day23(String data) {
        this(data, false);
    }


    public Day23(String data, boolean million) {
        cups = new CircularSet<>();
        int lastCup = -1;
        for (char c: data.toCharArray()) {
            int value = Integer.parseInt(String.valueOf(c));
            if (cups.getSize() == 0) {
                cups.add(value);
                current = value;
                min = value;
                max = value;
            } else {
                cups.addAfter(lastCup, value);
                if (value < min) {
                    min = value;
                }
                if (value > max) {
                    max = value;
                }
            }
            lastCup = value;
        }
        if (million) {
            while (max < 1000000) {
                max ++;
                cups.addAfter(lastCup, max);
                lastCup = max;
            }
        }
    }

    LinkedList<Integer> getThreeNextCups() {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<3; i++) {
            list.add(cups.removeNext(current));
        }
        return list;
    }

    public void playMove() {
        LinkedList<Integer> removedCups =  getThreeNextCups();
        int destination = current;
        do {
            destination--;
            if (destination < min) {
                destination = max;
            }
        }  while (!cups.contains(destination));
        cups.addAfter(destination, removedCups);
        current = cups.getNext(current);
    }

    public CircularSet<Integer> getCups() {
        return cups;
    }

    public int getCurrent() {
        return current;
    }

    public void playNMoves(int n) {
        for (int i=0; i<n; i++) {
            playMove();
        }
    }

    public String getOrderAfterNMoves(int n) {
        playNMoves(n);
        return getCupsAsString();
    }

    public String getCupsAsString() {
        int next = cups.getNext(1);
        String s = "";
        while (next != 1) {
            s += next;
            next = cups.getNext(next);
        }
        return s;
    }

    public long getProductAfterNMoves(int n) {
        playNMoves(n);
        int first = cups.getNext(1);
        long second = cups.getNext(first);
        return second * first;
    }

    public static void main(String[] args) {
        Day23 day23 = new Day23("583976241");
        String answer = day23.getOrderAfterNMoves(100);
        System.out.format("Order of cups after 100 moves: %s\n", answer);
        day23 = new Day23("583976241", true);
        long answerPart2 = day23.getProductAfterNMoves(10000000);
        System.out.format("Product of first two cups after 10000000 moves: %d\n", answerPart2);
    }

}
