package com.customers.customerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customers.customerapp.dto.CustomerRequestDto;
import com.customers.customerapp.service.CustomerService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	  private final CustomerService customerService;
	  
	  @Autowired
	  public AuthController(CustomerService customerService) {
	        this.customerService = customerService;
	    }
	
	
	  @GetMapping("/login")
	    public String showLoginPage() {
	        return "login";
	    }
	
	  @GetMapping("/register")
	    public String showRegisterPage(Model model) {

		  // δημιουργουμε ενα αδειο CustomerRequestDto καθως το form του jsp περιμενει ενα object τετοιου τυπου οποτε πρεπει να στειλουμε ενα αδειο για να ειναι κενα τα πεδια 
		  CustomerRequestDto customer= new CustomerRequestDto();
	      model.addAttribute("customer", customer	);

	      return "register";
	    }
	
	  @PostMapping("/register")
	    public String registerCustomer( @Valid @ModelAttribute("customer") CustomerRequestDto dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

	        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
	            bindingResult.rejectValue("password", "password.required",
	                "Το password είναι υποχρεωτικό." );
	        }


	        // Ελέγχουμε τη μοναδικότητα μόνο όταν
	        // το αντίστοιχο πεδίο δεν έχει ήδη σφάλμα.
	        if (!bindingResult.hasFieldErrors("username") && customerService.usernameExists(dto.getUsername())) {

	            bindingResult.rejectValue( "username",  "username.duplicate",
	                "Το username χρησιμοποιείται ήδη."
	            );
	        }

	        if (!bindingResult.hasFieldErrors("afm") && customerService.afmExists(dto.getAfm())) {

	            bindingResult.rejectValue("afm", "afm.duplicate",
	                "Το ΑΦΜ χρησιμοποιείται ήδη."
	            );
	        }
	        
	        if (bindingResult.hasErrors()) {
	            return "register";
	        }

	        
	        try {
	            customerService.registerCustomer(dto);

	        } catch (DataAccessException ex) {

	            bindingResult.reject("registration.error",
	                "Η εγγραφή δεν ολοκληρώθηκε. Παρακαλώ δοκιμάστε ξανά."
	            );
	            return "register";
	        }

	        
	        redirectAttributes.addFlashAttribute("successMessage",
	            "Η εγγραφή ολοκληρώθηκε επιτυχώς. Μπορείς να συνδεθείς."
	        );

	        return "redirect:/login";
	    }
	}
	
	


