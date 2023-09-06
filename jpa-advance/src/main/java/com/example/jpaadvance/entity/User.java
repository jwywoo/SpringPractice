package com.example.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "userList")
    private List<Food> foodList = new ArrayList<>();

    public void addFoodList(Food food) {
        this.foodList.add(food);
        food.getUserList().add(this); // 외래 키(연관 관계) 설정
    }

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
}
