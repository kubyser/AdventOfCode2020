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

    public static boolean validateField(String value, String field) {
        switch (field) {
            case "byr": return value.matches("[0-9]{4}") &&
                    Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
            case "iyr": return value.matches("[0-9]{4}") &&
                    Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
            case "eyr": return value.matches("[0-9]{4}") &&
                    Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
            case "hgt": if (!value.matches("[0-9]+(cm|in)")) {
                            return false;
                        }
                        int hgt = Integer.parseInt(value.substring(0, value.length() - 2));
                        return value.indexOf("cm") >= 0 ? hgt >= 150 && hgt <= 193 : hgt >= 59 && hgt <= 76;
            case "hcl": return value.matches("#[0-9a-f]{6}");
            case "ecl": return value.matches("(amb|blu|brn|gry|grn|hzl|oth)");
            case "pid": return value.matches("[0-9]{9}");
        }
        return false;
    }

    public boolean validateAllFieldsExceptCid() {
        if (!areAllFieldsExceptCidPresent()) {
            return false;
        }
        return validateField(data.get("byr"),"byr") &&
                validateField(data.get("iyr"), "iyr") &&
                validateField(data.get("eyr"), "eyr") &&
                validateField(data.get("hgt"), "hgt") &&
                validateField(data.get("hcl"), "hcl") &&
                validateField(data.get("ecl"), "ecl") &&
                validateField(data.get("pid"), "pid");
    }

}
