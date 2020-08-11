package com.techelevator.model;

import java.math.BigDecimal;

public class RecipeIngredient {
	
	private BigDecimal quantity;
	
	private String ingredientName;
	
	private String unitName;
	
	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredient(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	

}
