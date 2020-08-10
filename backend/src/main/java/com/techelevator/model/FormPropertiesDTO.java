package com.techelevator.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class FormPropertiesDTO {

	@NotEmpty
	private List<Type> types;
	
	@NotEmpty
	private List<Ingredient> ingredients;
	
	@NotEmpty
	private List<UnitOfMeasure> units;
	
	
	// Getters and Setters

	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<UnitOfMeasure> getUnits() {
		return units;
	}

	public void setUnits(List<UnitOfMeasure> units) {
		this.units = units;
	}
	
	

}
