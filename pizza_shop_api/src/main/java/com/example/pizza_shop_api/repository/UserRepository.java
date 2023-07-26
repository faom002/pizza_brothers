package com.example.pizza_shop_api.repository;

import com.example.pizza_shop_api.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
   List<User> findByUserNameAndPassword( String userName,int password);
}