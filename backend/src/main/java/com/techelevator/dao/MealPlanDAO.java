package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

public interface MealPlanDAO {

		void createMealPlan(MealPlan mealPlan);
		
		void updateMealPlan(MealPlan mealPlan, long plan_id);
		
		void addRecipeToPlan(long plan_id, long recipe_id);
		
		void deleteRecipeFromMealPlan(long plan_id, long recipe_id);
		
		void deleteMealPlan(long plan_id);
		
		List<MealPlan> viewAllPlans(String username);
		
		List<MealPlan> findPlansByKeyword(String nameKeyword);
		
		List<RecipeIngredient> getGroceryListByPlanId(long plan_id);
		
		MealPlan findPlanById(long plan_id);
		
		List<Recipe> findAllRecipesByMealPlanId(long plan_id);
		
}
