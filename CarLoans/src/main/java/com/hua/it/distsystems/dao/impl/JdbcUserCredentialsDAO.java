package com.hua.it.distsystems.dao.impl;

import com.hua.it.distsystems.dao.UserCredentialsDAO;
import com.hua.it.distsystems.model.UserCredentials;
import com.hua.it.distsystems.security.Password;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcUserCredentialsDAO implements UserCredentialsDAO {
	
	private DataSource datasource;
	private UserCredentials user;
	private static Password pass;
	
	public JdbcUserCredentialsDAO(UserCredentials user) {
		this.user=user;
		this.pass=new Password();
	}
	
	public JdbcUserCredentialsDAO() {
		this.user=null;
		this.pass=new Password();
	}
	
	public void setDataSource(DataSource dataSource) {
		this.datasource = dataSource;
	}

	@Override
	public UserCredentials findUserByName(String username) {
		
		String sql = "SELECT UserName, user_id, Password FROM disbookusers WHERE UserName = "+"'"+username+"'";
		Connection conn = null;
		PreparedStatement ps = null;
		String userName=null;
		String password=null;
		try {
			conn = this.datasource.getConnection();
			ps = conn.prepareStatement(sql);
			
			ResultSet rs=ps.executeQuery();
			
			while (rs.next()) {

				  userName = rs.getString("UserName");
				  password = rs.getString("Password");
                String user_id = rs.getString("user_id");
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
        if ((userName != null) && (password != null)) {
		  this.user=new UserCredentials(userName,password);
		  return user;
        } else
		return null;
	}

	@Override
	public boolean validateUserPassword(String password) {
		// TODO Auto-generated method stub
		return this.pass.checkPassword(password,this.user.getPassword());
	}
	
	

}
