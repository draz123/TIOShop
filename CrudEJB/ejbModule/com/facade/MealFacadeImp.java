package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.MealDAO;
import com.model.Meal;

@Stateless
public class MealFacadeImp implements MealFascade {
	
	@EJB
	private MealDAO mealDAO;
	
	@Override
	public void save(Meal meal) {
		isMealWithAllData(meal);		
		mealDAO.save(meal);
	}

	@Override
	public Meal update(Meal meal) {
		isMealWithAllData(meal);
		
		return mealDAO.update(meal);
	}
	
	@Override
	public void delete(Meal meal) {
		mealDAO.delete(meal);
	}

	@Override
	public Meal find(int entityID) {
		return mealDAO.find(entityID);
	}

	@Override
	public List<Meal> findAll() {
		return mealDAO.findAll();
	}
	
	private void isMealWithAllData(Meal meal){
		boolean hasError = false;
		
		if(meal == null){
			hasError = true;
		}
		
		if (meal.getCategory() == null ){
			hasError = true;
		}
		

		if (hasError){
			throw new IllegalArgumentException("The meal is missing data. Check the name and weight, they should have value.");
		}
	}

	}
