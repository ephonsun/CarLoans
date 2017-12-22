package com.hua.javaEE.services;

public class User {
	
	private String username;
	private String password;
	private String email;
	private int userid;
	private int roleid;
	
	public User() {
	  this.username="";
	  this.password="";
	  this.roleid=0;
	  this.userid=0;
	}
	
	public User(String name,String pass,String mail,int roleid,int userid) {
	  this.username=name;
	  this.password=pass;
	  this.email=mail;
	  this.userid=userid;
	  this.roleid=roleid;		  
	}
	
	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
	
	

}
