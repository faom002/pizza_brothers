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

@RestController
@RequestMapping(path="/pizza")
@CrossOrigin(origins = "*")
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




    @GetMapping(path="/user")
    public List<User> getUser(@RequestParam(name = "username") String user, @RequestParam(name = "password") int password){
        return this.userRepository.findByUserNameAndPassword(user,password);
    }


    @GetMapping(path = "/menus/add")
    public Iterable<Menus> addPredefinedMenu() {
        String[] imagePaths = {
                "C:\\Users\\Faisa\\Documents\\codeAcademy\\personal-project\\pizza_brothers\\pizza_shop_api\\src\\main\\resources\\images\\pizza-mozzarella.jpg",
                "C:\\Users\\Faisa\\Documents\\codeAcademy\\personal-project\\pizza_brothers\\pizza_shop_api\\src\\main\\resources\\images\\bacon-pizza.jpg",
                "C:\\Users\\Faisa\\Documents\\codeAcademy\\personal-project\\pizza_brothers\\pizza_shop_api\\src\\main\\resources\\images\\smokey-bacon-pizza.jpg"
        };

        List<Menus> predefinedMenus = new ArrayList<>();

        predefinedMenus.add(createMenu("Pizza", "Cheese, Tomato, Pepperoni", 199, imagePaths[0]));
        predefinedMenus.add(createMenu("Pizza with Bacon", "Cheese, Tomato, Pepperoni, Bacon", 250, imagePaths[1]));
        predefinedMenus.add(createMenu("Pizza with Bacon test", "Cheese, Tomato, Pepperoni, Bacontest", 200, imagePaths[2]));

        for (Menus menu : predefinedMenus) {
            if (!menuExists(menu)) {
                this.menuRepository.save(menu);
            }
        }

        return this.menuRepository.findAll();
    }

    private Menus createMenu(String menuName, String menuIngredient, int menuPrice, String imagePath) {
        Menus menu = new Menus();
        menu.setMenuName(menuName);
        menu.setMenuIngredient(menuIngredient);
        menu.setMenuPrice(menuPrice);

        try {
            byte[] imageBytes = ImageUtils.imageToByteArray(imagePath);
            menu.setMenuImage(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return menu;
    }

    private boolean menuExists(Menus menu) {
        Menus existingMenu = menuRepository.findByMenuNameAndMenuIngredientAndMenuPrice(
                menu.getMenuName(),
                menu.getMenuIngredient(),
                menu.getMenuPrice()
        );
        return existingMenu != null;
    }


    @GetMapping(path = "/menus/all")
    public Iterable<Menus> getAllMenus(){
        return this.menuRepository.findAll();
}


}