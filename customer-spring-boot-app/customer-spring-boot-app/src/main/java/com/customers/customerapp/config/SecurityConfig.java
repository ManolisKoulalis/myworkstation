
package com.customers.customerapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        .authorizeHttpRequests(auth -> auth
        	    .dispatcherTypeMatchers(
        	        DispatcherType.FORWARD,
        	        DispatcherType.ERROR
        	    ).permitAll()

        	    .requestMatchers(
        	    	"/",
        	        "/login",
        	        "/register",
        	        "/css/**",
        	        "/js/**",
        	        "/images/**"
        	    ).permitAll()

        	    .anyRequest().authenticated()
        	)


            /* ουσιαστικα το spring security εκτελει μονο του το post/login που ορισαμε επειτα μεσω την κλασης CustomerUserDetailsService που υλοποιησαμε μπορει 
            και κανει το authentication με βαση το username που υπαρχει πραγματικα στην βαση και ελεγχει και τον κωδικο */
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/customers/profile", true)
                .failureUrl("/login?error")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }
}

