package com.customers.customerapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customers.customerapp.dto.CustomerRequestDto;
import com.customers.customerapp.dto.CustomerResponseDto;
import com.customers.customerapp.dto.WorkResponseDto;
import com.customers.customerapp.service.CustomerService;
import com.customers.customerapp.service.WorkService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class CustomerController {

	private final CustomerService customerService;
	private final WorkService workService;

	public CustomerController( CustomerService customerService, WorkService workService) {

	    this.customerService = customerService;
	    this.workService = workService;
	}

    @GetMapping("/customers/profile")
    public String showProfile(Principal principal, Model model) {

        String username = principal.getName();

        CustomerResponseDto customer = customerService.getCustomerByUsername(username);
        List<WorkResponseDto> works = workService.getWorksByCustomerId(customer.getId());

        model.addAttribute("customer", customer);
        model.addAttribute("works", works);

        return "profile";
    }
    
    @GetMapping("/customers/edit")
    public String showEditPage(Principal principal, Model model) {

        String username = principal.getName();

        CustomerRequestDto customer =customerService.getCustomerForEdit(username);

        model.addAttribute("customer", customer);

        return "customer-edit";
    }
    
    

@PostMapping("/customers/edit")
public String updateCustomer(
        @Valid @ModelAttribute("customer") CustomerRequestDto dto,
        BindingResult bindingResult,
        @RequestParam(name = "currentPassword", required = false) String currentPassword,
        Principal principal,
        HttpServletRequest request,
        HttpServletResponse response,
        RedirectAttributes redirectAttributes) {

    String oldUsername = principal.getName();

    CustomerResponseDto customer =
            customerService.getCustomerByUsername(oldUsername);

    Integer customerId = customer.getId();

    // Έλεγχος μοναδικότητας username
    if (!bindingResult.hasFieldErrors("username") && !dto.getUsername().equals(oldUsername) && customerService.usernameExists(dto.getUsername())) {
     
    	bindingResult.rejectValue("username", "duplicateUsername",
                "Το username χρησιμοποιείται ήδη."
        );
    }

    // Έλεγχος μοναδικότητας ΑΦΜ
    if (!bindingResult.hasFieldErrors("afm") && !dto.getAfm().equals(customer.getAfm()) && customerService.afmExists(dto.getAfm())) {

        bindingResult.rejectValue("afm","duplicateAfm",
                "Το ΑΦΜ χρησιμοποιείται ήδη."
        );
    }

    // Αν υπαρχει validation error
    if (bindingResult.hasErrors()) {
        return "customer-edit";
    }

    //Update 
    try {
        customerService.updateCustomer(customerId,dto,currentPassword);
    } catch (IllegalArgumentException e) {

        bindingResult.reject("customerUpdateError",
                e.getMessage()
        );

        return "customer-edit";
    }

    // Αν άλλαξε το username, απαιτείται νέα σύνδεση
    if (!oldUsername.equals(dto.getUsername())) {

        new SecurityContextLogoutHandler()
                .logout(request, response,SecurityContextHolder.getContext().getAuthentication());

      //αν κάνουμε logout και μετά χρησιμοποιήσουμε flash message, το μήνυμα μπορεί να χαθεί, επειδή το logout ακυρώνει το session

        return "redirect:/login?usernameChanged";
    }

    redirectAttributes.addFlashAttribute("successMessage", "Τα στοιχεία σου ενημερώθηκαν επιτυχώς."
    );

    return "redirect:/customers/profile";
}


		//delete
		@PostMapping("/customers/delete")
		public String deleteCustomer(Principal principal,
							HttpServletRequest request,
							HttpServletResponse response) {
		
		    // Βρίσκουμε τον συνδεδεμένο πελάτη
		    String username = principal.getName();
		
		    CustomerResponseDto customer =customerService.getCustomerByUsername(username);
		
		    // Διαγράφουμε τον πελάτη από τη βάση
		    // Επειδή έχουμε ορίσει cascade = CascadeType.ALL και orphanRemoval = true στη σχέση του Customer με τις εργασίες του, η διαγραφή του πελάτη θα διαγράψει και τις σχετικές εργασίες του.
		    customerService.deleteCustomer(customer.getId());
		
		    // Τερματίζουμε τη σύνδεσή του
		    new SecurityContextLogoutHandler()
		            .logout(request,response,
		                    SecurityContextHolder.getContext().getAuthentication());
		
		    return "redirect:/login?accountDeleted";
		}
		
		}