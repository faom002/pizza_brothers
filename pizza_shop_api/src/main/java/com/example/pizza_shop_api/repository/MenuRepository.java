package com.example.pizza_shop_api.repository;

import com.example.pizza_shop_api.model.Menus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MenuRepository extends CrudRepository <Menus,Integer> {



}
