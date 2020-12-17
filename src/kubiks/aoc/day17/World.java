package kubiks.aoc.day17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {
    public enum CellState {
        ACTIVE,
        INACTIVE;
    }

    final static char INPUT_ACTIVE_CHARACTER = '#';

    Map<String, CellState> map;
    Map<String, Integer> neighbours;
    boolean mode4d = false;

    public World(List<String> data) {
        this(data, false);
    }

    public World(List<String> data, boolean mode4d) {
        this.mode4d = mode4d;
        int y = 0;
        map = new HashMap<>();
        neighbours = new HashMap<>();
        for (String line : data) {
            int x = 0;
            for (char c : line.toCharArray()) {
                if (c == INPUT_ACTIVE_CHARACTER) {
                    map.put(mode4d ? cellIdFromXYZ(x, y, 0, 0) : cellIdFromXYZ(x, y, 0), CellState.ACTIVE);
                }
                x++;
            }
            y++;
        }
    }


    public static String cellIdFromXYZ(int x, int y, int z) {
        return x+":"+y+":"+z;
    }

    public static String cellIdFromXYZ(int x, int y, int z, int t) {
        return x+":"+y+":"+z+":"+t;
    }


    public static int[] posFromCellId(String id) {
        String[] split = id.split(":");
        int[] pos = new int[split.length];
        for (int i=0; i<split.length; i++) {
            pos[i] = Integer.parseInt(split[i]);
        }
        return pos;
    }

    public CellState getCellState(int x, int y, int z) {
        if (!map.containsKey(cellIdFromXYZ(x, y, z))) {
            return CellState.INACTIVE;
        }
        return map.get(cellIdFromXYZ(x, y, z));
    }

    public void setCellState(int x, int y, int z, CellState state) {
        map.put(cellIdFromXYZ(x, y, z), state);
    }

    void setCellState(String cellId, CellState state) {
        map.put(cellId, state);
    }

    public int getNumNeighbours(int x, int y, int z) {
        if (!neighbours.containsKey(cellIdFromXYZ(x, y, z))) {
            return 0;
        }
        return neighbours.get(cellIdFromXYZ(x, y, z));
    }

    public void incrementAllNeighbours(int[] pos) {
        int t = mode4d ? pos[3]-1 : 0;
        do {
            for (int x = pos[0] - 1; x <= pos[0] + 1; x++) {
                for (int y = pos[1] - 1; y <= pos[1] + 1; y++) {
                    for (int z = pos[2] - 1; z <= pos[2] + 1; z++) {
                        if (x != pos[0] || y != pos[1] || z != pos[2] || (mode4d && t != pos[3])) {
                            String newCellId = mode4d ? cellIdFromXYZ(x, y, z, t) : cellIdFromXYZ(x, y, z);
                            if (neighbours.containsKey(newCellId)) {
                                neighbours.put(newCellId, neighbours.get(newCellId) + 1);
                            } else {
                                neighbours.put(newCellId, 1);
                            }
                        }
                    }
                }
            }
            t++;
        } while (mode4d && t<=pos[3]+1);
    }

    public void calculateNeighbours() {
        neighbours = new HashMap<>();
        for (String cellId : map.keySet())  {
            if (map.get(cellId) == CellState.ACTIVE) {
                if (!neighbours.containsKey(cellId)) {
                    neighbours.put(cellId, 0);
                }
                incrementAllNeighbours(posFromCellId(cellId));
            }
        }
    }

    public void runCycle() {
        calculateNeighbours();
        int numNeighbours;
        for (String cellId : neighbours.keySet())  {
            numNeighbours = neighbours.get(cellId);
            if (map.get(cellId) == CellState.ACTIVE) {
                if (numNeighbours !=2 && numNeighbours != 3) {
                    setCellState(cellId, CellState.INACTIVE);
                }
            } else {
                if (numNeighbours == 3) {
                    setCellState(cellId, CellState.ACTIVE);
                }
            }
        }
    }

    public void runCycles(int num) {
        for (int i=0; i<num; i++) {
            runCycle();
        }
    }

    public long countActiveCells() {
        return map.values().stream().filter(e -> e == CellState.ACTIVE).count();
    }


}
