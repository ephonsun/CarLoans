package com.hua.it.distsystems.model;

public class DisBookUser {
	
	private int age;
	private String email;
	private String Password;
	private String ProfileName;
	private String status;
	private String UserName;
	private String user_id;
	
	public DisBookUser() {
		
	   this.status="unknown";
	   this.user_id="";
	}
	
    public DisBookUser(int Age,String Email,String password,String profilename,String username) {
    	
    	this.age=Age;
    	this.email=Email;
    	this.Password=password;
    	this.ProfileName=profilename;
    	this.status="unknown";
    	this.UserName=username;
    	this.user_id="";
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getProfileName() {
		return ProfileName;
	}

	public void setProfileName(String profileName) {
		ProfileName = profileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
