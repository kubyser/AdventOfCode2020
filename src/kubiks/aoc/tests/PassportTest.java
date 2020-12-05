package kubiks.aoc.tests;

import kubiks.aoc.day4.Passport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportTest {

    @Test
    void passportTest() {
        Passport passport = new Passport("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        assertEquals("gry", passport.getValue("ecl"));
        assertEquals("860033327", passport.getValue("pid"));
        assertEquals("2020", passport.getValue("eyr"));
        assertEquals("#fffffd", passport.getValue("hcl"));
        assertEquals("1937", passport.getValue("byr"));
        assertEquals("2017", passport.getValue("iyr"));
        assertEquals("147", passport.getValue("cid"));
        assertEquals("183cm", passport.getValue("hgt"));
    }

    @Test
    void areAllFieldsExceptCidPresent() {
        Passport passport = new Passport("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm");
        assertTrue(passport.areAllFieldsExceptCidPresent());
        passport = new Passport("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929");
        assertFalse(passport.areAllFieldsExceptCidPresent());
        passport = new Passport("hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm");
        assertTrue(passport.areAllFieldsExceptCidPresent());
        passport = new Passport("hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in");
        assertFalse(passport.areAllFieldsExceptCidPresent());
    }

    @Test
    void validateByr() {
        assertTrue(Passport.validateField("2002", "byr"));
        assertFalse(Passport.validateField("2003", "byr"));
        assertFalse(Passport.validateField("02002", "byr"));
        assertFalse(Passport.validateField("2002a", "byr"));
    }

    @Test
    void validateHgt() {
        assertTrue(Passport.validateField("60in", "hgt"));
        assertTrue(Passport.validateField("190cm", "hgt"));
        assertFalse(Passport.validateField("190in", "hgt"));
        assertFalse(Passport.validateField("cm190", "hgt"));
        assertFalse(Passport.validateField("190cm1", "hgt"));
    }

    @Test
    void validateHcl() {
        assertTrue(Passport.validateField("#123abc", "hcl"));
        assertFalse(Passport.validateField("#123abz", "hcl"));
        assertFalse(Passport.validateField("123abc", "hcl"));
    }

    @Test
    void validateEcl() {
        assertTrue(Passport.validateField("brn", "ecl"));
        assertFalse(Passport.validateField("wat", "ecl"));
    }

    @Test
    void validatePid() {
        assertTrue(Passport.validateField("000000001", "pid"));
        assertFalse(Passport.validateField("0123456789", "pid"));
    }

    @Test
    void validateAllFieldsExceptCid() {
        Passport passport = new Passport("pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 " +
                "hcl:#623a2f");
        assertTrue(passport.validateAllFieldsExceptCid());
        passport = new Passport("eyr:2029 ecl:blu cid:129 byr:1989 " +
                "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm");
        assertTrue(passport.validateAllFieldsExceptCid());
        passport = new Passport("hcl:#888785 " +
                "hgt:164cm byr:2001 iyr:2015 cid:88 " +
                "pid:545766238 ecl:hzl " +
                "eyr:2022");
        assertTrue(passport.validateAllFieldsExceptCid());
        passport = new Passport("iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719");
        assertTrue(passport.validateAllFieldsExceptCid());

        passport = new Passport("eyr:1972 cid:100 " +
                "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926");
        assertFalse(passport.validateAllFieldsExceptCid());
        passport = new Passport("iyr:2019 " +
                "hcl:#602927 eyr:1967 hgt:170cm " +
                "ecl:grn pid:012533040 byr:1946");
        assertFalse(passport.validateAllFieldsExceptCid());
        passport = new Passport("hcl:dab227 iyr:2012 " +
                "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277");
        assertFalse(passport.validateAllFieldsExceptCid());
        passport = new Passport("hgt:59cm ecl:zzz " +
                "eyr:2038 hcl:74454a iyr:2023 " +
                "pid:3556412378 byr:2007");
        assertFalse(passport.validateAllFieldsExceptCid());
    }
}