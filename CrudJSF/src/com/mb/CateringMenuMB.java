package com.mb;

import com.facade.CategoryFascade;
import com.facade.IngredientFascade;
import com.facade.MealFascade;

import com.model.Category;
import com.model.Ingredient;
import com.model.Meal;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@RequestScoped
public class CateringMenuMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String STAY_IN_THE_SAME_PAGE = null;

	@EJB
	private CategoryFascade categoryFacade;

	@EJB
	private MealFascade mealFacade;

	@EJB
	private IngredientFascade ingredientFacade;

	private Category category;

	private Ingredient ingredient;

	public Ingredient getIngredient() {
		if (ingredient == null) {
			ingredient = new Ingredient();
		}
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	private Meal meal;

	public Meal getMeal() {
		if (meal == null) {
			meal = new Meal();
		}
		return meal;
	}

	public void setMeal(Meal meal) {
		this.meal = meal;
	}

	public Category getCategory() {
		if (category == null) {
			category = new Category();
		}
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String createCategoryStart() {
		return "createCategory";
	}

	private boolean editFlag;

	public boolean isEditFlag() {
		return editFlag;
	}

	public void setEditFlag(boolean editFlag) {
		this.editFlag = editFlag;
	}

	public String createCategoryEnd() {
		try {
			if (editFlag)
				categoryFacade.update(category);
			else
				categoryFacade.save(category);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with category create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return "cateringMenu";
	}

	public String createMealEnd() {
		try {
			duringAddMealStartFlag = false;
			duringAddIngredientsFlag = true;
			meal.setCategoryID(category);
			mealFacade.save(meal);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with meal create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return STAY_IN_THE_SAME_PAGE;
	}

	public boolean isDuringAddMealStartFlag() {
		return duringAddMealStartFlag;
	}

	public void setDuringAddMealStartFlag(boolean duringAddMealStartFlag) {
		this.duringAddMealStartFlag = duringAddMealStartFlag;
	}

	public boolean isAddMealFlag() {
		return duringAddMealStartFlag;
	}

	public void setAddMealFlag(boolean addMealFlag) {
		this.duringAddMealStartFlag = addMealFlag;
	}

	public String addMealConfirm() {
		duringAddMealStartFlag = false;
		duringAddIngredientsFlag = false;
		return "cateringMenu";
	}

	public String listMenu() {
		return "cateringMenu";
	}

	public String deleteCategoryEnd(Category category1) {
		for (Meal meal : mealFacade.findAll()) {
			if (category1.getId() == meal.getCategory().getId()) {
				for (Ingredient ingredient : ingredientFacade.findAll()) {
					if (meal.getId() == ingredient.getMeal().getId()) {
						ingredientFacade.delete(ingredient);
					}
				}
				mealFacade.delete(meal);
			}
		}
		try {
			categoryFacade.delete(category1);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with category delete operation");
			return STAY_IN_THE_SAME_PAGE;
		}

		sendInfoMessageToUser("Operation Complete: Delete");

		return "STAY_IN_THE_SAME_PAGE";
	}

	private void sendInfoMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				message, message));
	}

	private void sendErrorMessageToUser(String message) {
		FacesContext context = getContext();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				message, message));
	}

	private FacesContext getContext() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context;
	}

	public String updateCategoryStart() {

		return "";
	}

	public List<Category> getAllCategories() {
		return categoryFacade.findAll();
	}

	public List<Meal> getAllMeals() {
		return mealFacade.findAll();
	}

	public List<Ingredient> getAllIngredients() {
		return ingredientFacade.findAll();
	}

	private boolean duringAddMealStartFlag;
	private boolean duringAddIngredientsFlag;

	public boolean isDuringAddIngredientsFlag() {
		return duringAddIngredientsFlag;
	}

	public void setDuringAddIngredientsFlag(boolean duringAddIngredientsFlag) {
		this.duringAddIngredientsFlag = duringAddIngredientsFlag;
	}

	public String addMealStart() {
		duringAddMealStartFlag = true;
		return "addMeal";
	}

	public String deleteMealStart(Meal meal) {
		for (Ingredient ingredient : ingredientFacade.findAll()) {
			if (meal.getId() == ingredient.getMeal().getId()) {
				ingredientFacade.delete(ingredient);
			}
		}
		mealFacade.delete(meal);
		return STAY_IN_THE_SAME_PAGE;
	}

	public void addIngredient() {
		ingredientFacade.save(ingredient);
		ingredient = null;
	}

	public String deleteIngredient(Ingredient ingredient) {
		ingredientFacade.delete(ingredient);
		ingredient = null;
		return STAY_IN_THE_SAME_PAGE;
	}

}