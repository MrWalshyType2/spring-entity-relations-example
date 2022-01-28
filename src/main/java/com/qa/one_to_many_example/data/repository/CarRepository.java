package com.qa.one_to_many_example.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.one_to_many_example.data.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
