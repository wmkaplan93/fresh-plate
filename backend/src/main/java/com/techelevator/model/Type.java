package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class Type {
	
	@NotEmpty
	private long typeId;
	
	@NotEmpty
	private String type;
	
	

	public long getTypeId() {
		return typeId;
	}
	
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
