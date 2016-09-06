package com.facade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.CategoryDAO;
import com.model.Category;

@Stateless
public class CategoryFacadeImp implements CategoryFascade {
	
	@EJB
	private CategoryDAO categoryDAO;
	
	@Override
	public void save(Category category) {
		isCategoryWithAllData(category);		
		categoryDAO.save(category);
	}

	@Override
	public Category update(Category category) {
		isCategoryWithAllData(category);
		
		return categoryDAO.update(category);
	}
	
	@Override
	public void delete(Category category) {
		categoryDAO.delete(category);
	}

	@Override
	public Category find(int entityID) {
		return categoryDAO.find(entityID);
	}

	@Override
	public List<Category> findAll() {
		return categoryDAO.findAll();
	}
	
	private void isCategoryWithAllData(Category category){
		boolean hasError = false;
		
		if(category == null){
			hasError = true;
		}
		
		if (category.getTitle() == null || "".equals(category.getTitle().trim())){
			hasError = true;
		}
		

		if (hasError){
			throw new IllegalArgumentException("The category is missing data. Check the name and weight, they should have value.");
		}
	}

	}
