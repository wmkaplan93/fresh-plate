package com.techelevator.model;

import java.math.BigDecimal;

public class UserRecipe {

	private String username;
	
	private boolean isFavorite;
	
	private long recipeId;
	
	private String name;
	
	private String description;
	
	private BigDecimal yieldAmount;
	
	private String yieldUnit;
	
	private String duration;
	
	private String recipeMethod;
	
	private boolean isPublic;
	
	private String ownername;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
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

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	
}
	
	


	