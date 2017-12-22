package com.hua.it.distsystems;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.security.MessageDigest;

import com.hua.it.distsystems.dao.impl.JdbcDisBookUserDAO;

import com.hua.it.distsystems.dao.impl.JdbcUserCredentialsDAO;
import com.hua.it.distsystems.model.DisBookUser;
import com.hua.it.distsystems.model.UserCredentials;
import com.hua.it.distsystems.security.Password;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	
	//private UserCredentialsDAO usercheck;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("UserCredentials") @Valid UserCredentials credentials, BindingResult result,Model model) {
	  //  String greetings = "Greetings, Spring MVC!";
	     /*	 if (result.hasErrors()) {
	            return "loginerror";
	        } */
		System.out.println("Usename from form:"+credentials.getUsername());
		System.out.println("Password from form:"+credentials.getPassword());
		UserCredentials temp;
		Password pass;
		JdbcUserCredentialsDAO userData = 
			      (JdbcUserCredentialsDAO)context.getBean("JdbcUserCredentialsDAO");
		
		JdbcDisBookUserDAO DisBookuserData = 
			      (JdbcDisBookUserDAO)context.getBean("JdbcDisBookUserDAO");
	   
	    temp=userData.findUserByName(credentials.getUsername());
	 /*  String encryptedPassword=null;
	   //Create hash value from user provided Password
	   try {
	       //  MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
	       //  messageDigest.update(credentials.getPassword().getBytes());
	      //   encryptedPassword = new String(messageDigest.digest());
	      //   System.out.println("Now encrypted"+encryptedPassword);
	      //   System.out.println("From database:"+temp.getPassword());
	   } catch (Exception ex) {
		   System.out.println(ex.toString());
	   } */
	   if (temp != null) {
		   if (userData.validateUserPassword(credentials.getPassword())) {
			 // System.out.println("Login Mechanism Working");
			  String user=DisBookuserData.getDisBookUserProfileName(credentials.getUsername());
			  model.addAttribute("username",user);
			  return "loggedin";
		   } else
		    return "wrongpassword";
	   } else
	   return "wrongusername";
	} 
	
/*	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("DisBookUser") @Valid DisBookUser user, BindingResult result) {
	 
	//	System.out.println("Age:"+Integer.toString(user.getAge()));
	//	System.out.println("Email:"+user.getEmail());
	//	System.out.println("Password:"+user.getPassword());
	//	System.out.println("Profilename:"+user.getProfileName());	
	//	System.out.println("Status:"+user.getStatus());	
	//	System.out.println("Username:"+user.getUserName());
	//	System.out.println("user id:"+user.getUser_id());
		
	//	JdbcDisBookUserDAO userData = 
			    //  (JdbcDisBookUserDAO)context.getBean("JdbcDisBookUserDAO");	
		
		//int check=userData.checkUniqueUser(user);
		
		if (check == 1) {
			
			if (userData.addDisBookUser(user))
			return "useradded";
			else
		    return "useraddedError";
			
		} else if (check == 2) {
			
			 return "sameusername";
			
		} else if (check == 3) {
			
			return "sameEmail";	
		} 
		
		return "home";
	}  */
	
	
	
	
	
	@RequestMapping("/signup")
	public String signup() {

	   return "register";

	}
	
	@RequestMapping("/logout")
	public String logout() {

	   return "home";

	}
	
	@RequestMapping("/signin")
	public String signin() {

	   return "home";

	}
	
	@RequestMapping("/forgotpassword")
	public String forgotpassword() {

	   return "forgotPassword";

	}
	
/*	@RequestMapping(value="/searchfriends", method=RequestMethod.POST)
    public String handlePost(@ModelAttribute("DisBookUser") @Valid DisBookUser user , BindingResult result,Model model) {
        
      // System.out.println(user.getProfileName());
		JdbcDisBookUserDAO userData = 
			      (JdbcDisBookUserDAO)context.getBean("JdbcDisBookUserDAO");
		ArrayList<String> temporary=null;
		temporary=userData.searchProfileNames(user.getProfileName());
		for(int i=0;i<temporary.size();i++) {
			String temp=(String)temporary.get(i);
			if (temp.equals(user.getUserName())) {
				temporary.remove(i);
				break;
			}
		}
		if (temporary != null) {
			//for(int i=0;i<temporary.size();i++)
			//	System.out.println((String)temporary.get(i));
			model.addAttribute("username",user.getUserName());
			model.addAttribute("profileName", new String());
			model.addAttribute("users",temporary);
			return "searchFriends";
		}
		else
		return "loggedin";
    } */
	
	/*
	 
	  @RequestMapping(value="/test", method=RequestMethod.GET)
    public String handlePost(@RequestParam String action, Model m) {
        if( action.equals("save") ){
            //handle save
         }
         else if( action.equals("renew") ){
            //handle renew
         }
        m.addAttribute("name", "change");
        return "home";
    }
    
    Sto html:
    <form action="<c:url value="/test" />" method="POST">
    <input type="submit" name="action" value="save" />
      i sto button....
      </form>
    
    */
	 
	
	
	
	
	
	
}

