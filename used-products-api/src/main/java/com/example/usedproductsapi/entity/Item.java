package com.example.usedproductsapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

//- id : 게시글 번호 (DB 인덱스)
//- title (String) :  게시글 제목
//- content (String) : 게시글 내용
//- price(int) : 가격
//- username : 작성자

@Entity
@Getter
@Table(name="item")
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "username", nullable = false)
    private String username;
}
