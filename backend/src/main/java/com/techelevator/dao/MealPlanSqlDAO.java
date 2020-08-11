package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

@Service
public class MealPlanSqlDAO implements MealPlanDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public MealPlanSqlDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public void createMealPlan(MealPlan mealPlan) {
		mealPlan.setPlan_id(getNextMealPlanID());
		String sql = "INSERT INTO meal_plans (plan_id, username, plan_name, plan_description) " +
						"VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sql, mealPlan.getPlan_id(), mealPlan.getUsername(), mealPlan.getPlan_name(), mealPlan.getPlan_description());
		
		
	}

	@Override
	public void updateMealPlan(MealPlan mealPlan, long plan_id) {
		String sql = "UPDATE meal_plans SET username = ?, plan_name = ?, plan_description = ? WHERE plan_id = ?)";
		jdbcTemplate.update(sql, mealPlan.getUsername(), mealPlan.getPlan_name(), mealPlan.getPlan_description(), plan_id);
		
	}

	@Override
	public void deleteRecipeFromMealPlan(long plan_id, long recipe_id) {
		String sql = "DELETE FROM plan_recipes " +
						"WHERE plan_id = ? AND recipe_id = ?";	
		jdbcTemplate.update(sql, plan_id, recipe_id);
	}

	@Override
	public void addRecipeToPlan(long plan_id, long recipe_id) {
		String sql = "INSERT INTO plan_recipes (plan_id, recipe_id) " +
						"VALUES (?, ?)";
		jdbcTemplate.update(sql, plan_id, recipe_id);
	}

	@Override
	public List<MealPlan> viewAllPlans(String username) {
		List<MealPlan> mealPlans = new ArrayList<MealPlan>();
		
		String sql = "SELECT * FROM meal_plans WHERE username = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
		while (results.next()) {
			mealPlans.add(mapRowToMealPlan(results));
		}
		
		return mealPlans;
	}
	
	@Override
	public void deleteMealPlan(long plan_id) {
		String sql = "DELETE FROM meal_plans WHERE plan_id = ?";
		jdbcTemplate.update(sql, plan_id);
				
	}

	@Override
	public MealPlan findPlanById(long plan_id) {
		MealPlan mealPlan = null;
		String sql = "SELECT * FROM meal_plans WHERE plan_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plan_id);
		if (results.next()) {
			mealPlan = mapRowToMealPlan(results);
		}
		return mealPlan;
	}

	@Override
	public List<MealPlan> findPlansByKeyword(String nameKeyword) {
		List<MealPlan> mealPlans = new ArrayList<MealPlan>();
		
		String sql = "SELECT * FROM meal_plans " +
						"WHERE plan_name LIKE CONCAT('%',?,'%')\"";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, nameKeyword);
		
		while (results.next()) {
			mealPlans.add(mapRowToMealPlan(results));
		}
		
		return mealPlans;
	}
	
	@Override
	public List<Recipe> findAllRecipesByMealPlanId(long plan_id) {
		List<Recipe> recipeList = new ArrayList<Recipe>();
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
				"FROM recipes " + 
				"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
				"JOIN plan_recipes ON recipes.recipe_id = plan_recipes.recipe_id " + 
				"WHERE plan_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plan_id);
		while (results.next()) {
			recipeList.add(mapRowToRecipe(results));
		}
		return recipeList;
	}
	
	@Override
	public List<RecipeIngredient> getGroceryListByPlanId(long plan_id) {
		List<RecipeIngredient> ingredientList = new ArrayList<RecipeIngredient>();
		String sql = "SELECT quantity, unit_name, ingredient_name " + 
				"FROM recipe_ingredients " + 
				"JOIN units_of_measure ON recipe_ingredients.unit_id = units_of_measure.unit_id " + 
				"JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " + 
				"JOIN plan_recipes ON recipe_ingredients.recipe_id = plan_recipes.recipe_id " + 
				"WHERE plan_id = ? " + 
				"ORDER BY ingredient_name;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plan_id);
		while (results.next()) {
			ingredientList.add(mapRowToRecipeIngredient(results));
		}
		return ingredientList;
	}
	
	//Helper methods
	
	private MealPlan mapRowToMealPlan(SqlRowSet results) {
		MealPlan plan = new MealPlan();
		
		plan.setPlan_id(results.getLong("plan_id"));
		plan.setUsername(results.getString("username"));
		plan.setPlan_name(results.getString("plan_name"));
		plan.setPlan_description(results.getString("plan_description"));
		
		return plan;
	}
	
	private Recipe mapRowToRecipe(SqlRowSet results) {
		Recipe recipe = new Recipe();
		
		recipe.setRecipeId(results.getLong("recipe_id"));
		recipe.setName(results.getString("recipe_name"));
		recipe.setDescription(results.getString("description"));
		recipe.setYieldAmount(results.getBigDecimal("yield_amount"));
		recipe.setYieldUnit(results.getString("unit_name"));
		recipe.setDuration(results.getString("duration"));
		recipe.setRecipeMethod(results.getString("recipe_method"));
		recipe.setPublic(results.getBoolean("is_public"));
		recipe.setOwnername(results.getString("ownername"));
		
		return recipe;
	}
	
	private RecipeIngredient mapRowToRecipeIngredient(SqlRowSet results) {
		RecipeIngredient ingredient = new RecipeIngredient();
		
		ingredient.setQuantity(results.getBigDecimal("quantity"));
		ingredient.setUnitName(results.getString("unit_name"));
		ingredient.setIngredient(results.getString("ingredient_name"));
		
		return ingredient;
	}
	
	private long getNextMealPlanID() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('meal_plans_plan_id_seq')");
		if (nextIdResult.next()) {
			return nextIdResult.getLong(1);
		}
		else {
			throw new RuntimeException("Something went wrong while getting an id for the new Meal Plan");
		}
	}

}
