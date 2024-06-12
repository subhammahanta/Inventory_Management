package com.inventory.dto;



  public class JwtTokenDto {

    private String email;

    private String password;

    

    public JwtTokenDto() {
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


    

  }
