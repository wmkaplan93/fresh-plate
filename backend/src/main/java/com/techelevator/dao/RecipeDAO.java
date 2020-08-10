package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.RecipeType;

public interface RecipeDAO {
	
	 List<Recipe> findRecipesByUser (String userName);
	 
	 List<Recipe> findFavoriteRecipesByUser (String userName);

	 List<Recipe> findRecipesByTypeAndUser (String type, String userName);
	 
	 List<Recipe> findAllPublicRecipes();
	 
	 List<Recipe> findPublicRecipesByType (String type);
	 
	 Recipe findRecipeById (long recipeId);
	 
	 List<Recipe> findRecipeByKeyword (String keyword);
	 
	 List<RecipeType> findRecipeTypes(long recipeId);
	 
	 List<RecipeIngredient> findIngredientsByRecipeId (long recipeId);
	
	 void createRecipe (Recipe recipe, List<RecipeIngredient> recipeIngredients);
	 
	 void updateRecipe (Recipe recipe, long recipeId);
	 
	 void deleteRecipe (long recipeId);
	 
	 void createIngredient (RecipeIngredient recipeIngredient);
	 

}
