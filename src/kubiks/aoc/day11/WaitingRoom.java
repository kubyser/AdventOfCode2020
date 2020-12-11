package kubiks.aoc.day11;

import kubiks.aoc.utils.IntVector2D;

import java.util.*;

public class WaitingRoom {
    public enum SeatState {
        NO_SEAT('.'),
        VACANT('L'),
        OCCUPIED('#');

        public final Character code;

        private SeatState(Character code) {
            this.code = code;
        }

        public static WaitingRoom.SeatState byCode(Character label) {
            for (WaitingRoom.SeatState a : values()) {
                if (a.code.equals(label)) {
                    return a;
                }
            }
            return null;
        }
    }

    List<List<SeatState>> seatMap;
    int numRows;
    int numCols;
    Map<String, Set<IntVector2D>> nearestNeighborsMap;

    public WaitingRoom(List<String> data) {
        seatMap = new ArrayList<>();
        for (String s : data) {
            List<SeatState> row = new ArrayList<>();
            for (Character c : s.toCharArray()) {
                row.add(SeatState.byCode(c));
            }
            seatMap.add(row);
        }
        numRows = seatMap.size();
        numCols = seatMap.get(0).size();
        fillNearestNeighborsMap();
    }

    void fillNearestNeighborsMap() {
        nearestNeighborsMap = new HashMap<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (getSeatState(row, col) == SeatState.NO_SEAT) {
                    continue;
                }
                Set<IntVector2D> nearestNeighbor = new HashSet<>();
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, -1, 0));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, -1, 1));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, 0, 1));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, 1, 1));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, 1, 0));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, 1, -1));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, 0, -1));
                addToSetIfNotNull(nearestNeighbor, findNearestNeighbor(row, col, -1, -1));
                nearestNeighborsMap.put(row+":"+col, nearestNeighbor);
            }
        }
    }

    void addToSetIfNotNull(Set set, Object element) {
        if (element != null) {
            set.add(element);
        }
    }

    IntVector2D findNearestNeighbor(int startRow, int startCol, int incRow, int incCol) {
        int x=startRow+incRow;
        int y=startCol+incCol;
        while (x>=0 && x<numRows && y>=0 && y<numCols) {
            if (getSeatState(x, y) != SeatState.NO_SEAT) {
                return new IntVector2D(x, y);
            }
            x+=incRow;
            y+=incCol;
        }
        return null;
    }

    public SeatState getSeatState(int row, int col) {
        return seatMap.get(row).get(col);
    }

    public void setSeatState(int row, int col, SeatState state) {
        seatMap.get(row).set(col, state);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumberOfOccupiedNeighbors(int row, int col) {
        int count = 0;
        for (int x = row - 1; x <= row + 1; x++) {
            for (int y = col - 1; y <= col + 1; y++) {
                if (x >= 0 && x < numRows && y >= 0 && y < numCols && (x != row || y != col)) {
                    if (getSeatState(x, y) == SeatState.OCCUPIED) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public int getNumberOfNearestOccupiedNeighbors(int row, int col) {
        Set<IntVector2D> set = nearestNeighborsMap.get(row+":"+col);
        return (int)set.stream().filter(e -> getSeatState(e.getX(), e.getY()) == SeatState.OCCUPIED).count();
    }

    public int getNumberOfItemsInState(SeatState state) {
        int count = seatMap.stream().map(row -> (int)row.stream().filter(seat -> seat == state).count()).reduce(0, Integer::sum);
        return count;
    }

}
