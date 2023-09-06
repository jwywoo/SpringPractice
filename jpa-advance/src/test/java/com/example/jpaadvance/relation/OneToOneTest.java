package com.example.jpaadvance.relation;

import com.example.jpaadvance.entity.Food;
import com.example.jpaadvance.entity.User;
import com.example.jpaadvance.repository.FoodRepository;
import com.example.jpaadvance.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Transactional
@SpringBootTest
public class OneToOneTest {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("O2O test")
    void test1() {
        User user = new User();
        user.setName("Robbie");

        Food food = new Food();
        food.setName("Korean Fried Chicken");
        food.setPrice(11000);
        food.setUser(user);

        userRepository.save(user);
        foodRepository.save(food);
    }
}
