package com.customers.user_app.entity;

import java.time.LocalDate;

import com.customers.user_app.dto.UserResponse;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	

	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birthdate")
	private LocalDate birthdate;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride (name="name", column=@Column(name="Work_Adrress_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Work_Postcode"))
	})
	private Address workAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride (name="name", column=@Column(name="Home_Adrress_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Home_Postcode"))
	})
	private Address homeAddress;

	public User(String name, String surname, String gender, LocalDate birthdate, Address work_address,
			Address home_address) {
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = work_address;
		this.homeAddress = home_address;
	}
	
	public User(Long id,String name, String surname, String gender, LocalDate birthdate, Address work_address,
			Address home_address) {
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.workAddress = work_address;
		this.homeAddress = home_address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Address getWork_address() {
		return workAddress;
	}

	public void setWork_address(Address work_address) {
		this.workAddress = work_address;
	}

	public Address getHome_address() {
		return homeAddress;
	}

	public void setHome_address(Address home_address) {
		this.homeAddress = home_address;
	}

	public User() {
		
	}
	
	
	
	
}
