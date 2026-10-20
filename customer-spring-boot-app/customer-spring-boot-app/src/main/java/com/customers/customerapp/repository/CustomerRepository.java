package com.customers.customerapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.customers.customerapp.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByAfm(String afm);

    boolean existsByUsernameAndIdNot(String username, Integer id);

    boolean existsByAfmAndIdNot(String afm, Integer id);
}

