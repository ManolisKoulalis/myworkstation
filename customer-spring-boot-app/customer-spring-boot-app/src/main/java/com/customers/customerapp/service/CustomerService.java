package com.customers.customerapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customers.customerapp.dto.AddressDto;
import com.customers.customerapp.dto.CustomerRequestDto;
import com.customers.customerapp.model.Address;
import com.customers.customerapp.model.Customer;
import com.customers.customerapp.repository.CustomerRepository;



@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	public CustomerService(CustomerRepository customerRepository,PasswordEncoder passwordEncoder) {

	    this.customerRepository = customerRepository;
	    this.passwordEncoder = passwordEncoder;
	}
	
	
	 @Transactional(readOnly = true)
	public Customer findCustomerById(Integer id) {
		
		  return customerRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException( "Δεν βρέθηκε πελάτης με ID: " + id));
	}
	

@Transactional
public Integer registerCustomer(CustomerRequestDto dto) {

    // 1. Έλεγχος password
    if (dto.getPassword() == null || dto.getPassword().isBlank()) {
        throw new IllegalArgumentException(
            "Ο κωδικός πρόσβασης είναι υποχρεωτικός"
        );
    }

    // 2. Έλεγχος μοναδικότητας username
    if (customerRepository.existsByUsername(dto.getUsername())) {
        throw new IllegalArgumentException(
            "Το username χρησιμοποιείται ήδη"
        );
    }

    // 3. Έλεγχος μοναδικότητας ΑΦΜ
    if (customerRepository.existsByAfm(dto.getAfm())) {
        throw new IllegalArgumentException(
            "Το ΑΦΜ χρησιμοποιείται ήδη"
        );
    }

    // 4. Δημιουργία νέας Entity
    Customer customer = new Customer();

    customer.setName(dto.getName());
    customer.setSurname(dto.getSurname());
    customer.setGender(dto.getGender());
    customer.setAfm(dto.getAfm());
    customer.setBirthdate(dto.getBirthdate());
   
    //μετατρεπω το AddressDTO σε Address καθως αυτο περιμενει το customer entity
    customer.setHomeAddress(toAddress(dto.getHomeAddress()));
    customer.setWorkAddress(toAddress(dto.getWorkAddress()));
   
    customer.setMoreInfo(dto.getMoreInfo());
    customer.setUsername(dto.getUsername());

    // 5. Hash του password
    customer.setPassword(
        passwordEncoder.encode(dto.getPassword())
    );

    // 6. Αποθήκευση στη βάση
    Customer savedCustomer = customerRepository.save(customer);

    // 7. Επιστροφή του νέου ID
    return savedCustomer.getId();
}

	
	

// Μετατροπες απο DTOS σε αντικειμενα Entities

private Address toAddress(AddressDto dto) {

    if (dto == null) {
        throw new IllegalArgumentException(
            "Η διεύθυνση είναι υποχρεωτική"
        );
    }

    Address address = new Address();

    address.setStreetName(dto.getStreetName());
    address.setPostcode(dto.getPostcode());

    return address;
}






}
