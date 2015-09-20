package fr.cyberbase.util;

import java.util.Calendar;

public class Login {
    private String loginTechId;
    private Calendar maxAge;
    private String nom;
    private String prenom;
    private Integer siteId;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
    
	
    
}
