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

import com.qa.one_to_many_example.data.entity.Car;
import com.qa.one_to_many_example.data.entity.User;
import com.qa.one_to_many_example.service.CarService;
import com.qa.one_to_many_example.service.UserService;

@RestController
@RequestMapping("/car")
public class CarController {

	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping
	public ResponseEntity<List<Car>> getCars() {
		ResponseEntity<List<Car>> cars = ResponseEntity.ok(carService.getAll());									 
		return cars;
	}
	
	@PostMapping
	public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
		Car savedCar = carService.create(car);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/car/" + String.valueOf(savedCar.getId()));
		
		ResponseEntity<Car> response = new ResponseEntity<Car>(savedCar, headers, HttpStatus.CREATED);
		return response;
	}
}
