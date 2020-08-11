package com.techelevator.model;

import java.util.List;

public class MealPlanDTO {
	
	private MealPlan mealPlan;
	
	private List<Recipe> recipeList;

	public MealPlan getMealPlan() {
		return mealPlan;
	}

	public void setMealPlan(MealPlan mealPlan) {
		this.mealPlan = mealPlan;
	}

	public List<Recipe> getRecipeList() {
		return recipeList;
	}

	public void setRecipeList(List<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	
}
