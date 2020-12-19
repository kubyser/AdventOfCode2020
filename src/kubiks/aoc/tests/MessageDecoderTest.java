package kubiks.aoc.tests;

import kubiks.aoc.day19.MessageDecoder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageDecoderTest {

    @Test
    void stringMatchesRule() {
        List<String> rules = new ArrayList<>();
        rules.add("0: 1 2");
        rules.add("1: \"a\"");
        rules.add("2: 1 3 | 3 1");
        rules.add("3: \"b\"");
        MessageDecoder decoder = new MessageDecoder(rules);
        assertTrue(decoder.stringMatchesRule("aab", "0"));
        assertTrue(decoder.stringMatchesRule("aba", "0"));
        assertFalse(decoder.stringMatchesRule("bba", "0"));
        assertFalse(decoder.stringMatchesRule("aaa", "0"));
        assertFalse(decoder.stringMatchesRule("ab", "0"));
        assertFalse(decoder.stringMatchesRule("aabb", "0"));
        assertFalse(decoder.stringMatchesRule("baab", "0"));
        assertFalse(decoder.stringMatchesRule("abab", "0"));

        rules.clear();
        rules.add("0: 4 1 5");
        rules.add("1: 2 3 | 3 2");
        rules.add("2: 4 4 | 5 5");
        rules.add("3: 4 5 | 5 4");
        rules.add("4: \"a\"");
        rules.add("5: \"b\"");
        decoder = new MessageDecoder(rules);
        assertTrue(decoder.stringMatchesRule("aaaabb", "0"));
        assertTrue(decoder.stringMatchesRule("aaabab", "0"));
        assertTrue(decoder.stringMatchesRule("abbabb", "0"));
        assertTrue(decoder.stringMatchesRule("abbbab", "0"));
        assertTrue(decoder.stringMatchesRule("aabaab", "0"));
        assertTrue(decoder.stringMatchesRule("aabbbb", "0"));
        assertTrue(decoder.stringMatchesRule("abaaab", "0"));
        assertTrue(decoder.stringMatchesRule("ababbb", "0"));
        assertFalse(decoder.stringMatchesRule("bababa", "0"));
        assertFalse(decoder.stringMatchesRule("aaabbb", "0"));
        assertFalse(decoder.stringMatchesRule("aaaabbb", "0"));
    }
}