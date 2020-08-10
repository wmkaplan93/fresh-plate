package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class RecipeDTO {
	
	@NotEmpty
	private long recipeId;
	
	@NotEmpty
	private String name;
	
	private String description;
	
	@NotEmpty
	private BigDecimal yield;
	
	@NotEmpty
	private String unitName;
	
	@NotEmpty
	private String duration;
	
	@NotEmpty
	private String recipeMethod;
	
	@NotEmpty
	private boolean isPublic;
	
	@NotEmpty
	private List<Type> types;

	@NotEmpty
	private List<RecipeIngredient> ingredientList;
	
	private long userId;
	
	private long ownerId;
	
	private boolean isFavorite;
	
	
	
	
	
	
	//Getters and Setters

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getRecipeMethod() {
		return recipeMethod;
	}

	public void setRecipeMethod(String recipeMethod) {
		this.recipeMethod = recipeMethod;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public List<RecipeIngredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<RecipeIngredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	
	


	
	

}
