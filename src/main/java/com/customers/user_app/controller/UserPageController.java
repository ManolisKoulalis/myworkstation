package com.customers.user_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.customers.user_app.dto.UserRequest;
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
    public String showRegisterPage() {
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser( @Valid @ModelAttribute UserRequest request, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.saveUser(request);

        return "redirect:/";
    }
    
    
}
