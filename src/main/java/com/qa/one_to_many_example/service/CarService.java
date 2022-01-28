package com.qa.one_to_many_example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.one_to_many_example.data.entity.Car;
import com.qa.one_to_many_example.data.repository.CarRepository;

@Service
public class CarService {

	private CarRepository carRepository;

	@Autowired
	public CarService(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}
	
	public List<Car> getAll() {
		return carRepository.findAll();
	}
	
	public Car create(Car car) {
		return carRepository.save(car);
	}
}
