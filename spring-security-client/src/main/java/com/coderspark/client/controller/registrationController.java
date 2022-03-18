package com.coderspark.client.controller;

import com.coderspark.client.entity.User;
import com.coderspark.client.entity.VerificationToken;
import com.coderspark.client.event.RegistrationCompleteEvent;
import com.coderspark.client.model.UserModel;
import com.coderspark.client.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class registrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user, applicationUrl(request)
        ));
        return "Success";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User Verified Successfully";
        }
        return "Bad User";

    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);

        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        //Creating URl
        String url = applicationUrl
                + "/verifyRegistration?token="
                + verificationToken.getToken();
//        SendVerificationEmail()
        log.info("Click the link to verify your account: {}", url);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() + ":" +
                request.getServerPort() +
                request.getContextPath();
    }


}
