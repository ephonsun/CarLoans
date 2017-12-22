package com.hua.it.distsystems.dao.impl;

import javax.sql.DataSource;

import com.hua.it.distsystems.dao.DisBookUserDAO;
import com.hua.it.distsystems.model.DisBookUser;
import com.hua.it.distsystems.model.UserCredentials;
import com.hua.it.distsystems.security.Password;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcDisBookUserDAO implements DisBookUserDAO {
	
	private DataSource datasource;
	private DisBookUser user;
	private Password pass;
	
	public JdbcDisBookUserDAO(DisBookUser user) {
		this.user=user;
		pass=new Password();
	}
	
	public JdbcDisBookUserDAO() {
		this.user=null;
		pass=new Password();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.datasource = dataSource;
	}
	
	public void setDisBookUser(DisBookUser user) {
		this.user=user;
	}

	@Override
	public boolean addDisBookUser(DisBookUser user) {
		
		
		int update = 0;
		
		String sql = "INSERT INTO disbookusers"+"(age, email, Password, ProfileName, status, UserName, user_id) VALUES"+" (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			  conn = this.datasource.getConnection();
			  ps = conn.prepareStatement(sql);
			  ps.setInt(1,user.getAge());
			  ps.setString(2,user.getEmail());
	          String encryptedPassword = this.pass.hashPassword(user.getPassword());
	          System.out.println(encryptedPassword);
	          ps.setString(3,encryptedPassword);
	          ps.setString(4,user.getProfileName());
	          ps.setString(5,user.getStatus());
	          ps.setString(6,user.getUserName());
	          String user_idPlaintext=user.getUserName()+"_"+user.getEmail()+"_"+Integer.toString(user.getAge());
	          ps.setString(7,user_idPlaintext);
	          update=ps.executeUpdate();
	        
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				  System.out.println(e.toString());
				}
			}
	    }
		if (update == 1)
		return true;
		else
	    return false;
	}

	@Override
	public int checkUniqueUser(DisBookUser user) {
		
		//2 username exists , 3 email exists , 1 ok
		String sql = "SELECT UserName, email FROM disbookusers";
		int returnvalue=1;
		Connection conn = null;
		PreparedStatement ps = null;
		String userName=null;
		String email=null;
		
		try {
			conn = this.datasource.getConnection();
			ps = conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {

				  userName = rs.getString("UserName");
				  email = rs.getString("email");
                  if (userName.equals(user.getUserName())) {
                	  returnvalue=2;
                	  break;
                  }
                  if (email.equals(user.getEmail())) {
                	  returnvalue=3;
                	  break;
                  }
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		return returnvalue;
		
	}

	@Override
	public String getDisBookUserProfileName(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT ProfileName FROM disbookusers WHERE UserName = "+"'"+username+"'";
		Connection conn = null;
		PreparedStatement ps = null;
		String profilename=null;
		
		try {
			conn = this.datasource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {

				profilename = rs.getString("ProfileName");
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
        
	   if (profilename != null)
		   return profilename;
	   else 
		   return null;
	}

	@Override
	public ArrayList<String> searchProfileNames(String searchString) {
		// TODO Auto-generated method stub
		
		ArrayList<String> temp=new ArrayList<String>();
		
		String sql = "SELECT ProfileName FROM disbookusers WHERE ProfileName LIKE "+"'"+searchString+"%"+"'";
		Connection conn = null;
		PreparedStatement ps = null;
		String buffer=null;
		
		try {
			conn = this.datasource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {

				buffer = rs.getString("ProfileName");
				temp.add(buffer);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
				}
			}
			
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		if (!temp.isEmpty())
		return temp;
		else
		return null;
	}
	
}
