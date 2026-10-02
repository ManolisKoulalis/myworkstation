package com.customers.user_app.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customers.user_app.dto.UserRequest;
import com.customers.user_app.dto.UserResponse;
import com.customers.user_app.entity.Address;
import com.customers.user_app.entity.User;
import com.customers.user_app.exception.UserNotFoundException;
import com.customers.user_app.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
  
    // create user
    @Transactional
    public UserResponse saveUser(UserRequest request) {
    	
    	 Address workingAddress=null;
		 Address homeAddress=null;
		
		 workingAddress.setName(request.getWorkAddress().getName());
		 workingAddress.setPostcode(request.getWorkAddress().getPostcode());
		 
		 homeAddress.setName(request.getHomeAddress().getName());
		 homeAddress.setPostcode(request.getHomeAddress().getPostcode());
		 
    	
    	 User user = new User();
    	 user.setName(request.getName());
    	 user.setSurname(request.getSurname());
    	 user.setGender( request.getGender());
		 user.setBirthdate( request.getBirthdate());
		 user.setHome_address(homeAddress);
		 user.setWork_address(workingAddress);
    	 
    	 User savedUser = userRepository.save(user);

    	  return toResponse(savedUser);
    }
    
    //get user by id
    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
       
    	User user = userRepository.findById(id) .orElseThrow(() -> new UserNotFoundException(id));
    	
    	return toResponse(user);
    }
    
    // get user list
    @Transactional(readOnly = true)
    public List<UserResponse> getUserList() {
        
    	
    	return userRepository.findAll().stream() .map(user -> toResponse(user)).toList();
    }
    
    //delete
    @Transactional
    public void deleteUser(Long id) {
    	
    	if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
    	
        userRepository.deleteById(id);
    }
    
    
    //update
    @Transactional
    public UserResponse updateUser( Long id, UserRequest newUserData) {
    		 User existingUser = userRepository.findById(id) .orElseThrow(() -> new UserNotFoundException(id));
    		
    		 if (existingUser == null) {
        		 return null;
        		 }
    		 
    		 
    		 Address workingAddress=null;
    		 Address homeAddress=null;
    		 
    		 workingAddress.setName(newUserData.getWorkAddress().getName());
    		 workingAddress.setPostcode(newUserData.getWorkAddress().getPostcode());
    		 
    		 homeAddress.setName(newUserData.getHomeAddress().getName());
    		 homeAddress.setPostcode(newUserData.getHomeAddress().getPostcode());
    		 
    		 existingUser.setName( newUserData.getName());
    		 existingUser.setSurname( newUserData.getSurname());
    		 existingUser.setGender( newUserData.getGender());
    		 existingUser.setBirthdate( newUserData.getBirthdate());
    		 existingUser.setHome_address(homeAddress);
    		 existingUser.setWork_address(workingAddress);
    		 
    		// Εφοσον το existingUser ειναι managed μεσω στο transaction δεν χρειαζεται αυτη η εντολη για να γινουν οι αλλαγες
    		 User updatedUser= userRepository.save(existingUser);
 
    		 return toResponse(existingUser);
    		 }
    
    
    @Transactional(readOnly = true)
    public List<UserResponse> getUsersBySurname(String surname) {

        return userRepository.findBySurname(surname).stream().map(user -> toResponse(user)).toList();
    }
    
    
    
    
    private UserResponse toResponse(User user) {

	    return new UserResponse( user.getId(), user.getName(),user.getSurname(),user.getGender(),user.getBirthdate(),user.getWork_address(),user.getHome_address());
	}
}