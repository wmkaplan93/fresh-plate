package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

public class RecipeSqlDAO implements RecipeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public RecipeSqlDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Recipe> findAllPublicRecipes() {
		List<Recipe> recipes = new ArrayList<>();
		
		String sql = "SELECT * FROM recipes";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while (results.next()) {
			
			Recipe recipe = mapRowToUserRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}

	@Override
	public Recipe getRecipeById(long recipeId) {
		
		
		String sql = "SELECT * FROM recipes WHERE recipe_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		
		if (results.next()) {
			
			 return mapRowToUserRecipe(results);
		} else {
			throw new RuntimeException("recipe id "+recipeId+" was not found.");
		}
		
	}


	@Override
	public boolean createRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Recipe findRecipeByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRecipe(Recipe recipe, long recipeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecipe(long recipeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Recipe> findRecipesByType(String type, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> findRecipesByUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> findFavoriteRecipesByUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeIngredient> getIngredientsByRecipeId(long recipeId) {
		// TODO Auto-generated method stub
		return null;
	}
	private Recipe mapRowToUserRecipe( SqlRowSet results) {
		Recipe recipe = new Recipe();
		recipe.setRecipeId(results.getLong("recipeId"));
		recipe.setName(results.getString("name"));
		recipe.setDescription(results.getString("description"));
		recipe.setYield(results.getBigDecimal("yield"));
		recipe.setUnitName(results.getString("unitName"));
		recipe.setDuration(results.getString("duration"));
		recipe.setRecipeMethod(results.getString("recipeMethod"));
		recipe.setPublic(results.getBoolean("isPublic"));
		recipe.setFavorite(results.getBoolean("isFavorite"));
		
		return recipe;
	}
	private Recipe mapRowToPublicRecipe( SqlRowSet results) {
		Recipe recipe = new Recipe();
		recipe.setRecipeId(results.getLong("recipeId"));
		recipe.setName(results.getString("name"));
		recipe.setDescription(results.getString("description"));
		recipe.setYield(results.getBigDecimal("yield"));
		recipe.setUnitName(results.getString("unitName"));
		recipe.setDuration(results.getString("duration"));
		recipe.setRecipeMethod(results.getString("recipeMethod"));
		recipe.setPublic(results.getBoolean("isPublic"));
		
		
		return recipe;
	}
	
	

}
