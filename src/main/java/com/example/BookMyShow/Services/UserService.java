package com.example.BookMyShow.Services;

import com.example.BookMyShow.Repositories.UserRepository;
import com.example.BookMyShow.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User signUp(String email, String password){
           Optional<User> user =  userRepository.findByEmail(email);
            if(user.isPresent()) throw new RuntimeException("User is already present, Please create new user");

        User NewUser = new User();
        NewUser.setEmail(email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        NewUser.setPassword(encoder.encode(password));
        return userRepository.save(NewUser);
    }

    public boolean login(String email, String password){
      Optional<User> user =  userRepository.findByEmail(email);
        if(user.isEmpty()) throw new RuntimeException("Invalid User, Please create a new User");

        User oldUser = user.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, oldUser.getPassword());
    }
}
