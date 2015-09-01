package fr.cyberbase.util;

import java.util.Calendar;

public class Login {
    private String loginTechId;
    private Calendar maxAge;
    private boolean admin;

    public String getLoginTechId() {
        return loginTechId;
    }

    public Calendar getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Calendar maxAge) {
        this.maxAge = maxAge;
    }

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setLoginTechId(String loginTechId) {
		this.loginTechId = loginTechId;
	}
    
    
}
