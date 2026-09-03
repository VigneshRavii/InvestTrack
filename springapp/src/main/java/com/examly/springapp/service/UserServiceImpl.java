package com.examly.springapp.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examly.springapp.exceptions.PasswordIncorrect;
import com.examly.springapp.exceptions.UserAlreadyExists;
import com.examly.springapp.exceptions.UserNotExists;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(User user) {
        User eUser=userRepo.findByEmail(user.getEmail()).orElse(null);
        if(eUser != null) {
            throw new UserAlreadyExists("User with " + user.getEmail() + " already exists");
        }
        User user1 = userRepo.findByUsername(user.getUsername()).orElse(null);
        if(user1 != null) {
            throw new UserAlreadyExists("User with " + user.getUsername() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User loginUser(User user) {
        User eUser=userRepo.findByEmail(user.getEmail()).orElse(null);
        if(eUser==null){
            throw new UserNotExists("User doesnot exist!");
        }
        if(!(passwordEncoder.matches(user.getPassword(), eUser.getPassword()))){
            throw new PasswordIncorrect("Password is incorrect.. please enter valid password");
        }
      return eUser;
    }

}
