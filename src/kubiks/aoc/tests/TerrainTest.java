package kubiks.aoc.tests;

import kubiks.aoc.day3.Terrain;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TerrainTest {

    @Test
    void getHeight() throws FileNotFoundException {
        ArrayList<String> map = new ArrayList<>();
        map.add("..##..");
        map.add("##..##");
        map.add(".#..#.");
        Terrain terrain = new Terrain(map);
        assertEquals(3, terrain.getHeight());
        terrain = new Terrain("resources/day3_test_input.txt");
        assertEquals(11, terrain.getHeight());
    }

    @Test
    void getWidth() throws FileNotFoundException {
        ArrayList<String> map = new ArrayList<>();
        map.add("..##..");
        map.add("##..##");
        map.add(".#..#.");
        Terrain terrain = new Terrain(map);
        assertEquals(6, terrain.getWidth());
        terrain = new Terrain("resources/day3_test_input.txt");
        assertEquals(11, terrain.getHeight());
    }

    @Test
    void isTreeAtpos() throws Exception {
        Terrain terrain = new Terrain("resources/day3_test_input.txt");
        assertTrue(terrain.isTreeAtpos(0, 1));
        assertTrue(terrain.isTreeAtpos(10, 6));
        assertTrue(terrain.isTreeAtpos(10, 10));
        assertFalse(terrain.isTreeAtpos(0, 0));
        assertFalse(terrain.isTreeAtpos(10, 0));
        assertFalse(terrain.isTreeAtpos(3, 3));
        assertFalse(terrain.isTreeAtpos(9, 10));
    }
}