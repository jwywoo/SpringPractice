package com.example.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.setUser(this); // foreign key set
    }
}

// ManyToMany through third table
//@OneToMany(mappedBy = "user")
//    private List<Order> orderList = new ArrayList<>();
// OneToMany
//    @OneToMany(mappedBy = "user")
//    private List<Food> foodList = new ArrayList<>();
//    public void addFoodList(Food food) {
//        this.foodList.add(food);
//        food.setUser(this);
//    }
//    OneToOne
//    @OneToOne(mappedBy = "user")
//    private Food food;
//    public void addFood(Food food) {
//        this.food = food;
//        food.setUser(this);
//    }