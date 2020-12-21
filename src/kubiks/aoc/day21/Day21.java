package kubiks.aoc.day21;

import kubiks.aoc.utils.FileReaderUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 {
    List<Recipe> recipes;
    Map<String, Set<String>> allergensToIngredients;

    public Day21(List<String> data) {
        recipes = new ArrayList<>();
        allergensToIngredients = new HashMap<>();
        for (String s: data) {
            Recipe recipe = new Recipe(s);
            recipes.add(recipe);
            for (String allergen: recipe.getAllergens()) {
                if (allergensToIngredients.containsKey(allergen)) {
                    allergensToIngredients.get(allergen).retainAll(recipe.getIngredients());
                } else {
                    allergensToIngredients.put(allergen, new HashSet<>(recipe.getIngredients()));
                }
            }
        }
    }

    public Set<String> getIngredientsWithoutAllergens() {
        Set<String> allIngredients = recipes.stream().map(e -> e.getIngredients()).flatMap(Collection::stream).collect(Collectors.toSet());
        Set<String> allAllergicIngredients = allergensToIngredients.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
        allIngredients.removeAll(allAllergicIngredients);
        return allIngredients;
    }

    public int countOccurrencesOfSafeIngredients() {
        Set<String> safeIngredients = getIngredientsWithoutAllergens();
        int countSafeIngredients = recipes.stream().map((e -> (int)e.getIngredients().stream().filter(i -> safeIngredients.contains(i)).count())).reduce(0, Integer::sum);
        return countSafeIngredients;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public Map<String, Set<String>> getAllergensToIngredients() {
        return allergensToIngredients;
    }

    public String getSortedListOfDangerousIngredients() {
        List<String[]> confirmedIngredientsToAllergens = new ArrayList<>();
        Set<String> allergensToProcess = new HashSet<>(allergensToIngredients.keySet());
        while (!allergensToProcess.isEmpty()) {
            for (String allergen : allergensToProcess) {
                if (allergensToIngredients.get(allergen).size() == 1) {
                    String ingredient = allergensToIngredients.get(allergen).iterator().next();
                    allergensToProcess.remove(allergen);
                    String[] confirmedIngredientToAllergen = new String[2];
                    confirmedIngredientToAllergen[0] = ingredient;
                    confirmedIngredientToAllergen[1] = allergen;
                    confirmedIngredientsToAllergens.add(confirmedIngredientToAllergen);
                    allergensToIngredients.keySet().stream().filter(a -> !a.equals(allergen)).forEach(a -> allergensToIngredients.get(a).remove(ingredient));
                    break;
                }
            }
        }
        confirmedIngredientsToAllergens.sort(Comparator.comparing(a -> a[1]));
        List<String> sortedListOfIngredients = confirmedIngredientsToAllergens.stream().map(a -> a[0]).collect(Collectors.toList());
        return sortedListOfIngredients.stream().collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        Day21 day21 = new Day21(FileReaderUtils.readStringListFromFile("resources/day21_input.txt"));
        int answer = day21.countOccurrencesOfSafeIngredients();
        System.out.format("Number of safe ingredients in all recipes: %d\n", answer);
        String answerPart2 = day21.getSortedListOfDangerousIngredients();
        System.out.format("List of dangerous ingredients:\n%s\n", answerPart2);
    }
}
