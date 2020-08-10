package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.RecipeSqlDAO;
import com.techelevator.model.FormPropertiesDTO;
import com.techelevator.model.Ingredient;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.Type;
import com.techelevator.model.UnitOfMeasure;



@RestController
@CrossOrigin
public class RecipeController {
	
	private RecipeSqlDAO recipeDAO;

	public RecipeController(RecipeSqlDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}
	
	
	// Get Methods
	
	@RequestMapping(path = "users/{username}/myRecipes/{recipeId}", method = RequestMethod.GET)
	public List<RecipeIngredient> getRecipeIngredientsById(@PathVariable Long recipeId) {
		return recipeDAO.findIngredientsByRecipeId(recipeId);
	}
	
	@RequestMapping(path = "users/{username}/myRecipes", method = RequestMethod.GET)
	public List<Recipe> getRecipeByUsername(@PathVariable String username) {
		return recipeDAO.findRecipesByUser(username);
	}
	
	@RequestMapping(path = "users/{username}/myRecipes/types/{type}", method = RequestMethod.GET)
	public List<Recipe> getRecipeByUsernameAndType(@PathVariable String type, @PathVariable String username) {
		return recipeDAO.findRecipesByTypeAndUser(type, username);
	}
	
	@RequestMapping(path = "exploreRecipes", method = RequestMethod.GET)
	public List<Recipe> getAllPublicRecipes() {
		return recipeDAO.findAllPublicRecipes();
	}
	
	@RequestMapping(path = "exploreRecipes/{recipeId}", method = RequestMethod.GET)
	public List<RecipeIngredient> getPublicRecipeIngredientsById(@PathVariable Long recipeId) {
		return recipeDAO.findIngredientsByRecipeId(recipeId);
	}
	
	@RequestMapping(path = "exploreRecipes/types/{type}", method = RequestMethod.GET)
	public List<Recipe> getPublicRecipesByType(@PathVariable String type) {
		return recipeDAO.findPublicRecipesByType(type);
	}
	
	@RequestMapping(path = "users/{username}/myrecipes/addRecipe", method = RequestMethod.POST)
	public void addRecipe(@Valid @RequestBody RecipeDTO newRecipe, @PathVariable String username) {
		recipeDAO.createRecipe(newRecipe, username);
	}
	
	@RequestMapping(path = "users/{username}/myRecipes/addRecipe", method = RequestMethod.GET)
	public FormPropertiesDTO getFormProperties() {
		
		FormPropertiesDTO formProperties = new FormPropertiesDTO();
		formProperties.setIngredients(recipeDAO.findAllIngredients());
		formProperties.setTypes(recipeDAO.findAllRecipeTypes());
		formProperties.setUnits(recipeDAO.findAllUnitsOfMeasure());
		
		return formProperties;
		
	}
	
	
	
	// Create Update Delete Methods
	
	

}
