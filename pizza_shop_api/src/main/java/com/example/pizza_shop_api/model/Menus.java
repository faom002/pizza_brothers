package com.example.pizza_shop_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menus")
public class Menus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String menuName;
    @Column(unique = true)
    private String menuIngredient;
    @Column(unique = true)
    private int menuPrice;

    @Lob
    @Column(name = "menu_image", nullable = true, columnDefinition = "mediumblob", unique = true) // Adjust the columnDefinition accordingly
    private byte[] menuImage;



    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuIngredient() {
        return menuIngredient;
    }

    public void setMenuIngredient(String menuIngredient) {
        this.menuIngredient = menuIngredient;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public byte[] getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(byte[] menuImage) {
        this.menuImage = menuImage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
