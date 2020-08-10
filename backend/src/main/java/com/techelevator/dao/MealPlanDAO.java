package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;

public interface MealPlanDAO {

		void createMealPlan(MealPlan mealPlan);
		
		void updateMealPlan(MealPlan mealPlan, long plan_id);
		
		void deleteRecipeFromMealPlan(long plan_id, long recipe_id);
		
		void addMealToPlan(List<Recipe> recipeList, long plan_id);
	
		List<MealPlan> viewAllPlans();
		
		MealPlan findPlanByName(String plan_name);
		
		
}
