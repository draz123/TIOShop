package com.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import com.facade.UserFacade;
import com.model.Ingredient;
import com.model.User;

@SessionScoped
@ManagedBean
public class UserMB {
	private User user;

	@EJB
	private UserFacade userFacade;

	public User getUser() {
		if (user == null) {
			ExternalContext context = FacesContext.getCurrentInstance()
					.getExternalContext();
			String userEmail = context.getUserPrincipal().getName();

			user = userFacade.findUserByEmail(userEmail);
		}

		return user;
	}
	private String userEmail;
	
	public String getUserEmail() {
		if(userEmail==null)
			userEmail=new String();
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String changePasswordByAdmin(){		
		FacesContext fc = FacesContext.getCurrentInstance();
		user = userFacade.findUserByEmail(userEmail);	
		if(user==null){
			FacesMessage msg = new FacesMessage("User with this email doesn't exist");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(oldPassword, msg);
			fc.renderResponse();
			return null;
		}
//		if (!newPassword.equals(repeatedNewPassword)) {
//			FacesMessage msg = new FacesMessage(
//					"Password must match each other");
//			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			fc.addMessage(newPassword, msg);
//			fc.renderResponse();
//			return null;
//		}		
		getUser().setPassword(newPassword);
		userFacade.update(user);
		FacesMessage msg = new FacesMessage("Password changed");
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		fc.addMessage(oldPassword, msg);
		fc.renderResponse();
		return "categories";
	}

	public String newPassword;
	public String oldPassword;
	public String repeatedNewPassword;

	public String getOldPassword() {
		if (oldPassword == null)
			oldPassword = new String();
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String changeAccountPassword() {
		FacesContext fc = FacesContext.getCurrentInstance();
		if (!oldPassword.equals(getUser().getPassword())) {
			FacesMessage msg = new FacesMessage("Old password not match");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(oldPassword, msg);
			fc.renderResponse();
			return null;
		}
//		if (!newPassword.equals(repeatedNewPassword)) {
//			FacesMessage msg = new FacesMessage(
//					"Password must match each other");
//			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			fc.addMessage(newPassword, msg);
//			fc.renderResponse();
//			return null;
//		}
		else{
		getUser().setPassword(newPassword);
		userFacade.update(user);
		return "categories";
		}
	}

	public String getNewPassword() {
		if (newPassword == null)
			newPassword = new String();
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatedNewPassword() {
		if (repeatedNewPassword == null)
			repeatedNewPassword = new String();
		return repeatedNewPassword;
	}

	public void setRepeatedNewPassword(String repeatedNewPassword) {
		this.repeatedNewPassword = repeatedNewPassword;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public boolean isUserAdmin() {
		return getRequest().isUserInRole("ADMIN");
	}

	public boolean isUserClient() {
		return getRequest().isUserInRole("CLIENT");
	}

	public boolean isUserMenuManager() {
		return getRequest().isUserInRole("MENU_MANAGER");
	}

	public boolean isUserCatering() {
		return getRequest().isUserInRole("CATERING");
	}

	public String logOut() {
		getRequest().getSession().invalidate();
		return "logout";
	}

	private HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}


}