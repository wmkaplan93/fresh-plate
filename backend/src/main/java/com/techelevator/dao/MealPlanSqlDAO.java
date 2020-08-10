package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;

public class MealPlanSqlDAO implements MealPlanDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public MealPlanSqlDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public void createMealPlan(MealPlan mealPlan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMealPlan(MealPlan mealPlan, long plan_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecipeFromMealPlan(long plan_id, long recipe_id) {
		String sql = "DELETE FROM meal_plans mp" +
						"JOIN plan_recipes pr ON mp.plan_id = pr.plan_id " +
						"WHERE mp.plan_id = ? AND pr.recipe_id = ?";
		
		jdbcTemplate.update(sql, plan_id, recipe_id);
	}

	@Override
	public void addMealToPlan(List<Recipe> recipeList, long plan_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MealPlan> viewAllPlans() {
		List<MealPlan> mealPlan = new ArrayList<MealPlan>();
		
		String sql = "SELECT * FROM meal_plans";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			mealPlan.add(mapRowToMealPlan(results));
		}
		
		return mealPlan;
	}

	@Override
	public MealPlan findPlanByName(String plan_name) {
		MealPlan theMealPlan = null;
		
		String sql = "SELECT plan_id, username, plan_name, plan_description " +
						"FROM meal_plans WHERE plan_name = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, plan_name);
		
		while (results.next()) {
			theMealPlan = mapRowToMealPlan(results);
		}
		
		return theMealPlan;
	}
	
	
	//Helper methods
	
	private MealPlan mapRowToMealPlan(SqlRowSet results) {
		MealPlan plan = new MealPlan();
		
		plan.setPlan_id(results.getLong("plan_id"));
		plan.setUsername(results.getNString("username"));
		plan.setPlan_name(results.getString("plan_name"));
		plan.setPlan_description(results.getString("plan_description"));
		
		return plan;
	}
	

}
