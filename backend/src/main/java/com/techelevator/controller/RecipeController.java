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

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;



@RestController
@CrossOrigin
public class RecipeController {
	
	private RecipeSqlDAO recipeDAO;

	public RecipeController(RecipeSqlDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}
	
	@RequestMapping(path = "users/{username}/recipes/{recipeId}", method = RequestMethod.GET)
	public List<RecipeIngredient> getRecipeIngredientsById(@PathVariable Long recipeId) {
		return recipeDAO.getIngredientsByRecipeId(recipeId);
	}
	
	@RequestMapping(path = "users/{username}/recipes", method = RequestMethod.GET)
	public List<Recipe> getRecipeByUsername(@PathVariable String username) {
		return recipeDAO.findRecipesByUser(username);
	}
	
	@RequestMapping(path = "users/{username}/recipes/types/{type}", method = RequestMethod.GET)
	public List<Recipe> getRecipeByUsernameAndType(@PathVariable String type, @PathVariable String username) {
		return recipeDAO.findRecipesByTypeAndUsername(type, username);
	}
	
	@RequestMapping(path = "public/recipes", method = RequestMethod.GET)
	public List<Recipe> getAllPublicRecipes() {
		return recipeDAO.findAllPublicRecipes();
	}
	
	@RequestMapping(path = "public/recipes/{recipeId}", method = RequestMethod.GET)
	public List<RecipeIngredient> getPublicRecipeIngredientsById(@PathVariable Long recipeId) {
		return recipeDAO.getIngredientsByRecipeId(recipeId);
	}
	
	@RequestMapping(path = "public/recipes/types/{type}", method = RequestMethod.GET)
	public List<Recipe> getPublicRecipesByType(@PathVariable String type) {
		return recipeDAO.findPublicRecipesByType(type);
	}
	
	

}
