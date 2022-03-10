package com.coderspark.client.service;


import com.coderspark.client.entity.User;
import com.coderspark.client.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
