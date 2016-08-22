package com.model;

import java.util.ArrayList;
import java.util.List;

public class Meal {

	private String name;	
	private int price;
	private List<String> ingredients = new ArrayList<String>();
	
	
	public String getName() {
		return name;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	public Meal(){		
	}
	
	public Meal(String name, int price, List<String> ingredients){
		this.name=name;
		this.price=price;
		this.ingredients=ingredients;
	}
	
}
