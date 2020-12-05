package kubiks.aoc.day5;

import kubiks.aoc.utils.FileReaderUtils;
import kubiks.aoc.utils.IntVector2D;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    final int NUMBER_OF_SEATS = 1024;
    List<String> data;
    int[] occupancy;
    
    public Day5(List<String> data) {
        this.data = data;
        occupancy = new int[NUMBER_OF_SEATS];
    }
    
    public int findMaxSeatId() throws Exception {
        int max = 0;
        for (String code : data) {
            int seatId = getSeatId(code);
            markSeatInOccupancy(seatId);
            if (seatId > max) {
                max = seatId;
            }
        }
        return max;
    }

    public int findEmptySeat() {
        for (int i=0; i<NUMBER_OF_SEATS; i++) {
            if (occupancy[i] == 2) {
                return i;
            }
        }
        return -1;
    }

    void markSeatInOccupancy(int seatId) {
        occupancy[seatId] = 3;
        if (seatId > 0) {
            occupancy[seatId-1]++;
        }
        if (seatId < NUMBER_OF_SEATS - 1) {
            occupancy[seatId+1]++;
        }
    }

    public static int getSeatId(String inputString) throws Exception {
        int minRow = 0;
        int maxRow = 127;
        int minSeat = 0;
        int maxSeat = 7;
        int pos = 0;
        while (pos < inputString.length()) {
            switch (inputString.charAt(pos)) {
                case 'F' : maxRow = (maxRow + minRow) / 2; break;
                case 'B' : minRow = (maxRow + minRow) / 2 + 1; break;
                case 'L' : maxSeat = (maxSeat + minSeat) / 2; break;
                case 'R' : minSeat = (maxSeat + minSeat) / 2 + 1; break;
                default: throw new Exception("Unexpected char [" + inputString.charAt(pos) + "] in input string, aborting.");
            }
            pos++;
        }
        if (minRow != maxRow || minSeat != maxSeat) {
            throw new Exception("Position undefined and mo more input");
        }
        return minRow * 8 + minSeat;
    }

    public static void main(String[] args) throws Exception {
        Day5 day5 = new Day5(FileReaderUtils.readStringListFromFile("resources/day5_input.txt"));
        int answer = day5.findMaxSeatId();
        System.out.format("Max set Id: %d\n", answer);
        int emptySeatId = day5.findEmptySeat();
        System.out.format("Empty seat ID: %d\n", emptySeatId);
    }
}
