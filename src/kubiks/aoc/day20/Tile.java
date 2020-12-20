package kubiks.aoc.day20;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Tile {
    public enum EdgeSide {
        TOP, BOTTOM, LEFT, RIGHT
    }

    int size;
    int id;
    String[] map;


    public int getId() {
        return id;
    }

    public boolean isMatched() {
        return matched;
    }

    public int getRotation() {
        return rotation;
    }

    boolean matched = false;
    int rotation = 0;


    boolean flipped = false;
    String[] edges = new String[4];

    public Tile(List<String> data) {
        id = Integer.parseInt(data.get(0).split(" ")[1].replace(":", ""));
        size = data.get(1).length();
        map = new String[size];
        for (int i=1; i<data.size(); i++) {
            map[i-1] = data.get(i);
        }
        calculateInitialEdges();
    }

    void calculateInitialEdges() {
        edges[0] = map[0];
        String leftEdge = "";
        String rightEdge = "";
        for (int i=0; i<size; i++) {
            leftEdge += map[i].charAt(0);
            rightEdge += map[i].charAt(size-1);
        }
        edges[1] = rightEdge;
        edges[2] = map[size-1];
        edges[3] = leftEdge;
    }

    public String getEdge(EdgeSide side) {
        switch (side) {
            case TOP: return edges[0];
            case RIGHT: return edges[1];
            case BOTTOM: return edges[2];
            case LEFT: return edges[3];
        }
        return null;
    }

    String invertString(String s) {
        String res = "";
        for (int i = s.length()-1; i>=0; i--) {
            res += s.charAt(i);
        }
        return res;
    }

    public void rotateLeft() {
        rotation = rotation==3? 0 : rotation+1;
        String newTopEdge = getEdge(EdgeSide.RIGHT);
        String newRightEdge = invertString(getEdge(EdgeSide.BOTTOM));
        String newBottomEdge = getEdge(EdgeSide.LEFT);
        String newLeftEdge = invertString(getEdge(EdgeSide.TOP));
        edges[0] = newTopEdge;
        edges[1] = newRightEdge;
        edges[2] = newBottomEdge;
        edges[3] = newLeftEdge;
    }

    public void flipVertical() {
        flipped = !flipped;
        String newTopEdge = getEdge(EdgeSide.BOTTOM);
        String newRightEdge = invertString(getEdge(EdgeSide.RIGHT));
        String newBottomEdge = getEdge(EdgeSide.TOP);
        String newLeftEdge = invertString(getEdge(EdgeSide.LEFT));
        edges[0] = newTopEdge;
        edges[1] = newRightEdge;
        edges[2] = newBottomEdge;
        edges[3] = newLeftEdge;
    }

    public boolean tryMatch(String otherEdge, EdgeSide ourSide) {
        if (matched) {
            return otherEdge.equals(getEdge(ourSide));
        }
        for (int f=0; f<2; f++) {
            for (int i = 0; i < 4; i++) {
                if (otherEdge.equals(getEdge(ourSide))) {
                    matched = true;
                    return true;
                }
                rotateLeft();
            }
            flipVertical();
        }
        return false;
    }

    public boolean isFlipped() {
        return flipped;
    }

    public String toString() {
        return Integer.toString(id);
    }

    public char getCharAt(int x, int y) {
        int newX = x;
        int newY = y;
        switch (rotation) {
            case 1: newX = size-1-y; newY = x; break;
            case 2: newX = size-1-x; newY = size-1-y; break;
            case 3: newX = y; newY = size-1-x; break;
        }
        if (flipped) {
            newY = size-1-newY;
        }
        return map[newY].charAt(newX);
    }

    public String getRowAsString(int row) {
        String s = "";
        for (int x=0; x<size; x++) {
            s = s+getCharAt(x, row);
        }
        return s;
    }

    public char getCharAtOriginalState(int x, int y) {
        return map[y].charAt(x);
    }

    public void printTile() {
        System.out.format("T: ID=%d, %s, r=%d\n", getId(), isFlipped() ? "Y" : "N", getRotation());
        for (int y=0; y<size ; y++) {
            String s = "";
            for (int x=0; x<size; x++) {
                s = s + getCharAt(x, y);
            }
            System.out.println(s);
        }
    }

    public void printTileOriginalState() {
        System.out.format("O: ID=%d\n", getId(), isFlipped() ? "Y" : "N", getRotation());
        for (int y=0; y<size ; y++) {
            String s = "";
            for (int x=0; x<size; x++) {
                s = s + getCharAtOriginalState(x, y);
            }
            System.out.println(s);
        }
    }


}
