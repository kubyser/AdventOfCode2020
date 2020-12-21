package kubiks.aoc.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Recipe {
    Set<String> ingredients;
    Set<String> allergens;

    public Recipe(String recipeString) {
        ingredients = new HashSet<>();
        allergens = new HashSet<>();
        String[] split = recipeString.split(" \\(contains ");
        ingredients.addAll(Arrays.asList(split[0].split(" ")));
        allergens.addAll(Arrays.asList(split[1].replace(")", "").split(", ")));
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllergens() {
        return allergens;
    }
}
