package com.example.junit5practice;

import org.junit.jupiter.api.*;

public class BeforeAfterTest {
    // Things that needs to be executed before the execution
    @BeforeEach
    void setUp() {
        System.out.println("Before Test code started");

    }

    @AfterEach
    void tearDown() {
        System.out.println("Right after the test code");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Right before the test code started");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Right after all the test code executed");
    }

    @Test
    void test1() {
        System.out.println("Test Code 1");
    }

    @Test
    void test2() {
        System.out.println("Test Code 2");
    }
}
