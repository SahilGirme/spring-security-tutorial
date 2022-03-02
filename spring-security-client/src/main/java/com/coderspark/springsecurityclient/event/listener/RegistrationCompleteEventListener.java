package com.coderspark.springsecurityclient.event.listener;

import com.coderspark.springsecurityclient.entity.User;
import com.coderspark.springsecurityclient.event.RegistrationCompleteEvent;
import com.coderspark.springsecurityclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        Create the Verification Token for User
        User user = event.getUser();
        String token  = UUID.randomUUID().toString();
       userService.saveVerificationTokenForUser(token,user);
//        SendMail to user
    }
}
