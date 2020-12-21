package kubiks.aoc.tests;

import kubiks.aoc.day21.Day21;
import kubiks.aoc.utils.FileReaderUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {

    List<String> getTestData() {
        List<String> data = new ArrayList<>();
        data.add("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)");
        data.add("trh fvjkl sbzzf mxmxvkd (contains dairy)");
        data.add("sqjhc fvjkl (contains soy)");
        data.add("sqjhc mxmxvkd sbzzf (contains fish)");
        return data;
    }

    @Test
    void readRecipes() {
        Day21 day21 = new Day21(getTestData());
        assertEquals(4, day21.getRecipes().size());
        assertTrue(day21.getAllergensToIngredients().get("dairy").contains("mxmxvkd"));
        assertFalse(day21.getAllergensToIngredients().get("dairy").contains("kfcds"));
        assertFalse(day21.getAllergensToIngredients().get("dairy").contains("trh"));
        assertTrue(day21.getAllergensToIngredients().get("soy").contains("sqjhc"));
        assertTrue(day21.getAllergensToIngredients().get("soy").contains("fvjkl"));
    }

    @Test
    void getIngredientsWithoutAllergens() {
        Day21 day21 = new Day21(getTestData());
        Set<String> t = day21.getIngredientsWithoutAllergens();
        assertEquals(4, t.size());
        assertTrue(t.contains("kfcds"));
        assertTrue(t.contains("nhms"));
        assertTrue(t.contains("sbzzf"));
        assertTrue(t.contains("trh"));
    }

    @Test
    void countOccurrencesOfSafeIngredients() {
        Day21 day21 = new Day21(getTestData());
        assertEquals(5, day21.countOccurrencesOfSafeIngredients());
    }

    @Test
    void day21Part1() {
        Day21 day21 = new Day21(FileReaderUtils.readStringListFromFile("resources/day21_input.txt"));
        assertEquals(2412, day21.countOccurrencesOfSafeIngredients());
    }

    @Test
    void getSortedListOfDangerousIngredients() {
        Day21 day21 = new Day21(getTestData());
        assertEquals("mxmxvkd,sqjhc,fvjkl", day21.getSortedListOfDangerousIngredients());
    }

}