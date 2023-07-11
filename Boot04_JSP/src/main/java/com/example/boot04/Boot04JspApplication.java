package com.example.boot04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링부트어플리케이션 어노테이션이 붙어있는 클래스 하위패키지는 자동으로 컴포넌트 스캔이 일어남 -> 적절한 어노테이션을 붙여놓으면 자동으로 bean이 됨
						//즉 @controller를 homecontroller에붙여놓으면 자동으롴 ㅓㄴ트롤러가 됨
public class Boot04JspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot04JspApplication.class, args);
	}

}
