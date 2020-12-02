package kubiks.aoc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReaderUtils {

    public static List<Integer> readIntegerListFromFile(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        List<Integer>data = new ArrayList<Integer>();
        while (s.hasNextInt()) {
            data.add(s.nextInt());
        }
        return data;
    }

    public static List<String> readStringListFromFile(String fileName) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        List<String> data = br.lines().collect(Collectors.toList());
        return data;
    }

}
