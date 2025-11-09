package com.rickyfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.rickyfit.service.UserService;
import com.rickyfit.model.User;
import com.rickyfit.repository.UserRepository;

import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	 private final UserService userService;
	    private final UserRepository userRepository; // Per controllo unicità email

	    public AuthController(UserService userService, UserRepository userRepository) {
	        this.userService = userService;
	        this.userRepository = userRepository;
	    }

	    // Aggiunge l'oggetto User vuoto al modello per il form di registrazione (GET)
	    @GetMapping("/login.html")
	    public String loginPage() {
	        
	        return "login";
	    }

	    // Gestisce il submit del form di registrazione (POST)
	    @PostMapping("/register")
	    public String registerUser(@Valid @ModelAttribute("user") User user,
	                               BindingResult result,
	                               Model model) {
	        
	        // 1. Validazione base con @Valid e BindingResult
	        if (result.hasErrors()) {
	            // Se ci sono errori, torna alla pagina di login/register mostrando gli errori
	            return "login";
	        }

	        // 2. Controllo unicità email
	        if (userRepository.existsByEmail(user.getEmail())) {
	            model.addAttribute("registrationError", "Email già in uso. Prova ad accedere.");
	            return "login";
	        }

	        // 3. Registrazione effettiva
	        userService.registerNewMember(user);

	        // Successo: reindirizza alla pagina di login con messaggio di successo
	        return "redirect:/login.html?registered=true";
	    }
	}