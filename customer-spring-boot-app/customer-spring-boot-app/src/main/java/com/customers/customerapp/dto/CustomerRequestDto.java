package com.customers.customerapp.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerRequestDto {

		//name validations
		@NotBlank(message = "Το όνομα είναι υποχρεωτικό")
		@Size(min = 3, max = 30,
		      message = "Το όνομα πρέπει να έχει από 3 έως 30 χαρακτήρες")
		@Pattern(
		    regexp = "^[\\p{IsGreek}a-zA-Z]+$",
		    message = "Το όνομα πρέπει να περιέχει μόνο ελληνικά ή αγγλικά γράμματα"
		)
		private String name;
	
		//surname validations
		@NotBlank(message = "Το επώνυμο είναι υποχρεωτικό")
		@Size(min = 3, max = 30,
		      message = "Το επώνυμο πρέπει να έχει από 3 έως 30 χαρακτήρες")
		@Pattern(
		    regexp = "^[\\p{IsGreek}a-zA-Z]+$",
		    message = "Το επώνυμο πρέπει να περιέχει μόνο ελληνικά ή αγγλικά γράμματα"
		)
		private String surname;
	
		
		// gender validation
		@NotBlank(message = "Το φύλο είναι υποχρεωτικό")
		@Pattern(
		    regexp = "Male|Female|Other",
		    message = "Μη έγκυρη επιλογή φύλου"
		)
	    private String gender;
		
		// αφμ validations
		@NotBlank(message = "Το ΑΦΜ είναι υποχρεωτικό")
		@Pattern(
		    regexp = "\\d{9}",
		    message = "Το ΑΦΜ πρέπει να αποτελείται από 9 ψηφία"
		)
	    private String afm;
	    
		//birthdate validations
	    @NotNull(message = "Η ημερομηνία γέννησης είναι υποχρεωτική")
	    @PastOrPresent(message = "Η ημερομηνία γέννησης δεν μπορεί να είναι μελλοντική")
	    private LocalDate birthdate;
	  
	    //username validations
	    @NotBlank(message = "Το username είναι υποχρεωτικό")
	    @Size(min = 5, max = 30,
	          message = "Το username πρέπει να έχει από 5 έως 30 χαρακτήρες")
	    @Pattern(
	        regexp = "^[a-zA-Z0-9]+(?:[._][a-zA-Z0-9]+)*$",
	        message = "Το username επιτρέπεται να περιέχει λατινικά γράμματα, αριθμούς, τελείες και κάτω παύλες, χωρίς συνεχόμενα ειδικά σύμβολα"
	    )
	    private String username;
	    
	    // Δεν βαζω not blank γιατι στο update θα χρειαζοταν συνεχεια να βαζω τον κωδικο
	    private String password;
	    
	    // address validations με τον not null δηλωνουμε οτι θελουμε να δημιουργειτε αντικειμενο
	    @NotNull(message = "Η διεύθυνση κατοικίας είναι υποχρεωτική")
	    @Valid
	    private AddressDto homeAddress;
	    
	    @NotNull(message = "Η διεύθυνση εργασίας είναι υποχρεωτική")
	    @Valid
	    private AddressDto workAddress;

	    @Size(
	    	    max = 1000,
	    	    message = "Οι επιπλέον πληροφορίες δεν μπορούν να ξεπερνούν τους 1000 χαρακτήρες"
	    	)
	    private String moreInfo;

	    //getters and setters
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

		public AddressDto getHomeAddress() {
			return homeAddress;
		}

		public void setHomeAddress(AddressDto homeAddress) {
			this.homeAddress = homeAddress;
		}

		public AddressDto getWorkAddress() {
			return workAddress;
		}

		public void setWorkAddress(AddressDto workAddress) {
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		// Constructors
		public CustomerRequestDto(String name, String surname, String gender, String afm, LocalDate birthdate,String username,String password,
				AddressDto homeAddress, AddressDto workAddress, String moreInfo) {
			
			this.name = name;
			this.surname = surname;
			this.gender = gender;
			this.afm = afm;
			this.birthdate = birthdate;
			this.username=username;
			this.password=password;
			this.homeAddress = homeAddress;
			this.workAddress = workAddress;
			this.moreInfo = moreInfo;
			
		}

		public CustomerRequestDto() {
			
		}

		
	   
		@AssertTrue(message = "Η ηλικία δεν μπορεί να υπερβαίνει τα 100 έτη")
		public boolean isBirthdateWithinAllowedRange() {

		    if (birthdate == null) {
		        return true;
		    }

		    return !birthdate.isBefore(LocalDate.now().minusYears(100));
		}
	
}
