package kubiks.aoc.day20;

import java.util.*;

public class TileArray {
    LinkedList<Tile> unmatchedTiles;
    Map<String, Tile> layout;
    int minX=0, maxX=0, minY=0, maxY=0;

    public TileArray(List<String> data) {
        unmatchedTiles = new LinkedList<>();
        layout = new HashMap<>();
        List<String> tileData = new ArrayList<>();
        for (String s:data) {
            if (s.equals("")) {
                unmatchedTiles.add(new Tile(tileData));
                tileData = new ArrayList<>();
            } else {
                tileData.add(s);
            }
        }
        if (tileData.size() > 0) {
            unmatchedTiles.add(new Tile(tileData));
        }
    }

    String idFromXY(int x, int y) {
        return x+":"+y;
    }

    String getNeighbourPos(String posId, Tile.EdgeSide side) {
        int x = Integer.parseInt(posId.split(":")[0]);
        int y = Integer.parseInt(posId.split(":")[1]);
        switch (side) {
            case TOP: return idFromXY(x, y+1);
            case BOTTOM: return idFromXY(x, y-1);
            case LEFT: return idFromXY(x-1, y);
            case RIGHT: return idFromXY(x+1, y);
        }
        return null;
    }

    void findMatch(String posId, Tile.EdgeSide side) {
        String newPosId = getNeighbourPos(posId, side);
        if (layout.containsKey(newPosId)) {
            return;
        }
        Tile tile = layout.get(posId);
        Tile.EdgeSide otherSide;
        switch (side) {
            case TOP: otherSide = Tile.EdgeSide.BOTTOM; break;
            case BOTTOM: otherSide = Tile.EdgeSide.TOP; break;
            case LEFT: otherSide = Tile.EdgeSide.RIGHT; break;
            case RIGHT: otherSide = Tile.EdgeSide.LEFT; break;
            default: otherSide = null;
        }
        for (Tile candidate : unmatchedTiles) {
            if (candidate.tryMatch(tile.getEdge(side), otherSide)) {
                layout.put(newPosId, candidate);
                unmatchedTiles.remove(candidate);
                fitNeighbours(newPosId);
                return;
            }
        }
    }

    void fitNeighbours(String posId) {
        if (!layout.containsKey(posId)) {
            System.out.format("ERROR: no tile in layout at %s\n", posId);
            return;
        }
        findMatch(posId, Tile.EdgeSide.TOP);
        findMatch(posId, Tile.EdgeSide.BOTTOM);
        findMatch(posId, Tile.EdgeSide.LEFT);
        findMatch(posId, Tile.EdgeSide.RIGHT);
    }

    void updateMinMax() {
        for (String posId : layout.keySet()) {
            int x = Integer.parseInt(posId.split(":")[0]);
            int y = Integer.parseInt(posId.split(":")[1]);
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        return layout.get(idFromXY(x, y));
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean matchAll() {
        Tile tile = unmatchedTiles.removeFirst();
        layout.put(idFromXY(0, 0), tile);
        fitNeighbours(idFromXY(0, 0));
        updateMinMax();
        return unmatchedTiles.isEmpty();
    }

    public List<String> drawMap() {
        List<String> map = new ArrayList<>();
        int tileSize = layout.get(idFromXY(minX, minY)).size;
        for (int y=minY; y<=maxY; y++) {
            for (int tileY=tileSize-2; tileY>=1; tileY--) {
                String row = "";
                for (int x=minX; x<=maxX; x++) {
                    for (int tileX=1; tileX<tileSize-1; tileX++) {
                        row = row + layout.get(idFromXY(x, y)).getCharAt(tileX, tileY);
                    }
                }
                map.add(row);
            }
        }
        return map;
    }

    public void printTilesLayout() {
        for (int y=maxY; y>=minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                Tile tile = getTileAt(x, y);
                System.out.format("[%d,%d]: %d, %s, r=%d     ", x, y, tile.getId(), tile.isFlipped() ? "Y" : "N", tile.getRotation());
            }
            System.out.println("");
        }
    }

    public void printMapAsIs() {
        System.out.println("======= START OF MAP PRINT ===========");
        int tileSize = layout.get(idFromXY(minX, minY)).size;
        for (int y=minY; y<=maxY; y++) {
            for (int tileY=tileSize-1; tileY>=0; tileY--) {
                String row = "";
                for (int x=minX; x<=maxX; x++) {
                    for (int tileX=0; tileX<tileSize; tileX++) {
                        row = row + layout.get(idFromXY(x, y)).getCharAt(tileX, tileY);
                    }
                    row = row + " ";
                }
                System.out.println(row);
            }
            System.out.println("");
        }
        System.out.println("======= =========== ===========");
    }
}
