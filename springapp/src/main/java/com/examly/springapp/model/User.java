package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotBlank(message = "Email must not be blank")
    @Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "Email must be in a valid format like user@example.com"
    )
    private String email;


    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    
    @NotBlank(message = "Username must not be blank")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username;

    
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;
    
    @NotBlank(message = "User role must not be blank")
    private String userRole;

    // public User() {

    // }

    // public User(long userId, String email, String password, String username, String mobileNumber, String userRole) {
    //     this.userId = userId;
    //     this.email = email;
    //     this.password = password;
    //     this.username = username;
    //     this.mobileNumber = mobileNumber;
    //     this.userRole = userRole;
    // }

    // public long getUserId() {
    //     return userId;
    // }

    // public void setUserId(long userId) {
    //     this.userId = userId;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public void setEmail(String email) {
    //     this.email = email;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    // public String getUsername() {
    //     return username;
    // }

    // public void setUsername(String username) {
    //     this.username = username;
    // }

    // public String getMobileNumber() {
    //     return mobileNumber;
    // }

    // public void setMobileNumber(String mobileNumber) {
    //     this.mobileNumber = mobileNumber;
    // }

    // public String getUserRole() {
    //     return userRole;
    // }

    // public void setUserRole(String userRole) {
    //     this.userRole = userRole;
    // }

}
