package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Ingredient;
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
	 
	 List<Recipe> findRecipeByKeyword (String keyword);
	 
	 List<Type> findAllRecipeTypes();
	 
	 List<Type> findTypesByRecipeId(long recipeId);
	 
	 List<RecipeIngredient> findIngredientsByRecipeId (long recipeId);
	
	 void createRecipe (RecipeDTO newRecipe, String username);
	 
//	 void deleteRecipe (long recipeId, String username);
//	 
//	 void createIngredient (Ingredient ingredient);
//
//	 void updateRecipe(RecipeDTO recipeDTO, long recipeId);
	 
	 

}
