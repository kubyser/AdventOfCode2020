package kubiks.aoc.tests;

import kubiks.aoc.day16.Day16;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {
    List<String> initData() {
        List<String> data = new ArrayList<>();
        data.add("class: 1-3 or 5-7");
        data.add("row number: 6-11 or 33-44");
        data.add("class seat: 13-40 or 45-50");
        data.add("");
        data.add("your ticket:");
        data.add("7,1,14");
        data.add("");
        data.add("nearby tickets:");
        data.add("7,3,47");
        data.add("40,4,50");
        data.add("55,2,20");
        data.add("38,6,12");
        return data;
    }

    List<String> initDataPart2() {
        List<String> data = new ArrayList<>();
        data.add("class: 0-1 or 4-19");
        data.add("row: 0-5 or 8-19");
        data.add("class seat: 0-13 or 16-19");
        data.add("");
        data.add("your ticket:");
        data.add("11,12,13");
        data.add("");
        data.add("nearby tickets:");
        data.add("3,9,18");
        data.add("15,1,5");
        data.add("5,14,9");
        return data;
    }


    @Test
    void getFields() {
        Day16 day16 = new Day16(initData());
        assertEquals(3, day16.getFields().size());
        assertEquals("class", day16.getFields().get(0).getName());
        assertEquals("row number", day16.getFields().get(1).getName());
        assertEquals("class seat", day16.getFields().get(2).getName());
        assertTrue(day16.getFields().get(0).valueValidForField(5));
        assertTrue(day16.getFields().get(0).valueValidForField(2));
        assertTrue(day16.getFields().get(1).valueValidForField(11));
        assertTrue(day16.getFields().get(1).valueValidForField(33));
        assertTrue(day16.getFields().get(2).valueValidForField(39));
        assertTrue(day16.getFields().get(2).valueValidForField(40));
        assertTrue(day16.getFields().get(2).valueValidForField(49));
        assertFalse(day16.getFields().get(0).valueValidForField(4));
        assertFalse(day16.getFields().get(0).valueValidForField(10));
        assertFalse(day16.getFields().get(1).valueValidForField(1));
        assertFalse(day16.getFields().get(1).valueValidForField(12));
        assertFalse(day16.getFields().get(1).valueValidForField(32));
        assertFalse(day16.getFields().get(2).valueValidForField(12));
        assertFalse(day16.getFields().get(2).valueValidForField(41));
    }

    @Test
    void getOwnTicket() {
        Day16 day16 = new Day16(initData());
        assertEquals(3, day16.getOwnTicket().getValues().size());
        assertEquals(7, day16.getOwnTicket().getValues().get(0));
        assertEquals(1, day16.getOwnTicket().getValues().get(1));
        assertEquals(14, day16.getOwnTicket().getValues().get(2));
    }

    @Test
    void getTickets() {
        Day16 day16 = new Day16(initData());
        assertEquals(4, day16.getTickets().size());
        assertEquals(3, day16.getTickets().get(0).getValues().size());
        assertEquals(3, day16.getTickets().get(1).getValues().size());
        assertEquals(3, day16.getTickets().get(2).getValues().size());
        assertEquals(3, day16.getTickets().get(3).getValues().size());
        assertEquals(40, day16.getTickets().get(1).getValues().get(0));
        assertEquals(4, day16.getTickets().get(1).getValues().get(1));
        assertEquals(50, day16.getTickets().get(1).getValues().get(2));
        assertEquals(38, day16.getTickets().get(3).getValues().get(0));
        assertEquals(6, day16.getTickets().get(3).getValues().get(1));
        assertEquals(12, day16.getTickets().get(3).getValues().get(2));
    }

    @Test
    void solvePart1() {
        Day16 day16 = new Day16(initData());
        assertEquals(71, day16.solvePart1());
    }

    @Test
    void solvePart2() {
        Day16 day16 = new Day16(initDataPart2());
        assertEquals(12*13, day16.solvePart2("class"));
    }

    @Test
    void day16Part1() {
        Day16 day16 = new Day16(FileReaderUtils.readStringListFromFile("resources/day16_input.txt"));
        assertEquals(32835, day16.solvePart1());
    }

    @Test
    void day16Part2() {
        Day16 day16 = new Day16(FileReaderUtils.readStringListFromFile("resources/day16_input.txt"));
        assertEquals(514662805187L, day16.solvePart2("departure"));
    }

}