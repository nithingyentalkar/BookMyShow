package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Services.UserService;
import com.example.BookMyShow.dtos.ResponseStatus;
import com.example.BookMyShow.dtos.SignUpRequestdto;
import com.example.BookMyShow.dtos.SignupResponsedto;
import com.example.BookMyShow.dtos.SignUpRequestdto;
import com.example.BookMyShow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    public SignupResponsedto SignUp(SignUpRequestdto request){
       User user = userService.signUp(request.getEmail(), request.getPassword());
        return new SignupResponsedto(user.getId(), ResponseStatus.SUCCESS);
    }

    public boolean login(String email,String password){
       return userService.login(email, password);
    }
}
