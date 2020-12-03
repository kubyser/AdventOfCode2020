package kubiks.aoc.utils;

import java.util.List;

public class IntVector2D {
    int x;
    int y;

    public IntVector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2D(List<Integer> xyList) throws Exception {
        if (xyList.size() != 2) {
            throw new Exception("xyList size must be exactly two Integers");
        }
        this.x = xyList.get(0).intValue();
        this.y = xyList.get(1).intValue();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
