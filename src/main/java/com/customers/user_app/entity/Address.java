package com.customers.user_app.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Address {

	 @NotBlank(message = "Address name is required")
	 @Pattern( regexp = "^[\\p{L}\\p{N}\\s]+$", message = "Address must contain only letters, numbers and spaces" )
	private String name;
	 
	@NotBlank(message = "Postcode is required")
	@Pattern(regexp = "^\\d{5}$",message = "Postcode must contain exactly 5 digits")
	private String postcode;
	
	public Address() {
	}
	
	public Address(String name, String postcode) {
		
		this.name = name;
		

	    
		this.postcode = postcode;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
	
	
}
