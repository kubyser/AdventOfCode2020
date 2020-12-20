package kubiks.aoc.tests;

import kubiks.aoc.day19.MessageDecoder;
import kubiks.aoc.utils.FileReaderUtils;
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
        decoder.reduceRules();
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
        decoder.reduceRules();
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

    @Test
    void stringMatchesRulePart2() {
        MessageDecoder decoder = new MessageDecoder(FileReaderUtils.readStringListFromFile("resources/day19_p2_test_input.txt"));
        decoder.reduceRules();
        assertTrue(decoder.stringMatchesRule("bbabbbbaabaabba", "0"));
        assertTrue(decoder.stringMatchesRule("ababaaaaaabaaab", "0"));
        assertTrue(decoder.stringMatchesRule("ababaaaaabbbaba", "0"));
        assertFalse(decoder.stringMatchesRule("aaabbbbbbaaaabaababaabababbabaaabbababababaaa", "0"));
        assertFalse(decoder.stringMatchesRule("bbbababbbbaaaaaaaabbababaaababaabab", "0"));
        assertFalse(decoder.stringMatchesRule("baabbaaaabbaaaababbaababb", "0"));
        assertFalse(decoder.stringMatchesRule("abbbbabbbbaaaababbbbbbaaaababb", "0"));
        assertFalse(decoder.stringMatchesRule("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa", "0"));

        decoder = new MessageDecoder(FileReaderUtils.readStringListFromFile("resources/day19_p2_test_input.txt"));
        decoder.putRule("8: 42 | 42 8");
        decoder.putRule("11: 42 31 | 42 11 31");
        decoder.reduceRules();
        assertTrue(decoder.stringMatchesRule("bbabbbbaabaabba", "0"));
        assertTrue(decoder.stringMatchesRule("ababaaaaaabaaab", "0"));
        assertTrue(decoder.stringMatchesRule("ababaaaaabbbaba", "0"));
        assertTrue(decoder.stringMatchesRule("aaabbbbbbaaaabaababaabababbabaaabbababababaaa", "0"));
        assertTrue(decoder.stringMatchesRule("bbbababbbbaaaaaaaabbababaaababaabab", "0"));
        assertTrue(decoder.stringMatchesRule("baabbaaaabbaaaababbaababb", "0"));
        assertTrue(decoder.stringMatchesRule("abbbbabbbbaaaababbbbbbaaaababb", "0"));
        assertTrue(decoder.stringMatchesRule("aaaabbaabbaaaaaaabbbabbbaaabbaabaaa", "0"));
    }

    @Test
    void stringMatchesRulePart2failing() {
        List<String> rules = new ArrayList<>();
        rules.add("0: 8 11");
        rules.add("8: 42 | 42 8");
        rules.add("11: 42 31 | 42 11 31");
        rules.add("42: \"a\"");
        rules.add("31: \"b\"");
        MessageDecoder decoder = new MessageDecoder(rules);
        assertTrue(decoder.stringMatchesRule("aaaaabb", "0"));
    }


    }