package com.qa.one_to_many_example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.one_to_many_example.data.entity.User;
import com.qa.one_to_many_example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		ResponseEntity<List<User>> users = ResponseEntity.ok(userService.getAll());									 
		return users;
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.create(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/user/" + String.valueOf(savedUser.getId()));
		
		ResponseEntity<User> response = new ResponseEntity<User>(savedUser, headers, HttpStatus.CREATED);
		return response;
	}
}
