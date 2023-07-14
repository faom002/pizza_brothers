package com.example.pizza_shop_api.login_and_register;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserService, Integer> {

}