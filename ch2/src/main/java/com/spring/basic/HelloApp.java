package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		
		System.out.println("=== 컨테이너 구동 전 ===");
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		System.out.println("=== 컨테이너 구동 후 ===");
		
		MessageBean msg = (MessageBean) ctx.getBean("msg");
		msg.sayHello("홍길동1");
		
		
		
	}

}
