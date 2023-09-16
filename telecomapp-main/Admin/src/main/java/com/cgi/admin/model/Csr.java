package com.cgi.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Csr {
	
    @Id
    private int csrId;
    private String name;
    private String userName;
    private String password;
    private long phoneNo;
    private String email;
    private String details;
    private boolean isEnabled;
    private boolean isAdmin;

    public Csr() {  
    }
    
    public Csr(int csrId, String name, String userName, String password, long phoneNo, String email, String details, boolean isEnabled, boolean isAdmin) {
        this.csrId = csrId;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
        this.email = email;
        this.isEnabled = isEnabled;
        this.isAdmin = isAdmin;
    }

    public int getCsrId() {
        return csrId;
    }

    public void setCsrId(int csrId) {
        this.csrId = csrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
	@Override
	public String toString() {
		return "CSR [csrId=" + csrId + ", name=" + name + ", userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin + ", isEnabled=" + isEnabled + "]";
	}
}
