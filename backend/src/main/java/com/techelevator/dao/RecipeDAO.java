package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.Type;

public interface RecipeDAO {
	
	 List<Recipe> findRecipesByUser (String userName);
	 
	 List<Recipe> findFavoriteRecipesByUser (String userName);

	 List<Recipe> findRecipesByTypeAndUser (String type, String userName);
	 
	 List<Recipe> findAllPublicRecipes();
	 
	 List<Recipe> findPublicRecipesByType (String type);
	 
	 Recipe findRecipeById (long recipeId);
	 
	 Recipe findRecipeByKeyword (String keyword);
	 
	 List<Type> findAllRecipeTypes();
	 
	 List<Type> findTypesByRecipeId(long recipeId);
	 
	 List<RecipeIngredient> findIngredientsByRecipeId (long recipeId);
	
	 void createRecipe (Recipe recipe, List<RecipeIngredient> recipeIngredients);
	 
	 boolean updateRecipe (Recipe recipe, long recipeId);
	 
	 boolean deleteRecipe (long recipeId);
	 

}
