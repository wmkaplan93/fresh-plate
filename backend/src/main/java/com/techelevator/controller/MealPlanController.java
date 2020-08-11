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

import com.techelevator.dao.MealPlanSqlDAO;
import com.techelevator.dao.RecipeSqlDAO;
import com.techelevator.model.FormPropertiesDTO;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MealPlan;
import com.techelevator.model.MealPlanDTO;
import com.techelevator.model.Recipe;
import com.techelevator.model.RecipeDTO;
import com.techelevator.model.RecipeIngredient;
import com.techelevator.model.Type;
import com.techelevator.model.UnitOfMeasure;
import com.techelevator.model.UserRecipe;



@RestController
@CrossOrigin
public class MealPlanController {
	
	private MealPlanSqlDAO mpDAO;

	public MealPlanController(MealPlanSqlDAO mpDAO) {
		this.mpDAO = mpDAO;
	}
	
	// Get Request Methods
	
	@RequestMapping(path = "users/{username}/myPlans", method = RequestMethod.GET)
	public List<MealPlan> getMealPlansByUser(@PathVariable String username) {
		return mpDAO.viewAllPlans(username);
	}
	
	@RequestMapping(path = "users/{username}/myPlans/{planId}", method = RequestMethod.GET)
	public MealPlanDTO getMealPlanDetailsByPlanId(@PathVariable long planId) {
		MealPlanDTO mpDTO = new MealPlanDTO();
		mpDTO.setMealPlan(mpDAO.findPlanById(planId));
		mpDTO.setRecipeList(mpDAO.findAllRecipesByMealPlanId(planId));
		return mpDTO;
	}
	
	@RequestMapping(path = "users/{username}/myGroceryList/{planId}", method = RequestMethod.GET)
	public List<RecipeIngredient> getGroceryListByPlanId(@PathVariable long planId) {
		return mpDAO.getGroceryListByPlanId(planId);
	}

}