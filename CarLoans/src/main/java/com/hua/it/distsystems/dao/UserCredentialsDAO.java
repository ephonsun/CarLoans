package com.hua.it.distsystems.dao;

import com.hua.it.distsystems.model.UserCredentials;

public interface UserCredentialsDAO {
   
	public UserCredentials findUserByName(String username);
	public boolean validateUserPassword(String password);
	
}
