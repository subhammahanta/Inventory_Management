package com.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inventory.dto.AdminDto;
import com.inventory.model.Admin;
import com.inventory.repository.AdminRepository;
import com.inventory.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
   private AdminRepository adminRepository;


   @Autowired
   private PasswordEncoder passwordEncoder;

    @Override
    public String addAdmin(AdminDto adminDto) {


        Admin admin=adminDtoToAdmin(adminDto);

         admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        
         admin= adminRepository.save(admin);

         return admin.getUserName();


       
    }



    public  Admin adminDtoToAdmin(AdminDto adminDto){

        Admin admin=new Admin();

        admin.setUserName(adminDto.getUserName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());


        return admin;

}


      public  AdminDto adminToAdminDto(Admin admin){

                 AdminDto adminDto=new AdminDto();

                 adminDto.setUserName(admin.getUserName());
                 adminDto.setEmail(admin.getEmail());
                 adminDto.setPassword(admin.getPassword());


                 return adminDto;

    }

}
