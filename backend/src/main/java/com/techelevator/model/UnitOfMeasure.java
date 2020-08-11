package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class UnitOfMeasure {
	
	@NotEmpty
	private long unitId;
	
	@NotEmpty
	private String unitName;

	
	
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
