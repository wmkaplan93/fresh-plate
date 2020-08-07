package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.Type;

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
	public List<Type> findTypesByRecipeId(long recipeId) {
		List<Type> typeList = new ArrayList<Type>();
		
		String sql = "SELECT * " + 
						"FROM types " + 
						"JOIN recipe_types ON types.type_id = recipe_types.type_id " + 
						"WHERE recipe_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		while (results.next()) {
			typeList.add(mapRowToType(results));
		} 
		return typeList;
	}
	
	@Override
	public List<Type> findAllRecipeTypes() {
		List<Type> typeList = new ArrayList<Type>();
		
		String sql = "SELECT * FROM types";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			typeList.add(mapRowToType(results));
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
	public void createRecipe(Recipe recipe, List<RecipeIngredient> recipeIngredients) {

		String sql = "INSERT INTO recipes (recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public) "

					+ "VALUES (?, ?, ?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?) , ?, ?, ?)";

		recipe.setRecipeId(getNextRecipeID());

		jdbcTemplate.update(sql, recipe.getRecipeId(), recipe.getName(), recipe.getDescription(), recipe.getYield(), recipe.getUnitName(),recipe.getDuration(), recipe.getRecipeMethod(), recipe.isPublic());

		for ( RecipeIngredient ingredient : recipeIngredients) {

			String ingredientSql = "INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id) " +

									"VALUES (?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?))";

			jdbcTemplate.update(ingredientSql, recipe.getRecipeId(), ingredient.getQuantity(), ingredient.getUnitName(), ingredient.getIngredient());
			
		}
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
	
	private Type mapRowToType(SqlRowSet results) {
		Type type = new Type();
		type.setTypeId(results.getLong("type_id"));
		type.setType(results.getString("type"));
		
		return type;
	}
	
	private long getNextRecipeID() {

		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('recipes_recipe_id_seq')");

		if (nextIdResult.next()) {

			return nextIdResult.getLong(1);

		}

		else {

			throw new RuntimeException("Something went wrong while getting an id for the new recipe");

		}

	}

	

	private long getNextIngredientID() {

		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('ingredients_ingredient_id_seq')");

		if (nextIdResult.next()) {

			return nextIdResult.getLong(1);

		}

		else {

			throw new RuntimeException("Something went wrong while getting an id for the new ingredient");

		}

	}


	
}
