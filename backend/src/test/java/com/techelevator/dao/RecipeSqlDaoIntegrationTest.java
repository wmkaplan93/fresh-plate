package com.techelevator.dao;


import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

public class RecipeSqlDaoIntegrationTest extends DAOIntegrationTest {
	
	private RecipeSqlDAO recipeSqlDAO;
	
	@Before
    public void setup() {
        DataSource dataSource = this.getDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        recipeSqlDAO = new RecipeSqlDAO(jdbcTemplate);
    }
	
	@Test
	public void find_ingredients_by_recipe_id() {
		List <RecipeIngredient> ingredientList = recipeSqlDAO.findIngredientsByRecipeId(1);
		Assert.assertEquals(10, ingredientList.size());
		Assert.assertNotNull(ingredientList);
	}
	
//	@Test
//	public void find_recipe_by_id () {
//		Long testId = 100L;
//		BigDecimal number = new BigDecimal("1");
//		Recipe theRecipe = getRecipe(testId, "Steve", "cake", number, "snack", "0 hours", "preheat", true, true);
//		
//		Recipe results = recipeSqlDAO.findRecipeById(testId);
//		
//		assertNotNull(results);
//		
//		assertRecipesAreEqual(theRecipe, results);
//		
//	}
	
	private Recipe getRecipe (long recipeId, String name, String description, BigDecimal yield, String unitName, String duration, String recipeMethod, boolean isPublic, boolean isFavorite) {
		Recipe theRecipe = new Recipe();
		theRecipe.setRecipeId(recipeId);
		theRecipe.setName(name);
		theRecipe.setDescription(description);
		theRecipe.setYield(yield);
		theRecipe.setUnitName(unitName);
		theRecipe.setDuration(duration);
		theRecipe.setRecipeMethod(recipeMethod);
		theRecipe.setPublic(isPublic);
		theRecipe.setFavorite(isFavorite);
		return theRecipe;
	}
	
	private void assertRecipesAreEqual(Recipe expected, Recipe actual) {
		assertEquals(expected.getRecipeId(), actual.getRecipeId());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getYield(), actual.getYield());
		assertEquals(expected.getUnitName(), actual.getUnitName());
		assertEquals(expected.getDuration(), actual.getDuration());
		assertEquals(expected.getRecipeMethod(), actual.getRecipeMethod());
		assertEquals(expected.isPublic(), actual.isPublic());
		assertEquals(expected.isFavorite(), actual.isFavorite());
	}

}
