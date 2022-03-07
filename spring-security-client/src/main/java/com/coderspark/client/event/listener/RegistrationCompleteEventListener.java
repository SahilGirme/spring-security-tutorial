package com.coderspark.client.event.listener;

import com.coderspark.client.entity.User;
import com.coderspark.client.event.RegistrationCompleteEvent;
import com.coderspark.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

@Slf4j
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
        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;

//        SendVerificationEmail()
        log.info("Click the link to verify your account: {}",url);
    }
}
