package kubiks.aoc.tests;

import kubiks.aoc.day22.Day22;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {
    List<String> getTestData() {
        List<String> data = new ArrayList<>();
        data.add("Player 1:");
        data.add("9");
        data.add("2");
        data.add("6");
        data.add("3");
        data.add("1");
        data.add("");
        data.add("Player 2:");
        data.add("5");
        data.add("8");
        data.add("4");
        data.add("7");
        data.add("10");
        return data;
    }

    @Test
    void loadData() {
        Day22 day22 = new Day22(getTestData());
        assertEquals(2, day22.getStartingDecks().size());
        assertEquals("[9, 2, 6, 3, 1]", day22.getStartingDecks().get(0).toString());
        assertEquals("[5, 8, 4, 7, 10]", day22.getStartingDecks().get(1).toString());
    }

    @Test
    void playRound() {
        Day22 day22 = new Day22(getTestData());
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), false));
        assertEquals("[2, 6, 3, 1, 9, 5]", day22.getStartingDecks().get(0).toString());
        assertEquals("[8, 4, 7, 10]", day22.getStartingDecks().get(1).toString());

    }

    @Test
    void playGame() {
        Day22 day22 = new Day22(getTestData());
        assertEquals(306, day22.playFullGame(false));
    }

    @Test
    void day22Part1() {
        Day22 day22 = new Day22(FileReaderUtils.readStringListFromFile("resources/day22_input.txt"));
        assertEquals(32033, day22.playFullGame(false));
    }

    @Test
    void endlessLoopCheck() {
        List<String> data = new ArrayList<>();
        data.add("Player 1:");
        data.add("43");
        data.add("19");
        data.add("");
        data.add("Player 2:");
        data.add("2");
        data.add("29");
        data.add("14");
        Day22 day22 = new Day22(data);
        assertEquals(105, day22.playFullGame(true));
    }

    @Test
    void playRecursiveRound() {
        Day22 day22 = new Day22(getTestData());
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals(-1, day22.playRound(day22.getStartingDecks(), true));
        assertEquals("[9, 8, 5, 2]", day22.getStartingDecks().get(0).toString());
        assertEquals("[10, 1, 7, 6, 3, 4]", day22.getStartingDecks().get(1).toString());
    }

    @Test
    void playRecursiveGame() {
        Day22 day22 = new Day22(getTestData());
        assertEquals(291, day22.playFullGame(true));
        assertTrue(day22.getStartingDecks().get(0).isEmpty());
        assertEquals("[7, 5, 6, 2, 4, 1, 10, 8, 9, 3]", day22.getStartingDecks().get(1).toString());
    }

    @Test
    void day22Part2() {
        Day22 day22 = new Day22(FileReaderUtils.readStringListFromFile("resources/day22_input.txt"));
        assertEquals(34901, day22.playFullGame(true));
    }

}