package com.customers.user_app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.customers.user_app.dto.UserRequest;
import com.customers.user_app.dto.UserResponse;
import com.customers.user_app.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserPageController {

	private final UserService userService;
	
	public UserPageController(UserService userService) {
	    this.userService = userService;
	}
	
	
	
	@GetMapping("/")
    public String home() {
        return "index";
    }
    

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
    
    /* Επειδή επιλέξαμε να χρησιμοποιήσουμε:
    	<form:form modelAttribute="userRequest"> sto register.jsp
    		Το Spring διαβάζει αυτό και ψάχνει στο Model ένα αντικείμενο με όνομα userRequest.
    			Αν δεν το βρει, μπορεί να εμφανίσει σφάλμα κατά το rendering της φόρμας.*/
        model.addAttribute("userRequest", new UserRequest());

        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser( @Valid @ModelAttribute("userRequest") UserRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.saveUser(request);

        return "redirect:/";
    }
    
    @GetMapping("/displayUsers")
    public String showUsers(Model model) {

        List<UserResponse> users = userService.getUserList();

        model.addAttribute("userlist", users);

        return "displayUsers";
    }
    
    @GetMapping("/details/{id}")
    public String showUserDetails (@PathVariable Long id,Model model) {

        UserResponse user = userService.getUserById(id);

        model.addAttribute("user", user);

        return "details";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return "redirect:/displayUsers";
    }
    
    
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {

       

        // μετατρεπω το userRsponse σε userRequest καθως το form στο modelAttribute περιμενει userRequest
        UserRequest request = userService.getUserRequestById(id);

        
        model.addAttribute("userRequest", request);
        model.addAttribute("userId", id);

        return "editUser";
    }
    
    
    @PostMapping("/edit/{id}")
    public String updateUser( @PathVariable Long id, @Valid @ModelAttribute("userRequest") UserRequest request, BindingResult bindingResult,  Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userId", id);
            return "editUser";
        }

        userService.updateUser(id, request);

        return "redirect:/details/" + id;
    }
    
    
    
    
}
