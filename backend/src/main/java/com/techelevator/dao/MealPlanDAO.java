package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;

public interface MealPlanDAO {

		void createMealPlan(MealPlan mealPlan);
		
		void updateMealPlan(MealPlan mealPlan, long plan_id);
		
		void addRecipeToPlan(long plan_id, long recipe_id);
		
		void deleteRecipeFromMealPlan(long plan_id, long recipe_id);
		
		void deleteMealPlan(long plan_id);
		
		List<MealPlan> viewAllPlans(String username);
		
		List<MealPlan> findPlansByKeyword(String nameKeyword);
		
		MealPlan findPlanById(long plan_id);
		
		List<Recipe> findAllRecipesByMealPlanId(long plan_id);
		
}
