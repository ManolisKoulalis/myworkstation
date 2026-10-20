package com.customers.customerapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
	
	@Column(name="street_name")
	private String streetName;
	
	@Column(name="postcode")
	private String postcode;
	
	
	
	// constructors
	public Address() {
		
	}
	
	public Address(String streetName, String postcode) {
		
		this.streetName = streetName;
		this.postcode = postcode;
	}
	
	// getters and setters
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	

}
