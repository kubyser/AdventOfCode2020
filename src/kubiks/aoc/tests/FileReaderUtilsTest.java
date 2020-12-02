package kubiks.aoc.tests;

import kubiks.aoc.day1.Day1;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilsTest {

    @org.junit.jupiter.api.Test
    void readListFromFile() throws FileNotFoundException {
        List<Integer> data = FileReaderUtils.readIntegerListFromFile("resources/day1_test_input.txt");
        assertEquals(10, data.size());
        assertEquals(1975, data.get(0));
        assertEquals(1933, data.get(9));
    }

    @Test
    void readStringListFromFile() throws FileNotFoundException {
        List<String> data = FileReaderUtils.readStringListFromFile("resources/day2_test_input.txt");
        assertEquals(3, data.size());
        assertEquals("1-3 a: abcde", data.get(0));
        assertEquals("1-3 b: cdefg", data.get(1));
        assertEquals("2-9 c: ccccccccc", data.get(2));
    }
}