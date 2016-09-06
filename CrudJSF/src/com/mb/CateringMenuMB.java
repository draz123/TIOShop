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

	public String createCategoryEnd() {
		try {
			categoryFacade.save(category);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with category create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return "listAllDogs";
	}

	public String createMealEnd() {
		try {
			mealFacade.save(meal);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with meal create operation");
			return STAY_IN_THE_SAME_PAGE;
		}
		sendInfoMessageToUser("Operation Complete: Create");
		return "listAllDogs";
	}

	public String listMenu() {
		return "listAllDogs";
	}

	public String deleteCategoryStart() {
		return "deleteCategory";
	}

	public String deleteCategoryEnd() {
		try {
			categoryFacade.delete(category);
		} catch (EJBException e) {
			sendErrorMessageToUser("Error. Problem with category delete operation");
			return STAY_IN_THE_SAME_PAGE;
		}

		sendInfoMessageToUser("Operation Complete: Delete");

		return "listAllDogs";
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


}