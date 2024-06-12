package com.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inventory.filter.JwtAuthFilter;
import com.inventory.service.AdminUserDetailService;
@Configuration
@EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class InventorySecurityConfig {

  @Autowired
    private JwtAuthFilter jwtAuthFilter;


    @Bean // Authentication
    public UserDetailsService userDetailsService() {

 

                return new AdminUserDetailService();

    }

      @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

       

           return http.csrf().disable()
               .authorizeRequests().antMatchers("/admin/signup","/admin/login").permitAll()
                .and().authorizeRequests().antMatchers("/admin/product/**","/admin/invoice/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){

     DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();

     authenticationProvider.setUserDetailsService(userDetailsService());
     authenticationProvider.setPasswordEncoder(passwordEncoder());
   
     return authenticationProvider;

    }

 
    @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
     }






}
