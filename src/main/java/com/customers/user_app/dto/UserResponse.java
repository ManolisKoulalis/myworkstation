package com.customers.user_app.dto;



import java.time.LocalDate;

import com.customers.user_app.entity.Address;

public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String gender;
    private LocalDate birthdate;
    private Address homeAddress;
    private Address workAddress;

    

	public UserResponse(Long id, String name, String surname, String gender, LocalDate birthdate, Address work_address,
			Address home_address) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public String getGender() {
		return gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public Address getWorkAddress() {
		return workAddress;
	}
	
	
}