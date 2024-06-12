package com.inventory.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import com.inventory.dto.AdminDto;
import com.inventory.dto.JwtTokenDto;
import com.inventory.dto.LoginSucessDto;
import com.inventory.model.Admin;
import com.inventory.repository.AdminRepository;
import com.inventory.service.AdminService;
import com.inventory.service.JwtService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    Logger log = LoggerFactory.getLogger(AdminController.class);
    

    // Add a new Admin
    @PostMapping("/signup")
    ResponseEntity<String> addAdmin(@RequestBody @Valid AdminDto adminRequestDto) {

        log.info("AdminController :: RequestBody {} ", adminRequestDto);

        String name = adminService.addAdmin(adminRequestDto);

        String new_name = "Welcome " + name;

        log.info("AdminController :: ResponseBody {} ", adminRequestDto);

        return new ResponseEntity<>(new_name, HttpStatus.OK);

    }


    //Get the Jwt Token 
    @PostMapping("/login")
    public ResponseEntity<LoginSucessDto> authenticate(@RequestBody JwtTokenDto jwtTokenDto) {

        log.info("AdminController::authenticate request {} ", jwtTokenDto);

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtTokenDto.getEmail(), jwtTokenDto.getPassword()));

        log.info("AdminController::authenticate authenticate object--> {} ", authenticate);
        if (authenticate.isAuthenticated()) {

            log.info("AdminController::authenticate generating Token");
                Optional<Admin> admin= adminRepository.findByEmail(jwtTokenDto.getEmail());

                String jwtToken= jwtService.generateToken(jwtTokenDto.getEmail());

                LoginSucessDto loginSucessDto=new LoginSucessDto(jwtToken, admin.get().getAdmin_id(), admin.get().getUserName());
                loginSucessDto.setEmail(admin.get().getEmail());

                return new ResponseEntity<>(loginSucessDto,HttpStatus.OK);




        }

        else {

            log.info("AdminController::authenticate Failed to Generate Token");

            throw new UsernameNotFoundException("Authentication Failed");
        }

    }

      


}
