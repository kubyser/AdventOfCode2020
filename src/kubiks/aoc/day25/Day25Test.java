package kubiks.aoc.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {

    @Test
    void transform() {
        assertEquals(5764801, Day25.transform(7, 8));
        assertEquals(17807724, Day25.transform(7, 11));
        assertEquals(14897079, Day25.transform(17807724, 8));
        assertEquals(14897079, Day25.transform(5764801, 11));
    }

    @Test
    void reverseEngineerLoopSize() {
        assertEquals(8, Day25.reverseEngineerLoopSize(7, 5764801));
        assertEquals(11, Day25.reverseEngineerLoopSize(7, 17807724));
    }

    @Test
    void crackTheCode() {
        assertEquals(14897079, Day25.crackTheCode(17807724, 5764801));
    }

    @Test
    void day25Part1() {
        assertEquals(6408263, Day25.crackTheCode(8614349, 8335663));
    }
}