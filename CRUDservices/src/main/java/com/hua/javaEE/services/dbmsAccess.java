package com.hua.javaEE.services;

import java.nio.charset.Charset;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
//import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;



@Path("/directory")
public class dbmsAccess {
	
	
	
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain ; charset=UTF-8")
	@Path("/getUser/{username : ([^/]+?)?}")
	public Response searchUser(@PathParam("username") String name) {
	 
     String msg="";	
     Connection conn = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	 int status=0;
	 ArrayList<User> backup=new ArrayList<>();
     
     try {
 		Class.forName("com.mysql.jdbc.Driver");
 	} catch (ClassNotFoundException e) {
 		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}
     
     try {
 		
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carloandb","javaee", "javaee");

 	} catch (SQLException e) {
 	   return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}

    String sql="SELECT * FROM `users` WHERE username = '"+name+"'";
    
    try {
		  ps = conn.prepareStatement(sql);
		  //ps.setInt(1,user.getAge());
		  //ps.setString(1,name);
		  rs = ps.executeQuery(sql);
		  
		  while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				int roleid = Integer.parseInt(rs.getString("role_id"));
				int userid = Integer.parseInt(rs.getString("user_id"));
				backup.add(new User(username,password,email,roleid,userid));
			}
      
	} catch (SQLException e) {
		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
	} catch (Exception ex) {
	    return Response.status(200).entity(new String(ex.toString().getBytes(Charset.forName("UTF-8")))).build();
	} finally {
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			  return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
			}
		}
  }
    
      if (backup.size() >= 1) {
    
      for (User temp : backup) {
    	 msg=temp.getUsername()+","+temp.getPassword()+","+temp.getEmail()+","+temp.getRoleid()+","+temp.getUserid()+"\n"; 
      }
      } else
    	msg="User with username "+name+" not found";
    
		// return Response.ok(msg).build();
	  return Response.status(200).entity(new String(msg.getBytes(Charset.forName("UTF-8")))).build();
    }
	
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain ; charset=UTF-8")
	@Path("/getUserPassword/{username : ([^/]+?)?}")
	public Response searchUserPassword(@PathParam("username") String name) {
	 
     String msg="";	
     Connection conn = null;
	 PreparedStatement ps = null;
	 ResultSet rs = null;
	 int status=0;
	 ArrayList<String> backup=new ArrayList<>();
     
     try {
 		Class.forName("com.mysql.jdbc.Driver");
 	} catch (ClassNotFoundException e) {
 		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}
     
     try {
 		
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carloandb","javaee", "javaee");

 	} catch (SQLException e) {
 	   return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}

    String sql="SELECT password FROM `users` WHERE username = '"+name+"'";
    
    try {
		  ps = conn.prepareStatement(sql);
		  //ps.setInt(1,user.getAge());
		  //ps.setString(1,name);
		  rs = ps.executeQuery(sql);
		  
		  while (rs.next()) {
				String password = rs.getString("password");
				backup.add(password);
			}
      
	} catch (SQLException e) {
		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
	} catch (Exception ex) {
	    return Response.status(200).entity(new String(ex.toString().getBytes(Charset.forName("UTF-8")))).build();
	} finally {
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			  return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
			}
		}
  }
    
      if (backup.size() >= 1) {
    
      for (String temp : backup) {
    	 msg=temp+"\n"; 
      }
      } else
    	msg="User with username "+name+" not found";
    
		// return Response.ok(msg).build();
	  return Response.status(200).entity(new String(msg.getBytes(Charset.forName("UTF-8")))).build();
    }
	
	
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain ; charset=UTF-8")
	@Path("/DeleteUser/{userid : ([^/]+?)?}")
	public Response deleteUserByID(@PathParam("userid") String id) {
	 
     String msg="";	
     Connection conn = null;
	 PreparedStatement ps = null;
	 int status=0;
	 ArrayList<String> backup=new ArrayList<>();
     
     try {
 		Class.forName("com.mysql.jdbc.Driver");
 	} catch (ClassNotFoundException e) {
 		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}
     
     try {
 		
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carloandb","javaee", "javaee");

 	} catch (SQLException e) {
 	   return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}

    String sql="DELETE FROM `users` WHERE user_id = "+id;
    
    try {
		  ps = conn.prepareStatement(sql);
		 // ps.setInt(1,Integer.parseInt(id));
		  //ps.setString(1,name);
		  status=ps.executeUpdate(sql);
		  
		 
      
	} catch (SQLException e) {
		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
	} catch (Exception ex) {
	    return Response.status(200).entity(new String(ex.toString().getBytes(Charset.forName("UTF-8")))).build();
	} finally {
		
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
			  return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
			}
		}
  }
    
     
    if (status == 1) {
		msg="User"+" with user id:"+id+ " Deleted Successfully";
		return Response.status(200).entity(new String(msg.getBytes(Charset.forName("UTF-8")))).build();	
		
	} else {
       msg="User"+" with user id:"+id+" not found";
	  return Response.status(200).entity(new String(msg.getBytes(Charset.forName("UTF-8")))).build();
		
	}
    
 }
	
	
	
	
	
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain ; charset=UTF-8")
	@Path("/addUser/{username}/{password}/{email}/{role_id  : ([^/]+?)?}")
	public Response testServiceStatus1(@PathParam("username") String username,@PathParam("password") String pass,@PathParam("email") String email,@PathParam("role_id") String roleid) {
	 
     //String msg="";	
     Connection conn = null;
	 PreparedStatement ps = null;
	 int update = 0;
	 
     
     try {
 		Class.forName("com.mysql.jdbc.Driver");
 	} catch (ClassNotFoundException e) {
 		return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}
     
     try {
 		
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/carloandb","javaee", "javaee");

 	} catch (SQLException e) {
 	   return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
 	}

    String sql="INSERT INTO users"+"(username, password, email, role_id) VALUES"+" (?, ?, ?, ?)";
    Password password=new Password();	
		try {
			  ps = conn.prepareStatement(sql);
			  //ps.setInt(1,user.getAge());
			  ps.setString(1,username);
			  String encryptedPassword = password.hashPassword(pass);
			  ps.setString(2,encryptedPassword);
			  ps.setString(3,email);
			  ps.setInt(4,Integer.parseInt(roleid));
	          update=ps.executeUpdate();
	        
		} catch (SQLException e) {
			return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
		} catch (Exception ex) {
		    return Response.status(200).entity(new String(ex.toString().getBytes(Charset.forName("UTF-8")))).build();
		} finally {
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
				  return Response.status(200).entity(new String(e.toString().getBytes(Charset.forName("UTF-8")))).build();
				}
			}
	    }
     
     
		if (update == 1) {
			
			return Response.status(200).entity(new String("User Added Successfully".getBytes(Charset.forName("UTF-8")))).build();	
			
		} else {
		  
		  return Response.status(200).entity(new String("User not added".getBytes(Charset.forName("UTF-8")))).build();
			
		}
	
    }
	
	


}