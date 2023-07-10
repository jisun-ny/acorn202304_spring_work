package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class Boot01HelloApplication {
	/*
	 * Spring Boot Application의 시작점, 도입점이 되는 main 메소드
	 */

	public static void main(String[] args) {
		SpringApplication.run(Boot01HelloApplication.class, args);
		
		//resources 폴더의 config.xml 문서를 로딩한 후 bean을 생성해서 관리함(Legacy방식)
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		//스프링이 관리하는 객체중 Car type을 얻어와서 사용
		Car car1 = ctx.getBean(Car.class);
		car1.drive();
		
	}

}
