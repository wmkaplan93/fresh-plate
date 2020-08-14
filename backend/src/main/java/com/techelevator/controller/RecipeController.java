
package com.techelevator.controller;

import java.util.ArrayList;
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
import com.techelevator.model.UserRecipe;



@RestController
@CrossOrigin
public class RecipeController {
	
	private RecipeSqlDAO recipeDAO;

	public RecipeController(RecipeSqlDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}
	
	
	// Get Methods
	
//	@RequestMapping(path = "/exploreRecipes/{recipeId}", method = RequestMethod.GET)
//	public RecipeDTO getRecipeDTOByRecipeId(@PathVariable long recipeId) {
//		
//		Recipe recipe = recipeDAO.findRecipeById(recipeId);
//		List<RecipeIngredient> ingredientList = recipeDAO.findIngredientsByRecipeId(recipeId);
//		List<Type> typeList = recipeDAO.findTypesByRecipeId(recipeId);
//		RecipeDTO dto = buildRecipeDTO(recipe, ingredientList, typeList);
////		dto.setFavorite(recipeDAO.findIsFavorite(username, recipeId));
//		
//		return dto;
//	}
	
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
	public RecipeDTO getPublicRecipeDTOByRecipeId(@PathVariable Long recipeId) {
		
		Recipe recipe = recipeDAO.findRecipeById(recipeId);
		List<RecipeIngredient> ingredientList = recipeDAO.findIngredientsByRecipeId(recipeId);
		List<Type> typeList = recipeDAO.findTypesByRecipeId(recipeId);
		RecipeDTO dto = buildRecipeDTO(recipe, ingredientList, typeList);
		
		return dto;
	}
	
	@RequestMapping(path = "exploreRecipes/types/{type}", method = RequestMethod.GET)
	public List<Recipe> getPublicRecipesByType(@PathVariable String type) {
		return recipeDAO.findPublicRecipesByType(type);
	}
	
	@RequestMapping(path = "users/{username}/addRecipe", method = RequestMethod.GET)
	public FormPropertiesDTO getFormProperties() {
		
		FormPropertiesDTO formProperties = new FormPropertiesDTO();
		formProperties.setIngredients(recipeDAO.findAllIngredients());
		formProperties.setTypes(recipeDAO.findAllRecipeTypes());
		formProperties.setUnits(recipeDAO.findAllUnitsOfMeasure());
		
		return formProperties;
		
	}
	
	@RequestMapping(path = "/searchRecipes/filter", method = RequestMethod.GET)
	public List<Recipe> filterByName(@RequestParam(required = true) String name) {
		List<Recipe> filteredRecipes = new ArrayList<>();
		filteredRecipes = recipeDAO.findRecipeByKeyword(name);
		return filteredRecipes;
	}
	
	
	
	// Create Update Delete Methods
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "users/{username}/addRecipe", method = RequestMethod.POST)
	public void addRecipe(@RequestBody RecipeDTO newRecipe) {
		recipeDAO.createRecipe(newRecipe);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/exploreRecipes", method = RequestMethod.PUT)
	public void addRecipeToMyRecipes(@RequestBody Recipe recipe) {
		recipeDAO.addRecipeToMyRecipes(recipe);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/users/{username}/myRecipes", method = RequestMethod.POST)
	public void deleteRecipeFromUsersRecipes(@RequestBody Recipe recipe) {
		recipeDAO.deleteRecipeFromUserRecipes(recipe);
	}
	
	
	
	private RecipeDTO buildRecipeDTO(Recipe recipe, List<RecipeIngredient> ingredientList, List<Type> typeList) {
		RecipeDTO recipeDTO = new RecipeDTO();
		
		recipeDTO.setRecipeId(recipe.getRecipeId());
		recipeDTO.setName(recipe.getName());
		recipeDTO.setDescription(recipe.getDescription());
		recipeDTO.setYieldAmount(recipe.getYieldAmount());
		recipeDTO.setYieldUnit(recipe.getYieldUnit());
		recipeDTO.setDuration(recipe.getDuration());
		recipeDTO.setRecipeMethod(recipe.getRecipeMethod());
		recipeDTO.setPublic(recipe.isPublic());
		recipeDTO.setOwnername(recipe.getOwnername());
		recipeDTO.setTypeList(typeList);
		recipeDTO.setIngredientList(ingredientList);
		
		return recipeDTO;
		
	}

}