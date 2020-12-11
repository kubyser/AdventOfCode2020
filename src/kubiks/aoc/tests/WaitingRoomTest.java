package kubiks.aoc.tests;

import kubiks.aoc.day11.WaitingRoom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WaitingRoomTest {

    List<String> data;

    public WaitingRoomTest() {
        data = new ArrayList<>();
        data.add("L.LL.LL.LL");
        data.add("LLLLLLL.LL");
        data.add("L.L.L..L..");
        data.add("LLLL.LL.LL");
        data.add("L.LL.LL.LL");
        data.add("L.LLLLL.LL");
        data.add("..L.L.....");
        data.add("LLLLLLLLLL");
        data.add("L.LLLLLL.L");
        data.add("L.LLLLL.LL");
    }


    @Test
    void getSeatState() {
        WaitingRoom room = new WaitingRoom(data);
        assertEquals(10, room.getNumRows());
        assertEquals(10, room.getNumCols());
        assertEquals(WaitingRoom.SeatState.VACANT, room.getSeatState(0, 0));
        assertEquals(WaitingRoom.SeatState.NO_SEAT, room.getSeatState(0, 1));
        assertEquals(WaitingRoom.SeatState.NO_SEAT, room.getSeatState(6, 0));
        assertEquals(WaitingRoom.SeatState.VACANT, room.getSeatState(9, 9));
    }

    @Test
    void setSeatState() {
        WaitingRoom room = new WaitingRoom(data);
        room.setSeatState(5, 5, WaitingRoom.SeatState.OCCUPIED);
        assertEquals(WaitingRoom.SeatState.OCCUPIED, room.getSeatState(5, 5));
        assertEquals(WaitingRoom.SeatState.VACANT, room.getSeatState(5, 6));
        room.setSeatState(9, 9, WaitingRoom.SeatState.OCCUPIED);
        assertEquals(WaitingRoom.SeatState.OCCUPIED, room.getSeatState(9, 9));
        room.setSeatState(5, 5, WaitingRoom.SeatState.VACANT);
        assertEquals(WaitingRoom.SeatState.VACANT, room.getSeatState(5, 5));
    }

    @Test
    void getNumberOfNeighbors() {
        WaitingRoom room = new WaitingRoom(data);
        room.setSeatState(1, 1, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(5, 5, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(4, 5, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 4, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 4, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 4, WaitingRoom.SeatState.OCCUPIED);
        assertEquals(1, room.getNumberOfOccupiedNeighbors(0, 0));
        assertEquals(1, room.getNumberOfOccupiedNeighbors(0, 1));
        assertEquals(1, room.getNumberOfOccupiedNeighbors(0, 2));
        assertEquals(0, room.getNumberOfOccupiedNeighbors(0, 3));
        assertEquals(1, room.getNumberOfOccupiedNeighbors(5, 5));
        assertEquals(3, room.getNumberOfOccupiedNeighbors(7, 2));
        assertEquals(5, room.getNumberOfOccupiedNeighbors(7, 3));
        assertEquals(3, room.getNumberOfOccupiedNeighbors(7, 4));
        assertEquals(5, room.getNumberOfOccupiedNeighbors(8, 2));
        assertEquals(8, room.getNumberOfOccupiedNeighbors(8, 3));
        assertEquals(5, room.getNumberOfOccupiedNeighbors(8, 4));
        assertEquals(3, room.getNumberOfOccupiedNeighbors(9, 2));
        assertEquals(5, room.getNumberOfOccupiedNeighbors(9, 3));
        assertEquals(3, room.getNumberOfOccupiedNeighbors(9, 4));
    }

    @Test
    void getNumberOfItemsInState() {
        WaitingRoom room = new WaitingRoom(data);
        assertEquals(0, room.getNumberOfItemsInState(WaitingRoom.SeatState.OCCUPIED));
        assertEquals(71, room.getNumberOfItemsInState(WaitingRoom.SeatState.VACANT));
        room.setSeatState(1, 1, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(5, 5, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(4, 5, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(7, 4, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(8, 4, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 2, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 3, WaitingRoom.SeatState.OCCUPIED);
        room.setSeatState(9, 4, WaitingRoom.SeatState.OCCUPIED);
        assertEquals(12, room.getNumberOfItemsInState(WaitingRoom.SeatState.OCCUPIED));
        assertEquals(59, room.getNumberOfItemsInState(WaitingRoom.SeatState.VACANT));
    }

    @Test
    void getNumberOfNearestOccupiedNeighbors() {
        List<String> data = new ArrayList<>();
        data.add(".......#.");
        data.add("...#.....");
        data.add(".#.......");
        data.add(".........");
        data.add("..#L....#");
        data.add("....#....");
        data.add(".........");
        data.add("#........");
        data.add("...#.....");
        WaitingRoom room = new WaitingRoom(data);
        assertEquals(8, room.getNumberOfNearestOccupiedNeighbors(4, 3));
        data = new ArrayList<>();
        data.add(".............");
        data.add(".L.L.#.#.#.#.");
        data.add(".............");
        room = new WaitingRoom(data);
        assertEquals(0, room.getNumberOfNearestOccupiedNeighbors(1, 1));
        data = new ArrayList<>();
        data.add(".##.##.");
        data.add("#.#.#.#");
        data.add("##...##");
        data.add("...L...");
        data.add("##...##");
        data.add("#.#.#.#");
        data.add(".##.##.");
        assertEquals(0, room.getNumberOfNearestOccupiedNeighbors(3, 3));
    }
}