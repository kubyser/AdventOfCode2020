package kubiks.aoc.tests;

import kubiks.aoc.day21.Recipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void getIngredientsAndAllergens() {
        Recipe r = new Recipe("mxmxvkd kfcds sqjhc nhms (contains dairy, fish)");
        assertEquals(4, r.getIngredients().size());
        assertTrue(r.getIngredients().contains("mxmxvkd"));
        assertTrue(r.getIngredients().contains("kfcds"));
        assertTrue(r.getIngredients().contains("sqjhc"));
        assertTrue(r.getIngredients().contains("nhms"));
        assertEquals(2, r.getAllergens().size());
        assertTrue(r.getAllergens().contains("dairy"));
        assertTrue(r.getAllergens().contains("fish"));

        r = new Recipe("sqjhc fvjkl (contains soy)");
        assertEquals(2, r.getIngredients().size());
        assertTrue(r.getIngredients().contains("sqjhc"));
        assertTrue(r.getIngredients().contains("fvjkl"));
        assertEquals(1, r.getAllergens().size());
        assertTrue(r.getAllergens().contains("soy"));

    }

}