package com.techelevator.model;

import java.math.BigDecimal;

public class RecipeIngredient {
	
	private BigDecimal quantity;
	
	private long unitId;
	
	private String unitName;
	
	public RecipeIngredient() {}
	
	public RecipeIngredient(BigDecimal quantity, long unitId, String unitName) {
		this.quantity = quantity;
		this.unitId = unitId;
		this.unitName = unitName;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	

}
