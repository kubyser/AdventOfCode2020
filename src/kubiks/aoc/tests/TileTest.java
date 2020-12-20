package kubiks.aoc.tests;

import kubiks.aoc.day20.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    List<String> testData1951() {
        List<String> data = new ArrayList<>();
        data.add("Tile 1951:");
        data.add("#.##...##.");
        data.add("#.####...#");
        data.add(".....#..##");
        data.add("#...######");
        data.add(".##.#....#");
        data.add(".###.#####");
        data.add("###.##.##.");
        data.add(".###....#.");
        data.add("..#.#..#.#");
        data.add("#...##.#..");
        return data;
    }

    List<String> testData2311() {
        List<String> data = new ArrayList<>();
        data.add("Tile 2311:");
        data.add("..##.#..#.");
        data.add("##..#.....");
        data.add("#...##..#.");
        data.add("####.#...#");
        data.add("##.##.###.");
        data.add("##...#.###");
        data.add(".#.#.#..##");
        data.add("..#....#..");
        data.add("###...#.#.");
        data.add("..###..###");
        return data;
    }

    List<String> testData2729() {
        List<String> data = new ArrayList<>();
        data.add("Tile 2729:");
        data.add("...#.#.#.#");
        data.add("####.#....");
        data.add("..#.#.....");
        data.add("....#..#.#");
        data.add(".##..##.#.");
        data.add(".#.####...");
        data.add("####.#.#..");
        data.add("##.####...");
        data.add("##..#.##..");
        data.add("#.##...##.");
        return data;
    }

    List<String> testData3079() {
        List<String> data = new ArrayList<>();
        data.add("Tile 3079:");
        data.add("#.#.#####.");
        data.add(".#..######");
        data.add("..#.......");
        data.add("######....");
        data.add("####.#..#.");
        data.add(".#...#.##.");
        data.add("#.#####.##");
        data.add("..#.###...");
        data.add("..#.......");
        data.add("..#.###...");
        return data;
    }

    List<String> testData2473() {
        List<String> data = new ArrayList<>();
        data.add("Tile 2473:");
        data.add("#....####.");
        data.add("#..#.##...");
        data.add("#.##..#...");
        data.add("######.#.#");
        data.add(".#...#.#.#");
        data.add(".#########");
        data.add(".###.#..#.");
        data.add("########.#");
        data.add("##...##.#.");
        data.add("..###.#.#.");
        return data;
    }

    List<String> testData2719() {
        List<String> data = new ArrayList<>();
        data.add("Tile 2719:");
        data.add(".#.##..##.");
        data.add(".#.....#..");
        data.add("#.....#...");
        data.add("##.#.....#");
        data.add("#######...");
        data.add("##...##.#.");
        data.add("......##..");
        data.add("........##");
        data.add("..#....###");
        data.add("#..#..#.##");
        return data;
    }


    @Test
    void rotateLeft() {
        Tile tile = new Tile(testData1951());
        assertEquals("#.##...##.", tile.getEdge(Tile.EdgeSide.TOP));
        assertEquals(".#####..#.", tile.getEdge(Tile.EdgeSide.RIGHT));
        assertEquals("#...##.#..", tile.getEdge(Tile.EdgeSide.BOTTOM));
        assertEquals("##.#..#..#", tile.getEdge(Tile.EdgeSide.LEFT));
        tile.rotateLeft();
        assertEquals(".#####..#.", tile.getEdge(Tile.EdgeSide.TOP));
        assertEquals("..#.##...#", tile.getEdge(Tile.EdgeSide.RIGHT));
        assertEquals("##.#..#..#", tile.getEdge(Tile.EdgeSide.BOTTOM));
        assertEquals(".##...##.#", tile.getEdge(Tile.EdgeSide.LEFT));
    }

    @Test
    void flipVertical() {
        Tile tile = new Tile(testData1951());
        assertEquals("#.##...##.", tile.getEdge(Tile.EdgeSide.TOP));
        assertEquals(".#####..#.", tile.getEdge(Tile.EdgeSide.RIGHT));
        assertEquals("#...##.#..", tile.getEdge(Tile.EdgeSide.BOTTOM));
        assertEquals("##.#..#..#", tile.getEdge(Tile.EdgeSide.LEFT));
        tile.flipVertical();
        assertEquals("#.##...##.", tile.getEdge(Tile.EdgeSide.BOTTOM));
        assertEquals(".#..#####.", tile.getEdge(Tile.EdgeSide.RIGHT));
        assertEquals("#...##.#..", tile.getEdge(Tile.EdgeSide.TOP));
        assertEquals("#..#..#.##", tile.getEdge(Tile.EdgeSide.LEFT));
    }


    @Test
    void tryMatch() {
        Tile tile1951 = new Tile(testData1951());
        Tile tile2311 = new Tile(testData2311());
        Tile tile2729 = new Tile(testData2729());
        assertTrue(tile2311.tryMatch(tile1951.getEdge(Tile.EdgeSide.RIGHT), Tile.EdgeSide.LEFT));
        assertTrue(tile2311.isMatched());
        assertEquals(0, tile2311.getRotation());
        assertFalse(tile2311.isFlipped());

        assertFalse(tile2729.tryMatch(tile1951.getEdge(Tile.EdgeSide.BOTTOM), Tile.EdgeSide.TOP));

        assertTrue(tile2729.tryMatch(tile1951.getEdge(Tile.EdgeSide.TOP), Tile.EdgeSide.BOTTOM));
        assertTrue(tile2729.isMatched());
        assertEquals(0, tile2729.getRotation());
        assertFalse(tile2729.isFlipped());

        Tile tile3079 = new Tile(testData3079());
        assertTrue(tile3079.tryMatch(tile2311.getEdge(Tile.EdgeSide.RIGHT), Tile.EdgeSide.LEFT));
        assertTrue(tile3079.isMatched());
        assertEquals(0, tile3079.getRotation());
        assertTrue(tile3079.isFlipped());

        Tile tile2473 = new Tile(testData2473());
        assertTrue(tile2473.tryMatch(tile3079.getEdge(Tile.EdgeSide.TOP), Tile.EdgeSide.BOTTOM));
        assertTrue(tile2473.isMatched());
        assertEquals(3, tile2473.getRotation());
        assertFalse(tile2473.isFlipped());
    }

    @Test
    void getRowAsString() {
        Tile tile2719 = new Tile(testData2719());
        assertEquals(".#.##..##.", tile2719.getRowAsString(0));
        assertEquals(".#.....#..", tile2719.getRowAsString(1));
        assertEquals("#.....#...", tile2719.getRowAsString(2));
        assertEquals("##.#.....#", tile2719.getRowAsString(3));
        assertEquals("#######...", tile2719.getRowAsString(4));
        assertEquals("##...##.#.", tile2719.getRowAsString(5));
        assertEquals("......##..", tile2719.getRowAsString(6));
        assertEquals("........##", tile2719.getRowAsString(7));
        assertEquals("..#....###", tile2719.getRowAsString(8));
        assertEquals("#..#..#.##", tile2719.getRowAsString(9));
        tile2719.flipVertical();
        assertEquals(".#.##..##.", tile2719.getRowAsString(9));
        assertEquals(".#.....#..", tile2719.getRowAsString(8));
        assertEquals("#.....#...", tile2719.getRowAsString(7));
        assertEquals("##.#.....#", tile2719.getRowAsString(6));
        assertEquals("#######...", tile2719.getRowAsString(5));
        assertEquals("##...##.#.", tile2719.getRowAsString(4));
        assertEquals("......##..", tile2719.getRowAsString(3));
        assertEquals("........##", tile2719.getRowAsString(2));
        assertEquals("..#....###", tile2719.getRowAsString(1));
        assertEquals("#..#..#.##", tile2719.getRowAsString(0));
        tile2719.printTile();
        tile2719.rotateLeft();
        tile2719.printTile();
        assertEquals("###...#...", tile2719.getRowAsString(0));
        assertEquals("###.#....#", tile2719.getRowAsString(1));
        assertEquals(".#.#....##", tile2719.getRowAsString(2));
        assertEquals("#..###.#..", tile2719.getRowAsString(3));
        assertEquals("....##....", tile2719.getRowAsString(4));
        assertEquals(".....#...#", tile2719.getRowAsString(5));
        assertEquals("#....##..#", tile2719.getRowAsString(6));
        assertEquals(".#...#....", tile2719.getRowAsString(7));
        assertEquals("....###.##", tile2719.getRowAsString(8));
        assertEquals("#...####..", tile2719.getRowAsString(9));
    }

}