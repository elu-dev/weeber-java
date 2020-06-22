package elu.jsf.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import elu.db.UserQueries;

@ManagedBean(name="login")
@SessionScoped
public class LoginBean implements Serializable {
private static final long serialVersionUID = 1094801825228386363L;
	
	private String pwd;
	private String user;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public String validateUsernamePassword() {
		user = null;
		user = UserQueries.validate(email, pwd);
		if (user != null) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "feed";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "home";
		}
	}
	
	public String registerUser() {
		if (UserQueries.register(user, email, pwd)) {
			return "feed";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"ERROR adding user to the DB",
							"When in trouble or in doubt, run in circles, scream and shout"));
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "home";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "home";
	}
	
}
