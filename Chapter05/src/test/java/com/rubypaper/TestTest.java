package com.rubypaper;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) //메서드 실행순서를 정하는 방법
public class TestTest {

	@Test
	@Order(2) //숫자가 작은 @Test부터 실행됨
	public void test1() {
		System.out.println("Test1");
	}
	
	@Test
	@Order(1)
	public void test2() {
		System.out.println("Test2");
	}
}
