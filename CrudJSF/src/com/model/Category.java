package com.model;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private String name;
	private List<Meal> meals = new ArrayList<Meal>();

	public Category(String name, ArrayList<Meal> breakfasts) {
		super();
		this.name = name;
		this.meals = breakfasts;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
}
