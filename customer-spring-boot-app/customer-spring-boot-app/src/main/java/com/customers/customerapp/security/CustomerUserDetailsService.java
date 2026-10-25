
package com.customers.customerapp.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.customers.customerapp.model.Customer;
import com.customers.customerapp.repository.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Η δουλειά της είναι να φορτώνει τα στοιχεία ενός χρήστη με βάση το username του.
    //Όταν το Spring Security χρειαστεί να ταυτοποιήσει έναν χρήστη, θα μπορεί να χρησιμοποιήσει τη συγκεκριμένη υλοποίηση για να ανακτήσει τα στοιχεία του από τη βάση
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Customer customer = customerRepository.findByUsername(username).orElseThrow(() ->
                    new UsernameNotFoundException(
                        "Δεν βρέθηκε χρήστης με username: " + username
                    )
                );

        return User.withUsername(customer.getUsername())
                		.password(customer.getPassword())
                		.roles("CUSTOMER")
                		.build();
    }
}

