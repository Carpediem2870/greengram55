package com.example.greengram33;

import org.junit.jupiter.api.*;

public class Exam2 {
    @BeforeAll // 아래 @Test 들이 실행되기 전에 딱 한번만 실행됨 // all은 반드시 static 붙여야함
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll // 아래 @Test 들이 실행된 후에 딱 한번만 실행됨 // all은 반드시 static 붙여야함
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach // 각 @Test 테스트 이전에 한 번씩 실행
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @AfterEach // 각 @Test 테스트 이전에 한 번씩 실행
    public void AfterEach() {
        System.out.println("AfterEach");
    }

    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");

    }

    @Test
    public void test3() {
        System.out.println("test3");
    }
}
