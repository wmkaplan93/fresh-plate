package com.techelevator.model;

import java.math.BigDecimal;

public class RecipeIngredient {
	
	private BigDecimal quantity;
	
	private String ingredient;
	
	private String unitName;
	
	public RecipeIngredient() {}
	
	public RecipeIngredient(BigDecimal quantity, String ingredient, String unitName) {
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.unitName = unitName;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	

}
