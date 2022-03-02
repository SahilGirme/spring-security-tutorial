package com.coderspark.springsecurityclient.service;

import com.coderspark.springsecurityclient.entity.User;
import com.coderspark.springsecurityclient.entity.VerificationToken;
import com.coderspark.springsecurityclient.model.UserModel;
import com.coderspark.springsecurityclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName((userModel.getFirstName()));
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);

        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
//        VerificationToken  verificationToken = new
    }
}
