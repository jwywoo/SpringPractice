package com.example.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

// ManyToMany Using third table
// @OneToMany(mappedBy = "food")
// private List<Order> orderList = new ArrayList<>();
// ManyToMany
//    @ManyToMany
//    @JoinTable(name="orders",
//            joinColumns = @JoinColumn(name="food_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> userList = new ArrayList<>();
//    public void addUserList(User user) {
//        this.userList.add(user); // 외래 키(연관 관계) 설정
//        user.getFoodList().add(this);
//    }
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//    @OneToMany
//    @JoinColumn(name = "food_id")
//    private List<User> userList = new ArrayList<>();


