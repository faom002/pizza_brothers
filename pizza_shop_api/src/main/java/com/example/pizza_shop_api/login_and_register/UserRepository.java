package com.example.pizza_shop_api.login_and_register;

import org.springframework.data.repository.CrudRepository;

import com.example.pizza_shop_api.login_and_register.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}