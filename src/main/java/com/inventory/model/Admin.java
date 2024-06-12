package com.inventory.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int admin_id;

 private String userName;

 @Column(unique = true,nullable = false)
 private String email;

 private String password;


 @Column(nullable = false)
 private String role = "ROLE_ADMIN"; 
 


 
//  @OneToOne(mappedBy = "admin",fetch = FetchType.LAZY)
//  private ProductInventory productInventory;


//  @OneToMany
//  @JoinColumn(name = "admin_id")
//  private List<Sales> Sales;
 





public Admin() {
}

public int getAdmin_id() {
    return admin_id;
}

public void setAdmin_id(int admin_id) {
    this.admin_id = admin_id;
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

 public String getRole() {
    return role;
}

public void setRole(String role) {
    this.role = role;
}

public String getUserName() {
    return userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}


 


}
