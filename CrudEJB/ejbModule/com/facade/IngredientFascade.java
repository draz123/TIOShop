package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Category;
import com.model.Ingredient;

@Local
public interface IngredientFascade {

	public abstract void save(Ingredient ingredient);

	public abstract Ingredient update(Ingredient ingredient);
	
	public abstract void delete(Ingredient ingredient);

	public abstract Ingredient find(int entityID);

	public abstract List<Ingredient> findAll();
	
}
