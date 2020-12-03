package kubiks.aoc.day3;

import kubiks.aoc.utils.FileReaderUtils;
import kubiks.aoc.utils.IntVector2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day3 {
    Terrain terrain;

    public Day3(List<String> baseMap) {
        terrain = new Terrain(baseMap);
    }

    public int solve(int xSpeed, int ySpeed) {
        int xPos = 0;
        int yPos = 0;
        int numTrees = 0;
        try {
            while (yPos < terrain.getHeight()) {
                if (terrain.isTreeAtpos(xPos, yPos)) {
                    numTrees++;
                }
                xPos += xSpeed;
                while (xPos >= terrain.getWidth()) {
                    xPos -= terrain.getWidth();
                }
                yPos += ySpeed;
            }
            return numTrees;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int solveMultiply(List<IntVector2D> speedsList) {
        int product = 0;
        int processedSpeeds = 0;
        Iterator iterator = speedsList.iterator();
        while (iterator.hasNext()) {
            IntVector2D speed = (IntVector2D)iterator.next();
            int answer = solve(speed.getX(), speed.getY());
            product = processedSpeeds == 0 ? answer : product * answer;
            processedSpeeds++;
        }
        return product;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Day3 day3 = new Day3(FileReaderUtils.readStringListFromFile("resources/day3_input.txt"));
        int answer = day3.solve(3, 1);
        System.out.format("Answer: %d\n", answer);
        ArrayList<IntVector2D> speeds = new ArrayList<>();
        speeds.add(new IntVector2D(1, 1));
        speeds.add(new IntVector2D(3, 1));
        speeds.add(new IntVector2D(5, 1));
        speeds.add(new IntVector2D(7, 1));
        speeds.add(new IntVector2D(1, 2));
        answer = day3.solveMultiply(speeds);
        System.out.format("Answer for part 2: %d\n", answer);
    }


}
