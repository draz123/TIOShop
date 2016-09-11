package com.facade;

import javax.ejb.Local;

import com.model.Order;
import com.model.User;

@Local
public interface UserFacade {
	public User findUserByEmail(String email);
	public abstract User update(User order);
	
}