package com.techelevator.dao;


import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

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
	
	@Test
	public void find_recipe_by_id () {
		Recipe recipe = recipeSqlDAO.findRecipeById(1);
		Assert.assertNotNull(recipe);
	}

}
