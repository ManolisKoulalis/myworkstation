package com.customers.customerapp.dto;


import java.time.LocalDate;

import com.customers.customerapp.model.Address;

public class CustomerResponseDto {

    private Integer id;

    private String name;
    private String surname;
    private String gender;
    private String afm;
    private LocalDate birthdate;

    private Address homeAddress;
    private Address workAddress;

    private String moreInfo;
    private String username;

    public CustomerResponseDto() {
    }

    
    
    public CustomerResponseDto(String name, String surname, String gender, String afm, LocalDate birthdate,
			Address homeAddress, Address workAddress, String moreInfo, String username) {
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.afm = afm;
		this.birthdate = birthdate;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.moreInfo = moreInfo;
		this.username = username;
	}



	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
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

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

	

