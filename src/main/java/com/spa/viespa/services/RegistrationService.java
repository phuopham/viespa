package com.spa.viespa.services;

import com.spa.viespa.entities.RegistrationRequest;
import com.spa.viespa.entities.User;
import com.spa.viespa.entities.UserRoll;
import com.spa.viespa.security.EmailValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw  new IllegalStateException("Email not valid");
        }
        return userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRoll.USER
                )
        );
    }
}
