package kubiks.aoc.day20;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.List;

public class Day20 {
    TileArray tileArray;
    public Day20(List<String> data) {
        tileArray = new TileArray(data);
    }

    public long solvePart1() {
        if (!tileArray.matchAll()) {
            return -1;
        }
        return (long)tileArray.getTileAt(tileArray.getMinX(), tileArray.getMinY()).getId() *
                (long)tileArray.getTileAt(tileArray.getMinX(), tileArray.getMaxY()).getId() *
                (long)tileArray.getTileAt(tileArray.getMaxX(), tileArray.getMinY()).getId() *
                (long)tileArray.getTileAt(tileArray.getMaxX(), tileArray.getMaxY()).getId();
    }

    public int solvePart2() {
        List<String> map = tileArray.drawMap();
        SeaMonsterFinder finder = new SeaMonsterFinder(map);
        finder.findAllSeaMonsters();
        return finder.countRoughness();
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20(FileReaderUtils.readStringListFromFile("resources/day20_input.txt"));
        long answer = day20.solvePart1();
        System.out.format("Product of corner tiles: %d\n", answer);
        int answerPart2 = day20.solvePart2();
        System.out.format("Total roughness: %d\n", answerPart2);
    }
}
