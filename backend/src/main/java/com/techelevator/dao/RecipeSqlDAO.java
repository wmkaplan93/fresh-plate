package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.RecipeType;

@Service
public class RecipeSqlDAO implements RecipeDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public RecipeSqlDAO (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	
	// Get Methods

	@Override
	public List <Recipe> findRecipesByUser(String userName) {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " + 
						"JOIN users ON user_recipes.user_id = users.user_id " + 
						"WHERE username = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
		while (results.next()) {
			Recipe recipe = mapRowToUserRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}

	@Override
	public List <Recipe> findFavoriteRecipesByUser(String userName) {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " + 
						"JOIN users ON user_recipes.user_id = users.user_id " + 
						"WHERE username = ? " + 
						"AND is_favorite = true;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
		while (results.next()) {
			Recipe recipe = mapRowToUserRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public List<Recipe> findRecipesByTypeAndUser(String type, String userName) {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public, is_favorite " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id " + 
						"JOIN types ON recipe_types.type_id = types.type_id " + 
						"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " + 
						"JOIN users ON user_recipes.user_id = users.user_id " + 
						"WHERE type = ? " + 
						"AND username = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type, userName);
		while (results.next()) {
			Recipe recipe = mapRowToUserRecipe(results);
			recipes.add(recipe);	
		} 
		return recipes;
	}
	
	@Override
	public List <Recipe> findAllPublicRecipes() {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"WHERE is_public = true";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while (results.next()) {
			Recipe recipe = mapRowToPublicRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public List<Recipe> findPublicRecipesByType(String type) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id " + 
						"JOIN types ON recipe_types.type_id = types.type_id " + 
						"WHERE type = ? " + 
						"AND is_public = true;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type);
		while (results.next()) {
			Recipe recipe = mapRowToPublicRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public Recipe findRecipeById(long recipeId) {
		Recipe theRecipe = null;
		
		String sql = "SELECT recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.unit_id = units_of_measure.unit_id " + 
						"WHERE recipe_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		
		while (results.next()) {
			 theRecipe = mapRowToPublicRecipe(results);
		}
		return theRecipe;
	}
	
	@Override
	public Recipe findRecipeByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<RecipeType> findRecipeTypes(long recipeId) {
		List<RecipeType> typeList = new ArrayList<RecipeType>();
		
		String sql = "SELECT type " + 
						"FROM types " + 
						"JOIN recipe_types ON types.type_id = recipe_types.type_id " + 
						"WHERE recipe_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		while (results.next()) {
			RecipeType type = new RecipeType();
			type.setType(results.getString("type"));
			typeList.add(type);
		} 
		return typeList;
	}
	
	@Override
	public List <RecipeIngredient> findIngredientsByRecipeId(long recipeId) {
		List <RecipeIngredient> ingredients = new ArrayList<RecipeIngredient>();
		
		String sql = "SELECT quantity, unit_name, ingredient_name " + 
						"FROM recipe_ingredients " + 
						"JOIN units_of_measure ON recipe_ingredients.unit_id = units_of_measure.unit_id " + 
						"JOIN ingredients ON recipe_ingredients.ingredient_id = ingredients.ingredient_id " + 
						"WHERE recipe_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		while (results.next()) {
			RecipeIngredient ingredient = mapRowToIngredients(results);
			ingredients.add(ingredient);
		} 
		return ingredients;
	}
	
	
	
	// Create Update Delete Methods

	@Override
	public boolean createRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return false;
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
	
	
	
	// Private Helper Methods
	
	private Recipe mapRowToUserRecipe(SqlRowSet results) {
		Recipe recipe = new Recipe();
		
		recipe.setRecipeId(results.getLong("recipe_id"));
		recipe.setName(results.getString("name"));
		recipe.setDescription(results.getString("description"));
		recipe.setYield(results.getBigDecimal("yield"));
		recipe.setUnitName(results.getString("unit_name"));
		recipe.setDuration(results.getString("duration"));
		recipe.setRecipeMethod(results.getString("recipe_method"));
		recipe.setPublic(results.getBoolean("is_public"));
		recipe.setFavorite(results.getBoolean("is_favorite"));
		
		return recipe;
	}
	
	private Recipe mapRowToPublicRecipe(SqlRowSet results) {
		Recipe recipe = new Recipe();
		
		recipe.setRecipeId(results.getLong("recipe_id"));
		recipe.setName(results.getString("name"));
		recipe.setDescription(results.getString("description"));
		recipe.setYield(results.getBigDecimal("yield"));
		recipe.setUnitName(results.getString("unit_name"));
		recipe.setDuration(results.getString("duration"));
		recipe.setRecipeMethod(results.getString("recipe_method"));
		recipe.setPublic(results.getBoolean("is_public"));
		
		return recipe;
	}
	
	private RecipeIngredient mapRowToIngredients(SqlRowSet results) {
		RecipeIngredient ingredient = new RecipeIngredient();
		
		ingredient.setIngredient(results.getString("ingredient_name"));
		ingredient.setQuantity(results.getBigDecimal("quantity"));
		ingredient.setUnitName(results.getString("unit_name"));
		
		return ingredient;
	}
	
}
