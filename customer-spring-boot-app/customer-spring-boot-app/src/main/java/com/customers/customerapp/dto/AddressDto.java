package com.customers.customerapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDto {
	
	// street name validations
	@NotBlank(message = "Η διεύθυνση είναι υποχρεωτική")
	@Size(
	    min = 3,
	    max = 100,
	    message = "Η διεύθυνση πρέπει να έχει από 3 έως 100 χαρακτήρες"
	)
	private String streetName;
	
	// postcode validation
	@NotBlank(message = "Ο ταχυδρομικός κώδικας είναι υποχρεωτικός")
	@Pattern(
	    regexp = "\\d{5}",
	    message = "Ο ταχυδρομικός κώδικας πρέπει να αποτελείται από 5 ψηφία"
	)
	private String postcode;
	
	
	//constructors
	public AddressDto(String streetName, String postcode) {
		
		this.streetName = streetName;
		this.postcode = postcode;
	}

	public AddressDto() {
		
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
