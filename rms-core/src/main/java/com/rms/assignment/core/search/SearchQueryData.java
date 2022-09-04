package com.rms.assignment.core.search;

public class SearchQueryData {
    private final Boolean onlyVeg;
    private final int servings;
    private final String instructions;
    private final String includeIngredients;
    private final String excludeIngredients;

    public SearchQueryData(Boolean onlyVeg, int servings, String instructions, String includeIngredients,
            String excludeIngredients) {
        this.onlyVeg = onlyVeg;
        this.servings = servings;
        this.instructions = instructions;
        this.includeIngredients = includeIngredients;
        this.excludeIngredients = excludeIngredients;
    }

    public Boolean getOnlyVeg() {
        return onlyVeg;
    }

    public int getServings() {
        return servings;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getIncludeIngredient() {
        return includeIngredients;
    }

    public String getExcludeIngredient() {
        return excludeIngredients;
    }

}
