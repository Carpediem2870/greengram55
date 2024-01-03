package com.example.greengram33;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exam1 { // 클래스이름은 아무거나해도 상관없다.

    // 테스트 메서드가 여러개면 순서는 랜덤이다.

    @Test // 테스트하고싶은 메소드 위에 작성
    @DisplayName("테스트1") // 옵션
    public void test1(){
        System.out.println("test1");
        int sum = 2 + 2;
        Assertions.assertEquals(4, sum); // 왼쪽값(내가기대하는값)과 오른쪽값 비교 (같으면 테스트성공, 다르면 테스트실패)
    }

    @Test
    @DisplayName("테스트2")
    public void test2() {
        System.out.println("test2");
        int multi = 2 * 3;
        Assertions.assertEquals(6, multi); // 왼쪽이 내가 생각하는 값, 오른쪽이 실제로 나오는값
    }

    @Test
    @DisplayName("테스트3")
    public void test3() {
        System.out.println("test3");

        Assertions.assertEquals(4,MyUtils.sum(2, 2));
        Assertions.assertEquals(5,MyUtils.sum(2, 3));
        Assertions.assertEquals(15,MyUtils.sum(12, 3));
        Assertions.assertEquals(18,MyUtils.sum(15, 3));
    }

    @Test
    @DisplayName("테스트4")
    public void test4() {
        System.out.println("test4");
        MyUtils utils = new MyUtils();
        Assertions.assertEquals(4,utils.multi(2,2));
        Assertions.assertEquals(8,utils.multi(2,4));
        Assertions.assertEquals(16,utils.multi(4,4));
    }
}
