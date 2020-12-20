package kubiks.aoc.day20;

import java.util.ArrayList;
import java.util.List;

public class SeaMonsterFinder {
    List<String> map;
    List<String> seaMonsterImage;
    int size;
    int monsterWidth;
    int monsterHeight;

    public SeaMonsterFinder(List<String> map) {
        this.map = map;
        size = map.size();
        seaMonsterImage = new ArrayList<>();
        seaMonsterImage.add("                  # ");
        seaMonsterImage.add("#    ##    ##    ###");
        seaMonsterImage.add(" #  #  #  #  #  #   ");
        monsterWidth = seaMonsterImage.get(0).length();
        monsterHeight = seaMonsterImage.size();
    }

    public boolean lookForSeaMonsterAt(int topLeftX, int topLeftY) {
        if (topLeftX > size-monsterWidth || topLeftY > size-monsterHeight) {
            return false;
        }
        for (int y = 0; y<monsterHeight; y++) {
            for (int x = 0; x < monsterWidth; x++) {
                if (seaMonsterImage.get(y).charAt(x) == '#' && map.get(topLeftY + y).charAt(topLeftX + x) != '#'
                && map.get(topLeftY + y).charAt(topLeftX + x) != 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    void drawSeaMonsterAt(int topLeftX, int topLeftY) {
        for (int y = 0; y < monsterHeight; y++) {
            for (int x = 0; x < monsterWidth; x++) {
                if (seaMonsterImage.get(y).charAt(x) == '#') {
                    char[] c = map.get(topLeftY + y).toCharArray();
                    c[topLeftX + x] = 'O';
                    map.set(topLeftY + y, String.valueOf(c));
                }
            }
        }
    }

    public void findAllSeaMonsters() {
        boolean monsterFound = false;
        for (int f=0; f<=1; f++) {
            for (int r=0; r<4; r++) {
                for (int x=0; x<size; x++) {
                    for (int y=0; y<size; y++) {
                        boolean result = lookForSeaMonsterAt(x, y);
                        if (result) {
                            monsterFound = true;
                            drawSeaMonsterAt(x, y);
                        }
                    }
                }
                if (monsterFound) {
                    return;
                }
                rotateLeft();
            }
            flipVertical();
        }
    }

    void rotateLeft() {
        List<String> newMap = new ArrayList<>();
        for (int y=size-1; y>=0; y--) {
            String row = "";
            for (String s: map) {
                row = row + s.charAt(y);
            }
            newMap.add(row);
        }
        map = newMap;
    }

    void flipVertical() {
        List<String> newMap = new ArrayList<>();
        for (int i=map.size()-1; i>=0; i--) {
            newMap.add(map.get(i));
        }
        map = newMap;
    }

    public void printMap() {
        for (String s: map) {
            System.out.println(s);
        }
    }

    public int countRoughness() {
        int roughness = 0;
        for (String s : map) {
            for (char c:s.toCharArray()) {
                if (c == '#') {
                    roughness ++;
                }
            }
        }
        return roughness;
    }

}
