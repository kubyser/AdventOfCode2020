package kubiks.aoc.tests;

import kubiks.aoc.day2.PasswordPolicy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPolicyTest {

    @Test
    void stringMatchesOldPolicy() {
        PasswordPolicy p = new PasswordPolicy(1, 3, 'a');
        assertTrue(p.stringMatchesOldPolicy("abcde"));
        p = new PasswordPolicy(1, 3, 'b');
        assertFalse(p.stringMatchesOldPolicy("cdefg"));
        p = new PasswordPolicy(2, 9, 'c');
        assertTrue(p.stringMatchesOldPolicy("ccccccccc"));
    }

    @Test
    void stringMatchesNewPolicy() {
        PasswordPolicy p = new PasswordPolicy(1, 3, 'a');
        assertTrue(p.stringMatchesNewPolicy("abcde"));
        p = new PasswordPolicy(1, 3, 'b');
        assertFalse(p.stringMatchesNewPolicy("cdefg"));
        p = new PasswordPolicy(2, 9, 'c');
        assertFalse(p.stringMatchesNewPolicy("ccccccccc"));
    }
}