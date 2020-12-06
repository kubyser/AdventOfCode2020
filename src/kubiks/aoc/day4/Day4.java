package kubiks.aoc.day4;

import kubiks.aoc.utils.FileReaderUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class Day4 {
    List<String> data;

    public Day4(List<String> data) {
        this.data = data;
    }

    public int countValid(boolean fullValidation) {
        int answer = 0;
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()) {
            Passport passport = new Passport(iterator.next());
            if (fullValidation ? passport.validateAllFieldsExceptCid() : passport.areAllFieldsExceptCidPresent()) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4(FileReaderUtils.readWithDoubleCr("resources/day4_input.txt", true));
        int answer = day4.countValid(false);
        System.out.format("Answer: %d\n", answer);
        answer = day4.countValid(true);
        System.out.format("Answer part 2: %d\n", answer);
    }
}