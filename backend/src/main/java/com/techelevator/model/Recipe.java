package com.techelevator.model;

import java.math.BigDecimal;

public class Recipe {

	private long recipeId;
	
	private String name;
	
	private String description;
	
	private BigDecimal yield;
	
	private String unitName;
	
	private String duration;
	
	private String recipeMethod;
	
	private boolean isPublic;
	
	private boolean isFavorite;
	
	
	public Recipe () {}
	
	public Recipe( long recipeId, String name, String description, BigDecimal yield, String unitName, String duration, String recipeMethod, boolean isPublic, boolean isFavorite) {
		this.recipeId = recipeId;
		this.name = name;
		this.description = description;
		this.yield = yield;
		this.unitName = unitName;
		this.duration = duration;
		this.recipeMethod = recipeMethod;
		this.isPublic = isPublic;
		this.isFavorite = isFavorite;
	}

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

	
	
}
