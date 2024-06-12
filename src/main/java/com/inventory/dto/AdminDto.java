package com.inventory.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;




public class AdminDto {

    
    @NotNull(message = "userName cannot be null")

    private String userName;
    
    @Column(unique = true)
    @NotEmpty(message = "Please provide Email")
    private String email;
    
    @NotEmpty(message = "Please provide password")
    private String password;



    public AdminDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

  
    

}
