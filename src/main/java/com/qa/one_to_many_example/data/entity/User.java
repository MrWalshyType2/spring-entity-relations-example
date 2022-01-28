package com.qa.one_to_many_example.data.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
//hibernate requires we also annotate domain entities
// with the @Entity annotation
@Entity
@Table(name = "user") // optional, allows us to specify the tables name
public class User {

	@Id // any class marked with @Entity must have @Id to signify
		// the primary key field
	// this tells hibernate to auto-increment IDs
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Length(min = 1, message = "Names cannot be empty")
	private String forename;
	
	@NotNull
	@Length(min = 1)
	private String surname;
	
	@Max(130)
	@Min(18)
	private Integer age;
	
	// @OneToMany signifies that a User can have many Cars
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE) // deletes all related Car objects if the User is deleted
	private List<Car> cars;

	// for JPA to work, we need an empty
	// default constructor for Hibernate to use
	public User() {
		super();
	}

	public User(String forename, String surname, Integer age) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.age = age;
	}

	public User(long id, String forename, String surname, Integer age) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age, cars, forename, id, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(age, other.age) && Objects.equals(cars, other.cars)
				&& Objects.equals(forename, other.forename) && Objects.equals(id, other.id)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", forename=" + forename + ", surname=" + surname + ", age=" + age + ", cars=" + cars
				+ "]";
	}
	
}
