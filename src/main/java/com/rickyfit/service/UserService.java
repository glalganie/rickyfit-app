package com.rickyfit.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rickyfit.model.User.Role; 
import com.rickyfit.model.User;
import com.rickyfit.repository.UserRepository;

@Service
public class UserService {
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User registerNewMember(User user) {
	        // Codifica la password prima di salvarla
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        // Imposta il ruolo di default, se non già impostato dal form
	        if (user.getRole() == null) {
	            user.setRole(Role.MEMBER);
	        }
	        return userRepository.save(user);
	    }
	    
	    public java.util.Optional<User> findByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	    
	    
	}