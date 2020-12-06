package kubiks.aoc.utils;

import java.io.*;
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

    public static List<String> readStringListFromFile(String fileName) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        List<String> data = br.lines().collect(Collectors.toList());
        return data;
    }

    public static List<String> readWithDoubleCr(String fileName, boolean crToSpace) {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        ArrayList<String> list = new ArrayList<>();
        String s;
        try {
            s = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        String fullS = null;
        while (s != null) {
            if (s.length() == 0) {
                if (fullS != null) {
                    list.add(fullS);
                }
                fullS = null;
            } else {
                if (fullS == null) {
                    fullS = s;
                } else {
                    fullS = crToSpace ? fullS + " " + s : fullS + s;
                }
            }
            try {
                s = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        if (fullS != null) {
            list.add(fullS);
        }
        return list;
    }

}
