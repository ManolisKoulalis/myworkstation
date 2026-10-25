package com.customers.customerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customers.customerapp.dto.AddressDto;
import com.customers.customerapp.dto.CustomerRequestDto;
import com.customers.customerapp.dto.CustomerResponseDto;
import com.customers.customerapp.model.Address;
import com.customers.customerapp.model.Customer;
import com.customers.customerapp.repository.CustomerRepository;



@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public CustomerService(CustomerRepository customerRepository,PasswordEncoder passwordEncoder) {

	    this.customerRepository = customerRepository;
	    this.passwordEncoder = passwordEncoder;
	}
	
	
	
			// find customer by id
			@Transactional(readOnly = true)
			public Customer findCustomerById(Integer id) {
				
				  return customerRepository.findById(id)
			                .orElseThrow(() -> new IllegalArgumentException( "Δεν βρέθηκε πελάτης με ID: " + id));
			}
			
			
			// get CustomerResponseDto by id
			@Transactional(readOnly = true)
			public CustomerResponseDto getCustomerById(Integer id) {

			    Customer customer = findCustomerById(id);

			    return toCustomerResponseDto(customer);
			}
	 
	
			//register
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

	

		// update
		@Transactional
		public void updateCustomer( Integer customerId, CustomerRequestDto dto, String currentPassword) {
		
		    // 1. Εύρεση υπάρχοντος πελάτη
		    Customer customer = findCustomerById(customerId);
		
		    // 2. Έλεγχος μοναδικότητας username
		    if (customerRepository.existsByUsernameAndIdNot(dto.getUsername(), customerId)) {
		        throw new IllegalArgumentException(
		            "Το username χρησιμοποιείται ήδη"
		        );
		    }
		
		    // 3. Έλεγχος μοναδικότητας ΑΦΜ
		    if (customerRepository.existsByAfmAndIdNot(dto.getAfm(), customerId)) {
		        throw new IllegalArgumentException(
		            "Το ΑΦΜ χρησιμοποιείται ήδη"
		        );
		    }
		
		    
		    // 4. Ενημέρωση προσωπικών στοιχείων
		    customer.setName(dto.getName());
		    customer.setSurname(dto.getSurname());
		    customer.setGender(dto.getGender());
		    customer.setAfm(dto.getAfm());
		    customer.setBirthdate(dto.getBirthdate());
		    customer.setMoreInfo(dto.getMoreInfo());
		    customer.setUsername(dto.getUsername());
		
		    // 5. Ενημέρωση διευθύνσεων
		    customer.setHomeAddress(toAddress(dto.getHomeAddress()));
		
		    customer.setWorkAddress(toAddress(dto.getWorkAddress()));
		
		    // 6. Προαιρετική αλλαγή password
		    if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
		
		        if (currentPassword == null || !passwordEncoder.matches(currentPassword, customer.getPassword())) {
		            throw new IllegalArgumentException(
		                "Ο τρέχων κωδικός είναι λανθασμένος"
		            );
		        }
		
		        customer.setPassword(passwordEncoder.encode(dto.getPassword())
		        );
		    }
		
		    // Δεν χρειάζεται απαραίτητα save().
		    // Η Entity είναι managed μέσα στο transaction.
		}

			
		@Transactional(readOnly = true)
		public List<CustomerResponseDto> getAllCustomers() {

		    List<Customer> customers = customerRepository.findAll();

		    return customers.stream().map(customer ->toCustomerResponseDto(customer)).toList();
		}

		@Transactional
		public void deleteCustomer(Integer customerId) {

		    Customer customer = findCustomerById(customerId);

		    customerRepository.delete(customer);
		}	
			
			
		@Transactional(readOnly = true)
		public boolean usernameExists(String username) {
		    return customerRepository.existsByUsername(username);
		}

		@Transactional(readOnly = true)
		public boolean afmExists(String afm) {
		    return customerRepository.existsByAfm(afm);
		}	
			
			
		@Transactional(readOnly = true)
		public CustomerResponseDto getCustomerByUsername(String username) {

		    Customer customer = customerRepository.findByUsername(username)
		            .orElseThrow(() -> new IllegalArgumentException(
		                    "Δεν βρέθηκε πελάτης με username: " + username
		            ));

		    return toCustomerResponseDto(customer);
		}	
			
			
			
			
			

// Μετατροπες απο DTOS σε αντικειμενα Entities και αντιστροφα

		
// μετατροπη AddressDto to Address		
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



// μετατροπη Address to AddressDto
private AddressDto toAddressDto(Address address) {

    if (address == null) {
        return null;
    }

    AddressDto dto = new AddressDto();

    dto.setStreetName(address.getStreetName());
    dto.setPostcode(address.getPostcode());

    return dto;
}


// μετατροπη Customer to Response
private CustomerResponseDto toCustomerResponseDto(Customer customer) {

    CustomerResponseDto dto = new CustomerResponseDto();

    dto.setId(customer.getId());
    dto.setName(customer.getName());
    dto.setSurname(customer.getSurname());
    dto.setGender(customer.getGender());
    dto.setAfm(customer.getAfm());
    dto.setBirthdate(customer.getBirthdate());
    dto.setMoreInfo(customer.getMoreInfo());
    dto.setUsername(customer.getUsername());

    dto.setHomeAddress(toAddressDto(customer.getHomeAddress()) );

    dto.setWorkAddress(toAddressDto(customer.getWorkAddress()));

    return dto;
}




}
