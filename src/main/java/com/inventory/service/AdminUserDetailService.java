package com.inventory.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inventory.model.Admin;
import com.inventory.repository.AdminRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class AdminUserDetailService implements UserDetailsService{
 
    @Autowired
     private AdminRepository adminRepository;

     @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      

  log.info("AdminUserDetailService::loadUserByUsername before fetching from Database {} ",email);
  
  Optional<Admin> admin=adminRepository.findByEmail(email);
  
  Admin admin2=admin.get();
  
  log.info("AdminUserDetailService::loadUserByUsername after fetching from Database {} , Name {}  , password {}  ",admin2.getEmail(),admin2.getUserName(),admin2.getPassword());


  return admin.map(AdminUserDetails::new)
  .orElseThrow(() -> new UsernameNotFoundException(email + "Not found in System"));
        


    }

}
