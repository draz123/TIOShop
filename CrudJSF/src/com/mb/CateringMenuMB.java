package com.mb;

import com.model.Category;
import com.model.Meal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean
@SessionScoped
public class CateringMenuMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Category> categories= new ArrayList<Category>();

	@PostConstruct
	public void init() {
		createDefaultCategories();
	}

	private void createDefaultCategories() {

		List<Meal> breakfasts = new ArrayList<Meal>();
		breakfasts.add(new Meal("Jajecznica z kielbasa", 10, new ArrayList<String>(Arrays.asList("jajka", "maslo", "kielbasa"))));
		breakfasts.add(new Meal("Jedzonko na desce", 5, new ArrayList<String>(Arrays.asList("kielbasa", "chleb", "smalec"))));
		breakfasts.add(new Meal("Owsianka", 3,new ArrayList<String>(Arrays.asList("mleko", "platki"))));
		categories.add(new Category("Dania sniadaniowe", (ArrayList<Meal>) breakfasts));

		List<Meal> suppers = new ArrayList<Meal>();
		suppers.add(new Meal("Sledz dwa smaki", 10, new ArrayList<String>(Arrays.asList("jajka", "maslo", "kielbasa"))));
		suppers.add(new Meal("Carpaccio z lososia", 5, new ArrayList<String>(Arrays.asList("losos", "i", "nie wiem co"))));
		suppers.add(new Meal("Serek wedzony z zurawina", 3, new ArrayList<String>(Arrays.asList("ser wedzony", "zurawina"))));
		categories.add(new Category("Dania sniadaniowe", (ArrayList<Meal>) suppers));

		List<Meal> soups = new ArrayList<Meal>();
		soups.add(new Meal("Rosol domowy", 10, new ArrayList<String>(Arrays.asList("kura", "woda", "makaron"))));
		soups.add(new Meal("Zupa grochowa", 5, new ArrayList<String>(Arrays.asList("woda", "groch"))));;
		soups.add(new Meal("Zurek", 3, new ArrayList<String>(Arrays.asList("mleko", "woda","jajko","kielbasa"))));
		categories.add(new Category("Dania sniadaniowe", (ArrayList<Meal>) soups));

	}
	
	public void addCategory(String name, List<Meal> meals){
		
	}

	public List<Category> getCategories() {
		return categories;
	}

}