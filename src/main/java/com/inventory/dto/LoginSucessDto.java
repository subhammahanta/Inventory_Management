package com.inventory.dto;




public class LoginSucessDto {


    private String jwtToken;

    private int adminId;

    private String userName;

    private String email;

    

    

    public LoginSucessDto(String jwtToken, int adminId, String userName) {
        this.jwtToken = jwtToken;
        this.adminId = adminId;
        this.userName = userName;
    }

    public LoginSucessDto() {
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
