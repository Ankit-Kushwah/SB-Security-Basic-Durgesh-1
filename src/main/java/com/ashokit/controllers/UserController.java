package com.ashokit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.models.User;
import com.ashokit.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	//all users
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
	}
	
//	Single User
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
//	Add user
	@PostMapping
	public User addUser(@RequestBody User user) {
		return this.userService.addUser(user);
	}
}
