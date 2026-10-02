package com.customers.user_app.dto;

import java.time.LocalDate;

import com.customers.user_app.entity.Address;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequest {

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
	@NotBlank(message = "Surname is required")
	@Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;
    
	@NotBlank(message = "Gender is required")
	@Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female or Other")
	private String gender;
    
	@NotNull(message = "Birthdate is required")
	@Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;
    
    @Valid
    @NotNull(message = "Home address is required")
    private Address homeAddress;
    
    @Valid
    @NotNull(message = "Work address is required")
    private Address workAddress;
    
    
    public UserRequest() {
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

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(Address workAddress) {
		this.workAddress = workAddress;
	}
    
    
}
