package kubiks.aoc.day24;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;

public class Day24 {
    Set<String> blacks = new HashSet<>();
    Map<String, Integer> neighbours;

    static String xyToPos(int x, int y) {
        return x+":"+y;
    }

    static String pathToPos(String path) {
        int x=0, y=0;
        String shortenedPath = path.replace("se", "s").replace("sw", "S").replace("ne", "N").replace("nw", "n");
        for (char c: shortenedPath.toCharArray()) {
            switch (c) {
                case 'w': x--; break;
                case 'e': x++; break;
                case 's': y--; break;
                case 'n': y++; break;
                case 'S': y--; x--; break;
                case 'N': y++; x++; break;
            }
        }
        return xyToPos(x, y);
    }

    public void flipTileByPath(String path) {
        String pos = pathToPos(path);
        flipTileByPos(pos);
    }

    void flipTileByPos(String pos) {
        if (blacks.contains(pos)) {
            blacks.remove(pos);
        } else {
            blacks.add(pos);
        }
    }

    void flipTilesByPosSet(Set<String> posSet) {
        for (String pos : posSet) {
            flipTileByPos(pos);
        }
    }


    int getNumBlacks() {
        return blacks.size();
    }

    public int flipTilesByPaths(List<String> paths) {
        for (String s: paths) {
            flipTileByPath(s);
        }
        return getNumBlacks();
    }

    void increaseNumNeighbours(int x, int y) {
        String pos = xyToPos(x, y);
        neighbours.put(pos, neighbours.containsKey(pos) ? neighbours.get(pos) + 1 : 1);
    }

    void increaseAllNeighboursOf(int x, int y) {
        increaseNumNeighbours(x, y+1);
        increaseNumNeighbours(x+1, y+1);
        increaseNumNeighbours(x+1, y);
        increaseNumNeighbours(x, y-1);
        increaseNumNeighbours(x-1, y-1);
        increaseNumNeighbours(x-1, y);
    }

    void calculateNeighbours() {
        neighbours = new HashMap<>();
        for (String pos: blacks) {
            String[] split = pos.split(":");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            increaseAllNeighboursOf(x, y);
            if (!neighbours.containsKey(pos)) {
                neighbours.put(pos, 0);
            }
        }
    }

    void playRound() {
        calculateNeighbours();
        Set<String> flipSet = new HashSet<>();
        for (String pos: neighbours.keySet()) {
            int numNeighbours = neighbours.get(pos);
            if (blacks.contains(pos)) {
                if (numNeighbours == 0 || numNeighbours > 2) {
                    flipSet.add(pos);
                }
            } else {
                if (numNeighbours == 2) {
                    flipSet.add(pos);
                }
            }
        }
        flipTilesByPosSet(flipSet);
    }

    public int playNRounds(int n) {
        for (int i=0; i<n; i++) {
            playRound();
        }
        return getNumBlacks();
    }

    public static void main(String[] args) {
        Day24 day24 = new Day24();
        int answer = day24.flipTilesByPaths(FileReaderUtils.readStringListFromFile("resources/day24_input.txt"));
        System.out.format("Number of black tiles: %d\n", answer);
        int answerPart2 = day24.playNRounds(100);
        System.out.format("Number of black tiles after 100 moves: %d\n", answerPart2);
    }


}
