package com.example.pizza_shop_api.controllers;


import com.example.pizza_shop_api.model.Menus;
import com.example.pizza_shop_api.model.User;
import com.example.pizza_shop_api.repository.MenuRepository;
import com.example.pizza_shop_api.repository.UserRepository;
import com.example.pizza_shop_api.utilities.ImageUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // This means that this class is a Controller
@RequestMapping(path="/pizza") // This means URL's start with /pizza (after Application path)
@CrossOrigin(origins = "*") //  allows other application outside this project to communicate with backend
public class ResourceController {
    private final UserRepository userRepository;

    private final MenuRepository menuRepository;

    public ResourceController(UserRepository userRepository, MenuRepository menuRepository){
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
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
        return this.userRepository.findByUserNameAndPassword(user,password);
    }


    @GetMapping(path = "/menus/add")
    public Iterable<Menus> addPredefinedMenu() {
        String imagePath = "C:\\Users\\Faisa\\Documents\\Frontend\\Personal-projects\\pizza_brothers\\pizza_shop_api\\src\\main\\resources\\images\\pizza-mozzarella.jpg";
        byte[] imageBytes;
        try {
            imageBytes = ImageUtils.imageToByteArray(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

        Menus menu = new Menus();
        menu.setMenuName("Pizza");
        menu.setMenuIngredient("Cheese, Tomato, Pepperoni");
        menu.setMenuPrice(199);
        menu.setMenuImage(imageBytes);

        this.menuRepository.save(menu);

        return this.menuRepository.findAll();
    }

@GetMapping(path = "/menus/all")
    public Iterable<Menus> getAllMenus(){
        return this.menuRepository.findAll();
}


}