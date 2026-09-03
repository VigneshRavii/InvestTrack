package com.examly.springapp.service;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;

@Service
public interface UserService {

    User createUser(User user);

    User loginUser(User user);

}
