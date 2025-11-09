package com.rickyfit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rickyfit.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	// Configura l'algoritmo di hashing per le password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            UserDetailsServiceImpl userDetailsService,
            PasswordEncoder passwordEncoder) { // <-- INIETTA ANCHE PasswordEncoder QUI
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder); // <-- USA L'PasswordEncoder INIETTATO
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**").disable())
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", "/homepage.html", "/abbonamenti.html", "/login", "/login.html", "/register",
                        "/css/**", "/js/**", "/images/**", "/navbar.css", "/style.css", "/app.js", "/h2-console/**"
                ).permitAll()
                .requestMatchers("/admin.html", "/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/progressi.html", "/corsi_prenotazioni.html").hasAnyAuthority("MEMBER", "TRAINER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/progressi.html", true)
                .failureUrl("/login.html?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/login.html?logout=true")
                .permitAll()
            );

        return http.build();
    }
}