package com.coderspark.client.service;

import com.coderspark.client.entity.User;
import com.coderspark.client.entity.VerificationToken;
import com.coderspark.client.model.UserModel;
import com.coderspark.client.repository.VerificationTokenRepository;
import com.coderspark.client.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

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
        VerificationToken verificationToken = new VerificationToken(user,token);

        verificationTokenRepository.save(verificationToken);
    }
}
