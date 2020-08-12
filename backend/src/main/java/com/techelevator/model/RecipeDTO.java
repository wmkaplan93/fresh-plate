package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class RecipeDTO {
	
	
	private long recipeId;
	
	
	private String name;
	
	private String description;
	
	
	private BigDecimal yieldAmount;
	
	
	private String yieldUnit;
	
	
	private String duration;
	
	
	private String recipeMethod;
	
	
	private boolean isPublic;
	
	
	private List<Type> typeList;

	
	private List<RecipeIngredient> ingredientList;
	
	
	private String username;
	
	private String ownername;
	
	
	private boolean isFavorite;
	
	

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

	public BigDecimal getYieldAmount() {
		return yieldAmount;
	}

	public void setYieldAmount(BigDecimal yieldAmount) {
		this.yieldAmount = yieldAmount;
	}

	public String getYieldUnit() {
		return yieldUnit;
	}

	public void setYieldUnit(String yieldUnit) {
		this.yieldUnit = yieldUnit;
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

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

	public List<RecipeIngredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<RecipeIngredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}
	
}