package com.facade;

import java.util.List;

import javax.ejb.Local;

import com.model.Category;
import com.model.Meal;

@Local
public interface MealFascade {

	public abstract void save(Meal meal);

	public abstract Meal update(Meal meal);
	
	public abstract void delete(Meal meal);

	public abstract Meal find(int entityID);

	public abstract List<Meal> findAll();
	
}
