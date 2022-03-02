package com.coderspark.springsecurityclient.controller;

import com.coderspark.springsecurityclient.entity.User;
import com.coderspark.springsecurityclient.event.RegistrationCompleteEvent;
import com.coderspark.springsecurityclient.model.UserModel;
import com.coderspark.springsecurityclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class registrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,"url"
        ));
        return "Success";
    }
}
