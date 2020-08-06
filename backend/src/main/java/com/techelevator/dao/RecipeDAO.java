package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.RecipeType;

public interface RecipeDAO {
	 List<Recipe> findAllPublicRecipes();
	 
	 Recipe findRecipeById( long recipeId);
	 
	 Recipe findRecipeByKeyword(String keyword);
	 
	 boolean createRecipe(Recipe recipe);
	 
	 boolean updateRecipe(Recipe recipe, long recipeId);
	 
	 boolean deleteRecipe(long recipeId);
	 
	 List<Recipe> findRecipesByType (String type);
	 
	 List<Recipe> findRecipesByUser (String userName);
	 
	 List<Recipe> findFavoriteRecipesByUser (String userName);
	 
	 List<RecipeIngredient> findIngredientsByRecipeId (long recipeId);
	 
	 RecipeType recipeTypes(long recipeId);
	 
	 List<Recipe> findRecipesByTypeAndUsername (String type, String userName);

}
