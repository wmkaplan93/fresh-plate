package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlan;
import com.techelevator.model.MealPlanDTO;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

public interface MealPlanDAO {

		void createMealPlan(MealPlan mealPlan);
		
		void updateMealPlan(MealPlan mealPlan, long plan_id);
		
		void addRecipeToPlan(MealPlanDTO mealPlanDTO);
		
		void deleteRecipeFromMealPlan(MealPlanDTO dto);
		
		void deleteMealPlan(MealPlan mealPlan);
		
		List<MealPlan> viewAllPlans(String username);
		
		List<MealPlan> findPlansByKeyword(String nameKeyword);
		
		List<RecipeIngredient> getGroceryListByPlanId(long plan_id);
		
		MealPlan findPlanById(long plan_id);
		
		List<Recipe> findAllRecipesByMealPlanId(long plan_id);
		
}
