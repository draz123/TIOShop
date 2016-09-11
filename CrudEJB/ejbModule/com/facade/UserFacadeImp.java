package com.facade;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.dao.UserDAO;
import com.model.User;

@Stateless
public class UserFacadeImp implements UserFacade {

	@EJB 
	private UserDAO userDAO;
	
	public User findUserByEmail(String email) {
		return userDAO.findUserByEmail(email);
	}
	
	@Override
	public User update(User user) {
		isUserWithAllData(user);
		
		return userDAO.update(user);
	}
	
	private void isUserWithAllData(User user){
		boolean hasError = false;
		
		if(user == null){
			hasError = true;
		}
		
		if(user.getPassword()==null){
			hasError = true;
		}
		
		if (hasError){
			throw new IllegalArgumentException("The user is missing data. Check the password.");
		}
	}
}