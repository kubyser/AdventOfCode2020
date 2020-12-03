package kubiks.aoc.day3;

import kubiks.aoc.utils.FileReaderUtils;

import java.io.FileNotFoundException;
import java.util.List;

public class Terrain {
    List<String> baseMap;
    final char TREE_CHAR = '#';

    public Terrain(List<String> baseMap) {
        this.baseMap = baseMap;
    }

    public Terrain(String fileName) throws FileNotFoundException {
        this(FileReaderUtils.readStringListFromFile(fileName));
    }

    public int getHeight() {
        return baseMap.size();
    }

    public int getWidth() {
        return baseMap.get(0).length();
    }

    public boolean isTreeAtpos(int x, int y) throws Exception {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
            throw new Exception("Terrain index out of bounds ["+x+" "+y+"]");
        }
        return baseMap.get(y).charAt(x) == TREE_CHAR;
    }




}
