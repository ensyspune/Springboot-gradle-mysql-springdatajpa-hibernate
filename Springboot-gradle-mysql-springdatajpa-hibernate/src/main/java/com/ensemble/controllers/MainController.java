package com.ensemble.controllers;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;

import com.ensemble.dto.MessageDTO;
import com.ensemble.dto.MessageType;
import com.ensemble.models.User;
import com.ensemble.models.UserDao;




@RestController
public class MainController {

	
	/**
	   * Create a new user with an auto-generated id and email and name as passed 
	   * values.
	 */ 
  @RequestMapping(value="/create", method=RequestMethod.POST)
  public  ResponseEntity<User> createUser(@Validated @RequestBody User u){
  userDao.save(u);
  System.out.println("returning User model !!!");   
  return new ResponseEntity<User>(u, HttpStatus.OK);
  }

  
  
  
  
  
  
  
  /**
   * Delete the user with the passed id.
   */
  @RequestMapping(method = RequestMethod.DELETE, value="/{id}")
  public  String deleteUser(@PathVariable("id") long id){
	
	  try {
		  System.out.println("In deleteUser Method");
	      User user = new User(id);
	      userDao.delete(user);
	    }
	    catch (Exception ex) {
	      return "Error deleting the user: " + ex.toString();
	    }
	    return "User succesfully deleted!";
	  
  }
  

 
  @RequestMapping(method = RequestMethod.GET,value = "/getbyid/{id}")
  public User userById(@PathVariable("id") long id) {
	  System.out.println("In userById method............");
      return userDao.findOne(id);// uses the findOne() method inherited from CrudRepository
  }
  
  
  @RequestMapping(method = RequestMethod.GET,value = "/getall")
  public Iterable<User> getAll() {
	  System.out.println("In getAll method............");
      return userDao.findAll();// uses the findOne() method inherited from CrudRepository
  }

  
  
  @RequestMapping(method = RequestMethod.PUT,value = "/update/{id}")
  public String updateUser(@RequestBody User u,@PathVariable("id") long id) {
	  System.out.println("In updateUser method............");
	  try {
	      User user = userDao.findOne(id);
	      user.setEmail(u.getEmail());
	      user.setName(u.getName());
	      user.setMobno(u.getMobno());
	      userDao.save(user);
	    }
	    catch (Exception ex) {
	      return "Error updating the user: " + ex.toString();
	    }
	    return "User succesfully updated!";
	  }      
  
  
  
  
  // Wire the UserDao used inside this controller.
  @Autowired
  private UserDao userDao;


}
