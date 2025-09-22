package com.acropolis.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurataion
{
	@Autowired
	private JWTAuthFilter jwtAuthFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
	{
		http
			.csrf(t -> t.disable())
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests(auth-> 
					auth.requestMatchers("/money/**").permitAll()
					.requestMatchers("/auth/admin/**").hasRole("ADMIN")
					.requestMatchers("/auth/cust/**").hasRole("CUSTOMER")
					.anyRequest().authenticated())
			.exceptionHandling(cust->cust.accessDeniedPage("/money/wrongauth"))
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);	
		
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder getEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
}
