package com.customers.customerapp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.customers.customerapp.dto.CustomerResponseDto;
import com.customers.customerapp.service.CustomerService;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/profile")
    public String showProfile(Principal principal, Model model) {

        String username = principal.getName();

        CustomerResponseDto customer = customerService.getCustomerByUsername(username);

        model.addAttribute("customer", customer);

        return "profile";
    }
}