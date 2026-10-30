
package com.customers.customerapp.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customers.customerapp.dto.CustomerResponseDto;
import com.customers.customerapp.dto.WorkRequestDto;
import com.customers.customerapp.service.CustomerService;
import com.customers.customerapp.service.WorkService;

import jakarta.validation.Valid;

@Controller
public class WorkController {

    private final WorkService workService;
    private final CustomerService customerService;

    public WorkController(WorkService workService,CustomerService customerService) {
        this.workService = workService;
        this.customerService=customerService;
    }

    @GetMapping("/customers/works/new")
    public String showAddWorkPage(Model model) {

        WorkRequestDto work = new WorkRequestDto();

        model.addAttribute("work", work);

        return "work-add";
    }
    

		@PostMapping("/customers/works/new")
		public String addWork(@Valid @ModelAttribute("work") WorkRequestDto dto,BindingResult bindingResult,
		        Principal principal,
		        RedirectAttributes redirectAttributes) {
		
		    // Έλεγχος των validation annotations του DTO
		    if (bindingResult.hasErrors()) {
		        return "work-add";
		    }
		
		    String username=principal.getName();
		    // Βρίσκουμε τον συνδεδεμένο πελάτη
		    CustomerResponseDto customer =customerService.getCustomerByUsername(username);
		
		    try {
		
		        // Δημιουργούμε την εργασία για τον πελάτη
		        workService.createWork(customer.getId(), dto);
		
		    } catch (IllegalArgumentException e) {
		        bindingResult.rejectValue("paidCharge","invalidPaidCharge",
		                e.getMessage()
		        );
		
		        return "work-add";
		    }
		
		    redirectAttributes.addFlashAttribute("successMessage",
		            "Η εργασία προστέθηκε επιτυχώς."
		    );
		
		    return "redirect:/customers/profile";
		}

    

		@GetMapping("/customers/works/{workId}/edit")
		public String showEditWorkPage(@PathVariable Integer workId, Principal principal,Model model) {
		
		    // Βρίσκουμε τον συνδεδεμένο πελάτη
		    CustomerResponseDto customer =customerService.getCustomerByUsername(principal.getName());
		
		    // Βρίσκουμε την εργασία μόνο αν ανήκει στον πελάτη
		    WorkRequestDto work = workService.getWorkForEdit(workId,customer.getId());
		
		    model.addAttribute("work", work);
		    //το work που στέλνουμε στη JSP είναι WorkRequestDto, όχι Work Entity ή WorkResponseDto.Στο δικό μας WorkRequestDto δεν έχουμε πεδίο id.
		    model.addAttribute("workId", workId);
		    
		
		    return "work-edit";
		}

		

		@PostMapping("/customers/works/{workId}/edit")
		public String updateWork(@PathVariable Integer workId,@Valid @ModelAttribute("work") WorkRequestDto dto,
		        BindingResult bindingResult,
		        Principal principal,
		        Model model,
		        RedirectAttributes redirectAttributes) {
		
		    //Δεν χρειάζεται hidden πεδίο, γιατί το workId επιστρέφει στον Controller μέσω του URL της φόρμας, όχι μέσω των πεδίων της.
			
		    // Το ID χρειάζεται ξανά στη φόρμα αν υπάρξει σφάλμα
		    model.addAttribute("workId", workId);
		
		    // Έλεγχος των validation annotations
		    if (bindingResult.hasErrors()) {
		        return "work-edit";
		    }
		
		    // Βρίσκουμε τον συνδεδεμένο πελάτη
		    CustomerResponseDto customer =customerService.getCustomerByUsername(principal.getName());
		
		    try {
		
		        // Ενημερώνουμε την εργασία του συγκεκριμένου πελάτη
		        workService.updateWork(workId,customer.getId(),dto);
		
		    } catch (IllegalArgumentException e) {
		
		        bindingResult.rejectValue("paidCharge","invalidPaidCharge",
		                e.getMessage()
		        );
		
		        return "work-edit";
		    }
		
		    redirectAttributes.addFlashAttribute("successMessage",
		            "Η εργασία ενημερώθηκε επιτυχώς."
		    );
		
		    return "redirect:/customers/profile";
		}

		
		@PostMapping("/customers/works/{workId}/delete")
		public String deleteWork( @PathVariable Integer workId,Principal principal,RedirectAttributes redirectAttributes) {
		
		    // Βρίσκουμε τον συνδεδεμένο πελάτη
		    CustomerResponseDto customer =customerService.getCustomerByUsername(principal.getName());
		
		    // Διαγράφουμε την εργασία μόνο αν ανήκει στον πελάτη
		    workService.deleteWork(workId,customer.getId());
		
		    redirectAttributes.addFlashAttribute("successMessage",
		            "Η εργασία διαγράφηκε επιτυχώς."
		    );
		
		    return "redirect:/customers/profile";
		}


}
