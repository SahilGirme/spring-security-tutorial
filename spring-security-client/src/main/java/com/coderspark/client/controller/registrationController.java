package com.coderspark.client.controller;

import com.coderspark.client.entity.User;
import com.coderspark.client.event.RegistrationCompleteEvent;
import com.coderspark.client.model.UserModel;
import com.coderspark.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class registrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel , final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")){
            return "User Verified Successfully";
        }
        return "Bad User";

    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://"+
                request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }


}
