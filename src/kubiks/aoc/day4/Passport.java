package kubiks.aoc.day4;

import java.util.HashMap;
import java.util.Map;

public class Passport {
    Map<String, String> data = new HashMap<>();

    public Passport(String rawData) {
        String[] splitData = rawData.split(" ");
        for (int i=0; i<splitData.length; i++) {
            String[] keyValue = splitData[i].split(":");
            data.put(keyValue[0], keyValue[1]);
        }
    }

    public String getValue(String field) {
        if (!data.containsKey(field)) {
            return null;
        }
        return data.get(field);
    }

    public boolean areAllFieldsExceptCidPresent() {
        return data.containsKey("byr") &&
                data.containsKey("iyr") &&
                data.containsKey("eyr") &&
                data.containsKey("hgt") &&
                data.containsKey("hcl") &&
                data.containsKey("ecl") &&
                data.containsKey("pid");
    }

    public static boolean validateByr(String value) {
        return value.matches("[0-9]{4}") &&
                Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
    }

    public static boolean validateIyr(String value) {
        return value.matches("[0-9]{4}") &&
                Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
    }

    public static boolean validateEyr(String value) {
        return value.matches("[0-9]{4}") &&
                Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
    }

    public static boolean validateHgt(String value) {
        if (!value.matches("[0-9]+(cm|in)")) {
            return false;
        }
        int hgt = Integer.parseInt(value.substring(0, value.length() - 2));
        return value.indexOf("cm") >= 0 ? hgt >= 150 && hgt <= 193 : hgt >= 59 && hgt <= 76;
    }

    public static boolean validateHcl(String value) {
        return value.matches("#[0-9a-f]{6}");
    }

    public static boolean validateEcl(String value) {
        return value.matches("(amb|blu|brn|gry|grn|hzl|oth)");
    }

    public static boolean validatePid(String value) {
        return value.matches("[0-9]{9}");
    }

    public boolean validateAllFieldsExceptCid() {
        if (!areAllFieldsExceptCidPresent()) {
            return false;
        }
        return validateByr(data.get("byr")) &&
                validateIyr(data.get("iyr")) &&
                validateEyr(data.get("eyr")) &&
                validateHgt(data.get("hgt")) &&
                validateHcl(data.get("hcl")) &&
                validateEcl(data.get("ecl")) &&
                validatePid(data.get("pid"));
    }

}
