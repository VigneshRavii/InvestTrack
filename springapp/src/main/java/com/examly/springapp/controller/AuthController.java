package com.examly.springapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.config.MyUserDetailsService;
import com.examly.springapp.exceptions.PasswordIncorrect;
import com.examly.springapp.exceptions.UserNotExists;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
   
    private final AuthenticationManager authenticationManager;
   
    private final JwtUtils jwtUtils;

    private final MyUserDetailsService userDetailsService;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils,
            MyUserDetailsService userDetailsService, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User u = userService.createUser(user);
        if(u != null) {
            return ResponseEntity.status(201).body(u);
        }
        return new ResponseEntity<>(HttpStatus.valueOf(400));
         
    }  

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> loginUser(@RequestBody User user) {
        try {
            authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String token = jwtUtils.generateToken(userDetails);
            User fullUser = userService.loginUser(user);
            LoginDTO loginDTO = new LoginDTO(token, fullUser.getUsername(), fullUser.getUserRole(), fullUser.getUserId());
            return new ResponseEntity<>(loginDTO, HttpStatus.valueOf(200));
        }
        catch(Exception e) {
            User eUser=userRepo.findByEmail(user.getEmail()).orElse(null);
        if(eUser==null){
            throw new UserNotExists("User does not exist with " + user.getEmail());
        }
        if(!(passwordEncoder.matches(user.getPassword(), eUser.getPassword()))){
            throw new PasswordIncorrect("Password is incorrect.. please enter valid password");
        }
            return new ResponseEntity<>(HttpStatus.valueOf(400));
        }

    }
} 
