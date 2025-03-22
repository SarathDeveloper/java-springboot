package com.pys.course.admin.web.dto;



import java.util.ArrayList;
import java.util.List;


public class User {
	private String username;
	private String password;
	private String mobileNumber;
	private boolean enabled;
	
	 private List<Role> authorities = new ArrayList<Role>();
	 
	public void addAuthorities(List<Role> roles)
	 {
		 authorities.addAll(roles);
	 }
	/**
	 * @return the authorities
	 */
	public List<Role> getAuthorities() {
		return authorities;
	}
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

}
