package com.coderspark.springsecurityclient.service;


import com.coderspark.springsecurityclient.entity.User;
import com.coderspark.springsecurityclient.model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
