package com.techelevator.model;

public class MealPlan {
	
	private long plan_id;
	
	private String username;
	
	private String plan_name;
	
	private String plan_description;
	
	
	public MealPlan () {};
	
	public MealPlan (long plan_id, String username, String plan_name, String plan_description) {
		this.plan_id = plan_id;
		this.username = username;
		this.plan_name = plan_name;
		this.plan_description = plan_description;
	}

	
	public long getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(long plan_id) {
		this.plan_id = plan_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getPlan_description() {
		return plan_description;
	}

	public void setPlan_description(String plan_description) {
		this.plan_description = plan_description;
	}
	
	
}
