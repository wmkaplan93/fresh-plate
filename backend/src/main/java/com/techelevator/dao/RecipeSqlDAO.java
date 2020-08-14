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
		
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
				"FROM recipes " + 
				"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
				"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " +
				"WHERE username = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}

	@Override
	public List <Recipe> findFavoriteRecipesByUser(String userName) {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername" + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
						"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " +  
						"WHERE username = ? " + 
						"AND is_favorite = true;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userName);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public List<Recipe> findRecipesByTypeAndUser(String type, String userName) {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
						"JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id " + 
						"JOIN types ON recipe_types.type_id = types.type_id " + 
						"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " + 
						"WHERE type_name = ? " + 
						"AND username = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type, userName);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			recipes.add(recipe);	
		} 
		return recipes;
	}
	
	@Override
	public List <Recipe> findAllPublicRecipes() {
		List <Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
						"WHERE is_public = true";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public List<Recipe> findPublicRecipesByType(String type) {
		List<Recipe> recipes = new ArrayList<Recipe>();
		
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
						"JOIN recipe_types ON recipes.recipe_id = recipe_types.recipe_id " + 
						"JOIN types ON recipe_types.type_id = types.type_id " + 
						"WHERE type_name = ? " + 
						"AND is_public = true;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, type);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
			recipes.add(recipe);
		}
		return recipes;
	}
	
	@Override
	public Recipe findRecipeById(long recipeId) {
		Recipe theRecipe = null;
		
		String sql = "SELECT recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " + 
						"FROM recipes " + 
						"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " + 
						"WHERE recipe_id = ?;";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
		
		while (results.next()) {
			 theRecipe = mapRowToRecipe(results);
		}
		return theRecipe;
	}
	
	public boolean findIsFavorite(String username, Long recipeId) {
		boolean isFavorite = false;
		String sql = "SELECT is_favorite FROM user_recipes WHERE username = ? AND recipe_id = ?";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username, recipeId);
		if (results.next()) {
			isFavorite = results.getBoolean("is_favorite");
		}
		return isFavorite;
	}
	
	@Override
	public List<Recipe> findRecipeByKeyword(String keyword, String username) {
		List<Recipe> recipes = new ArrayList<>();
		String sql = "SELECT recipes.recipe_id, recipe_name, description, yield_amount, unit_name, duration, recipe_method, is_public, ownername " +
				"FROM recipes " +
				"JOIN units_of_measure ON recipes.yield_unit_id = units_of_measure.unit_id " +
				"JOIN user_recipes ON recipes.recipe_id = user_recipes.recipe_id " +
				"WHERE recipe_name LIKE CONCAT('%',?,'%') " +
				"AND (username = ? OR is_public = true)";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, keyword.toLowerCase(), username);
		while (results.next()) {
			Recipe recipe = mapRowToRecipe(results);
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
		
		String sql = "SELECT * FROM ingredients ORDER BY ingredient_name";
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			Ingredient ingredient = new Ingredient();
			ingredient.setIngredientId(results.getLong("ingredient_id"));
			ingredient.setIngredientName(results.getString("ingredient_name"));
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
	public void createRecipe(RecipeDTO newRecipe) {
		
		
		newRecipe.setRecipeId(getNextRecipeID());
		
		List<RecipeIngredient> recipeIngredients = newRecipe.getIngredientList();
		List<Type> recipeTypes = newRecipe.getTypeList();
		
	
		String sqlRecipe = "INSERT INTO recipes (recipe_id, recipe_name, description, yield_amount, yield_unit_id, duration, recipe_method, is_public, ownername) " +
					"VALUES (?, ?, ?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), ?, ?, ?, ?)";
		
		jdbcTemplate.update(sqlRecipe, newRecipe.getRecipeId(), newRecipe.getName().toLowerCase(), newRecipe.getDescription(), newRecipe.getYieldAmount(), newRecipe.getYieldUnit(), newRecipe.getDuration(), newRecipe.getRecipeMethod(), newRecipe.isPublic(), newRecipe.getOwnername());
		
		String sqlUser = "INSERT INTO user_recipes (username, recipe_id, is_favorite) " +
					"VALUES (?, ?, ?)";
		jdbcTemplate.update(sqlUser, newRecipe.getUsername(), newRecipe.getRecipeId(), newRecipe.isFavorite());
		
		for(Type recipeType : recipeTypes) {
			String sqlTypes = "INSERT INTO recipe_types (recipe_id, type_id) " +
					"Values(?, (SELECT type_id FROM types WHERE type_name = ?))";
			jdbcTemplate.update(sqlTypes, newRecipe.getRecipeId(), recipeType.getType());
		}
		for (RecipeIngredient ingredient : recipeIngredients) {
			String sqlIngredients = "INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id) " +
									"VALUES (?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?))";
			jdbcTemplate.update(sqlIngredients, newRecipe.getRecipeId(), ingredient.getQuantity(), ingredient.getUnitName().toLowerCase(), ingredient.getIngredientName());	
		}
	}
	
	public void addRecipeToMyRecipes (Recipe recipe) {
		String sql = "INSERT INTO user_recipes (username, recipe_id, is_favorite) VALUES (?, ?, true)";
		
		jdbcTemplate.update(sql, recipe.getOwnername(), recipe.getRecipeId());
	}

//	@Override
//	public void updateRecipe(RecipeDTO recipeDTO, long recipeId) {
//		
//		Recipe recipe = parseRecipeFromDTO(recipeDTO);
//		UserRecipe userRecipe = parseUserRecipeFromDTO(recipeDTO);
//		List<RecipeIngredient> recipeIngredients = recipeDTO.getIngredientList();
//		List<Type> recipeTypes = recipeDTO.getTypeList();
//
//		String sql = "UPDATE recipes SET recipe_name = ?, description = ?, yield_amount = ?, yield_unit_id = (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), duration = ?, recipe_method = ?, is_public = ?, ownername = ?"
//						+ "WHERE recipe_id = ?";
//		
//		jdbcTemplate.update(sql, recipe.getName(), recipe.getDescription(), recipe.getYieldAmount(), recipe.getYieldUnit(), recipe.getDuration(), recipe.getRecipeMethod(), recipe.isPublic(), recipe.getOwnername());	
//		
//		String sqlUser = "INSERT INTO user_recipes (username, recipe_id, is_favorite) " +
//				"VALUES (?, ?, ?)";
//		jdbcTemplate.update(sqlUser, userRecipe.getUsername(), userRecipe.isFavorite());
//		
//		for(Type recipeType : recipeTypes) {
//			String sqlTypes = "INSERT INTO recipe_types (recipe_id, type_id) " +
//					"Values(?, (SELECT type_id FROM types WHERE type_name = ?))";
//			jdbcTemplate.update(sqlTypes, recipe.getRecipeId(), recipeType.getType());
//		}
//		for (RecipeIngredient ingredient : recipeIngredients) {
//			String sqlIngredients = "INSERT INTO recipe_ingredients (recipe_id, quantity, unit_id, ingredient_id) " +
//									"VALUES (?, ?, (SELECT unit_id FROM units_of_measure WHERE unit_name = ?), (SELECT ingredient_id FROM ingredients WHERE ingredient_name = ?))";
//			jdbcTemplate.update(sqlIngredients, recipe.getRecipeId(), ingredient.getQuantity(), ingredient.getUnitName(), ingredient.getIngredientName());	
//		}
//		
//	}

	@Override
	public void deleteRecipeFromUserRecipes(Recipe recipe) {
		String sql = "DELETE FROM user_recipes WHERE recipe_id = ? AND username = ?";
		
		jdbcTemplate.update(sql, recipe.getRecipeId(), recipe.getOwnername());
	}
	
//	@Override
//	public void createIngredient(Ingredient ingredient) {
//		String ingredientSql = "INSERT INTO ingredients (ingredient_id, ingredient_name) " +
//				"VALUES (?)";
//		
//		jdbcTemplate.update(ingredientSql, ingredient.getingredientName());
//	}
	
	
	
	// Private Helper Methods
	
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
		type.setType(results.getString("type_name"));
		
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
	
	private Recipe parseRecipeFromDTO(RecipeDTO dto) {
		Recipe recipe = new Recipe();
		
		recipe.setRecipeId(dto.getRecipeId());
		recipe.setName(dto.getName());
		recipe.setDescription(dto.getDescription());
		recipe.setYieldAmount(dto.getYieldAmount());
		recipe.setYieldUnit(dto.getYieldUnit());
		recipe.setDuration(dto.getDuration());
		recipe.setRecipeMethod(dto.getRecipeMethod());
		recipe.setPublic(dto.isPublic());
		recipe.setOwnername(dto.getOwnername());
		
		return recipe;
	}
	
}
