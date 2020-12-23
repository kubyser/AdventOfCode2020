package kubiks.aoc.tests;

import kubiks.aoc.day23.CircularSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularSetTest {

    @Test
    void getSize() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        assertEquals(0, circularSet.getSize());
        circularSet.add(123);
        assertEquals(1, circularSet.getSize());
        circularSet.add(256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        assertEquals(4, circularSet.getSize());
        circularSet.remove(256);
        assertEquals(3, circularSet.getSize());
    }

    @Test
    void getNext() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        circularSet.add(123);
        circularSet.addAfter(123, 256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        assertEquals(256, circularSet.getNext(123));
        assertEquals(123, circularSet.getNext(34));
    }

    @Test
    void removeNext() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        circularSet.add(123);
        circularSet.addAfter(123, 256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        assertEquals(42, circularSet.removeNext(256));
        assertNull(circularSet.remove(512));
    }

    @Test
    void remove() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        circularSet.add(123);
        circularSet.addAfter(123, 256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        circularSet.remove(256);
        assertEquals(42, circularSet.getNext(123));
        assertNull(circularSet.remove(512));
    }


    @Test
    void testToString() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        assertEquals("", circularSet.toString());
        circularSet.add(123);
        assertEquals("[123]", circularSet.toString());
        circularSet.addAfter(123, 256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        assertEquals("[123, 256, 42, 34]", circularSet.toString());
        circularSet.remove(256);
        assertEquals("[123, 42, 34]", circularSet.toString());
        circularSet.removeNext(34);
        assertEquals("[42, 34]", circularSet.toString());
    }

    @Test
    void contains() {
        CircularSet<Integer> circularSet = new CircularSet<>();
        assertFalse(circularSet.contains(123));
        circularSet.add(123);
        assertTrue(circularSet.contains(123));
        circularSet.addAfter(123, 256);
        circularSet.addAfter(256, 42);
        circularSet.addAfter(42, 34);
        assertTrue(circularSet.contains(42));
        assertTrue(circularSet.contains(256));
        circularSet.remove(256);
        assertFalse(circularSet.contains(256));
        circularSet.removeNext(34);
        assertTrue(circularSet.contains(34));
        assertFalse(circularSet.contains(123));
    }
}