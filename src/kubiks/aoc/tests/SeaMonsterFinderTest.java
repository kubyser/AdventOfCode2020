package kubiks.aoc.tests;

import kubiks.aoc.day20.SeaMonsterFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeaMonsterFinderTest {

    List<String> getTestData() {
        List<String> data = new ArrayList<>();
        data.add(".#.#..#.##...#.##..#####");
        data.add("###....#.#....#..#......");
        data.add("##.##.###.#.#..######...");
        data.add("###.#####...#.#####.#..#");
        data.add("##.#....#.##.####...#.##");
        data.add("...########.#....#####.#");
        data.add("....#..#...##..#.#.###..");
        data.add(".####...#..#.....#......");
        data.add("#..#.##..#..###.#.##....");
        data.add("#.####..#.####.#.#.###..");
        data.add("###.#.#...#.######.#..##");
        data.add("#.####....##..########.#");
        data.add("##..##.#...#...#.#.#.#..");
        data.add("...#..#..#.#.##..###.###");
        data.add(".#.#....#.##.#...###.##.");
        data.add("###.#...#..#.##.######..");
        data.add(".#.#.###.##.##.#..#.##..");
        data.add(".####.###.#...###.#..#.#");
        data.add("..#.#..#..#.#.#.####.###");
        data.add("#..####...#.#.#.###.###.");
        data.add("#####..#####...###....##");
        data.add("#.##..#..#...#..####...#");
        data.add(".#.###..##..##..####.##.");
        data.add("...###...##...#...#..###");
        return data;
    }

    @Test
    void countRoughness() {
        SeaMonsterFinder finder = new SeaMonsterFinder(getTestData());
        finder.findAllSeaMonsters();
        assertEquals(273, finder.countRoughness());
    }
}