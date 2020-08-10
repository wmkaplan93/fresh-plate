package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.Type;
import com.techelevator.model.UnitOfMeasure;
import com.techelevator.model.UserRecipe;

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
	public List<Recipe> findRecipeByKeyword(String keyword) {
		List<Recipe> recipes = new ArrayList<>();
		String sql = "SELECT recipe_id, name, description, yield, unit_id, duration, recipe_method, is_public " + 
						"FROM recipes " +
						"WHERE name LIKE %||:?||%";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, keyword);
		while (results.next()) {
			Recipe recipe = mapRowToPublicRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
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
	
	public List<UnitOfMeasure> findAllUnitsOfMeasure() {
		List<UnitOfMeasure> units = new ArrayList<UnitOfMeasure>();
		
		String sql = "SELECT * FROM units_of_measure";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			UnitOfMeasure unit = new UnitOfMeasure();
			unit.setUnitId(results.getLong("unit_id"));
			unit.setUnitName(results.getString("unit_name"));
			units.add(unit);
		}
		return units;
		
	}
	
	public List<Ingredient> findAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		String sql = "SELECT * FROM ingredients";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientId(results.getLong("ingredient_id"));
			ingredient.setIngredient(results.getString("ingredient_name"));
			ingredients.add(ingredient);
		}
		return ingredients;
		
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
			RecipeIngredient ingredient = mapRowToIngredient(results);
			ingredients.add(ingredient);
		} 
		return ingredients;
	}
	
	
	
	// Create Update Delete Methods

	@Override
<<<<<<< HEAD
	public void createRecipe(RecipeDTO newRecipe, String username) {
=======
	public void createRecipe(Recipe recipe, List<RecipeIngredient> recipeIngredients) {
	
		String sql = "INSERT INTO recipes (recipe_id, name, description, yield, unit_id, duration, recipe_method, is_public) "
					+ "VALUES (?, ?, ?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?) , ?, ?, ?)";
>>>>>>> ce8d018fcecb86c1ef4ef7505946d551d358c362
		
		Recipe recipe = new Recipe();
		recipe.setRecipeId(getNextRecipeID());
		recipe.setName(newRecipe.getName());
		recipe.setDescription(newRecipe.getDescription());
		recipe.setYield(newRecipe.getYield());
		recipe.setUnitName(newRecipe.getUnitName());
		recipe.setDuration(newRecipe.getDuration());
		recipe.setRecipeMethod(newRecipe.getRecipeMethod());
		recipe.setPublic(newRecipe.isPublic());
		
		UserRecipe userRecipe = new UserRecipe();
		userRecipe.setUserId(newRecipe.getUserId());
		userRecipe.setRecipeId(newRecipe.getRecipeId());
		userRecipe.setFavorite(newRecipe.isFavorite());
		
		List<RecipeIngredient> recipeIngredients = newRecipe.getIngredientList();
		List<Type> recipeTypes = newRecipe.getTypes();
		
	
		String sqlRecipe = "INSERT INTO recipes (recipe_id, name, description, yield, unit_name, duration, recipe_method, is_public) " +
					"VALUES (?, ?, ?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?) , ?, ?, ?)";
		
		jdbcTemplate.update(sqlRecipe, recipe.getRecipeId(), recipe.getName(), recipe.getDescription(), recipe.getYield(), recipe.getUnitName(),recipe.getDuration(), recipe.getRecipeMethod(), recipe.isPublic());
		
		String sqlUser = "INSERT INTO user_recipes (user_id, recipe_id, is_favorite) " +
					"VALUES ((SELECT user_id FROM users WHERE username = ?), ?, ?)";
		jdbcTemplate.update(sqlUser, userRecipe.getUserId(), userRecipe.isFavorite());
		
		for( Type recipeType : recipeTypes) {
			String sqlTypes = "INSERT INTO recipe_types (recipe_id, type_id) " +
					"Values(?, (SELECT type_id FROM types WHERE type = recipeType.get))";
			jdbcTemplate.update(sqlTypes, recipe.getRecipeId(), recipeType.getType());
		}
		for ( RecipeIngredient ingredient : recipeIngredients) {
			String sqlIngredients = "INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id) " +
									"VALUES (?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?))";
			jdbcTemplate.update(sqlIngredients, recipe.getRecipeId(), ingredient.getQuantity(), ingredient.getUnitName(), ingredient.getIngredient());	
		}
	}

	@Override
	public void updateRecipe(Recipe recipe, long recipeId) {
		String sql = "UPDATE recipes SET name = ?, description = ?, yield = ?, unit_id = (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), duration = ?, recipe_method = ?, is_public = ? "
						+ "WHERE recipe_id = ?";
		
		jdbcTemplate.update(sql, recipe.getName(), recipe.getDescription(), recipe.getYield(), recipe.getUnitName(),recipe.getDuration(), recipe.getRecipeMethod(), recipe.isPublic());	
	}

	@Override
	public void deleteRecipe(long recipeId) {
		String sql = "DELETE FROM recipes WHERE recipe_id = ?";
		
		jdbcTemplate.update(sql, recipeId);
	}
	
	@Override
	public void createIngredient(RecipeIngredient recipeIngredient) {
		String ingredientSql = "INSERT INTO recipe_ingredients (quantity, unit_name, ingredient_name) " +
				"VALUES (?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?))";
		
		jdbcTemplate.update(ingredientSql, recipeIngredient.getQuantity(), recipeIngredient.getUnitName(), recipeIngredient.getIngredient());
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
	
	private RecipeIngredient mapRowToIngredient(SqlRowSet results) {
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
