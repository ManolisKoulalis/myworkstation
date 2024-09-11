package SImpleWebProjectApplication.model;

import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name="addresses")
public class Address {
	
	private String name;
	private String postcode;
	
	public Address(String name, String postcode) {
		
		this.name = name;
		this.postcode = postcode;
	}

	public Address() {
		
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
