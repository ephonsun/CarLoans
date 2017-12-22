package com.hua.it.distsystems.dao;

import java.util.ArrayList;

import com.hua.it.distsystems.model.DisBookUser;

public interface DisBookUserDAO {
	
	public boolean addDisBookUser(DisBookUser user);
	public int checkUniqueUser(DisBookUser user);
	public String getDisBookUserProfileName(String username);
	public ArrayList<String> searchProfileNames(String searchString);

}
