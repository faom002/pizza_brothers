package com.example.pizza_shop_api.login_and_register;


import com.example.pizza_shop_api.model.User;
import com.example.pizza_shop_api.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping(path="/pizza") // This means URL's start with /pizza (after Application path)
@CrossOrigin(origins = "*") //  allows other application outside this project to communicate with backend
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping(path="/add")
    public String addNewUser (@RequestParam String userName, @RequestParam int password) {
        Optional<User> existingUser = userRepository.findByUserName(userName);
        if (existingUser.isPresent()) {
            return "User already exists";
        } else {
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(password);
            userRepository.save(newUser);
            return "User saved";
        }
    }


    @GetMapping(path="/all")
    public  Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/user")
    public List<User> getUser(@RequestParam(name = "username") String user, @RequestParam(name = "password") int password){
        return userRepository.findByUserNameAndPassword(user,password);
    }
}