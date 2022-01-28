package com.qa.one_to_many_example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.one_to_many_example.data.entity.User;
import com.qa.one_to_many_example.data.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	public User create(User user) {
		return userRepository.save(user);
	}
}
