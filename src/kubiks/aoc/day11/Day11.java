package kubiks.aoc.day11;

import kubiks.aoc.utils.FileReaderUtils;
import kubiks.aoc.utils.IntVector2D;

import java.util.LinkedList;
import java.util.List;

public class Day11 {
    WaitingRoom room;

    public Day11(List<String> data) {
        room = new WaitingRoom(data);
    }

    public int solve(boolean part2) {
        LinkedList<IntVector2D> newOccupied;
        LinkedList<IntVector2D> newVacant;
        do {
            newOccupied = new LinkedList<>();
            newVacant = new LinkedList<>();
            for (int col=0; col<room.getNumCols(); col++) {
                for (int row=0; row<room.getNumRows(); row++) {
                    WaitingRoom.SeatState state = room.getSeatState(row, col);
                    if (state == WaitingRoom.SeatState.VACANT || state == WaitingRoom.SeatState.OCCUPIED) {
                        int countNeighbors = part2 ?
                                room.getNumberOfNearestOccupiedNeighbors(row, col) :
                                room.getNumberOfOccupiedNeighbors(row, col);
                        if (state == WaitingRoom.SeatState.VACANT && countNeighbors == 0) {
                            newOccupied.add(new IntVector2D(row, col));
                        }
                        if (state == WaitingRoom.SeatState.OCCUPIED && countNeighbors >= (part2 ? 5 : 4)) {
                            newVacant.add(new IntVector2D(row, col));
                        }
                    }
                }
            }
            newOccupied.forEach(e -> room.setSeatState(e.getX(), e.getY(), WaitingRoom.SeatState.OCCUPIED));
            newVacant.forEach(e -> room.setSeatState(e.getX(), e.getY(), WaitingRoom.SeatState.VACANT));
        } while (!newOccupied.isEmpty() || !newVacant.isEmpty());
        return room.getNumberOfItemsInState(WaitingRoom.SeatState.OCCUPIED);
    }


    public static void main(String[] args) {
        Day11 day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_input.txt"));
        int answer = day11.solve(false);
        System.out.format("Number of occupied seats: %d\n", answer);
        day11 = new Day11(FileReaderUtils.readStringListFromFile("resources/day11_input.txt"));
        answer = day11.solve(true);
        System.out.format("Number of occupied seats in part2: %d\n", answer);
    }

}
