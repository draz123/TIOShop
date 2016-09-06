package com.dao;

import javax.ejb.Stateless;

import com.model.Category;
import com.model.Meal;

@Stateless
public class MealDAO extends GenericDAO<Meal> {

    public MealDAO() {
	super(Meal.class);
    }
    
    public void delete(Meal meal) {
        super.delete(meal.getId(), Meal.class);
    }
}