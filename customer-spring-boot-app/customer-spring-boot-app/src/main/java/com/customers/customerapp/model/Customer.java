package com.customers.customerapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="birth_date")
	private LocalDate birthdate;
	
	@Column(name="afm", unique=true)
	private String afm;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="moreInformation")
	private String moreInfo;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride (name="name", column=@Column(name="Home_Address_Name")),
		@AttributeOverride (name="postcode", column=@Column(name="Home_Postcode"))
	})
	private Address homeAddress;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="streetName" , column=@Column(name="WorkAddressName")),
		@AttributeOverride(name="postcode", column=@Column(name="WorkAddressPostcode"))
	})
	private Address workAddress;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name="Customer_Work",
					joinColumns=@JoinColumn(name="Customer_Id"),
					inverseJoinColumns=@JoinColumn(name="Work_Id"))
	private ArrayList<Work> worklist;

	
	//getters and setters
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
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

	public ArrayList<Work> getWorklist() {
		return worklist;
	}

	public void setWorklist(ArrayList<Work> worklist) {
		this.worklist = worklist;
	}

	
	//constructors
	public Customer(Integer id, String name, String surname, String gender, LocalDate birthdate, String afm, String username,
			String password, String moreInfo, Address homeAddress, Address workAddress, ArrayList<Work> worklist) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.afm = afm;
		this.username = username;
		this.password = password;
		this.moreInfo = moreInfo;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.worklist = worklist;
	}

	public Customer(String name, String surname, String gender, LocalDate birthdate, String afm, String username,
			String password, String moreInfo, Address homeAddress, Address workAddress, ArrayList<Work> worklist) {
		
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthdate = birthdate;
		this.afm = afm;
		this.username = username;
		this.password = password;
		this.moreInfo = moreInfo;
		this.homeAddress = homeAddress;
		this.workAddress = workAddress;
		this.worklist = worklist;
	}

	public Customer() {
		
	}
	
	
	
	
	

}
