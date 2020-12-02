package kubiks.aoc.day2;

public class PasswordPolicy {
    int firstNum;
    int secondNum;
    char requiredChar;

    public PasswordPolicy(int firstNum, int secondNum, char requiredChar) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
        this.requiredChar = requiredChar;
    }

    public boolean stringMatchesOldPolicy(String inputString) {
        int numChar = new Long(inputString.chars().filter(c -> c == requiredChar).count()).intValue();
        return numChar >= firstNum && numChar <= secondNum;
    }

    public boolean stringMatchesNewPolicy(String inputString) {
        int c = 0;
        if (inputString.length() >= firstNum && inputString.charAt(firstNum - 1) == requiredChar) {
            c++;
        }
        if (inputString.length() >= secondNum && inputString.charAt(secondNum - 1) == requiredChar) {
            c++;
        }
        return c == 1;
    }

}
