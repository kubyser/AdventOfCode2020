package kubiks.aoc.tests;

import kubiks.aoc.day1.Day1;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderUtilsTest {

    @org.junit.jupiter.api.Test
    void readIntegerListFromFile() {
        List<Integer> data = FileReaderUtils.readIntegerListFromFile("resources/day1_test_input.txt");
        assertEquals(10, data.size());
        assertEquals(1975, data.get(0));
        assertEquals(1933, data.get(9));
    }

    @org.junit.jupiter.api.Test
    void readLongListFromFile() {
        List<Long> data = FileReaderUtils.readLongListFromFile("resources/longlist.txt");
        assertEquals(12, data.size());
        assertEquals(117452844395560L, data.get(0));
        assertEquals(205596899114177L, data.get(9));
        assertEquals(100610451401986L, data.get(11));
    }


    @Test
    void readStringListFromFile() {
        List<String> data = FileReaderUtils.readStringListFromFile("resources/day2_test_input.txt");
        assertEquals(3, data.size());
        assertEquals("1-3 a: abcde", data.get(0));
        assertEquals("1-3 b: cdefg", data.get(1));
        assertEquals("2-9 c: ccccccccc", data.get(2));
    }

    @Test
    void readWithDoubleCr() {
        List<String> data = FileReaderUtils.readWithDoubleCr("resources/day4_test_input.txt", true);
        assertEquals(4, data.size());
        assertEquals("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm", data.get(0));
        assertEquals("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929", data.get(1));
        assertEquals("hcl:#ae17e1 iyr:2013" +
                " eyr:2024" +
                " ecl:brn pid:760753108 byr:1931" +
                " hgt:179cm", data.get(2));
        assertEquals("hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in", data.get(3));
        data = FileReaderUtils.readWithDoubleCr("resources/day6_test_input.txt", false);
        assertEquals(5, data.size());
        assertEquals("abc", data.get(0));
        assertEquals("abc", data.get(1));
        assertEquals("abac", data.get(2));
        assertEquals("aaaa", data.get(3));
        assertEquals("b", data.get(4));
    }
}