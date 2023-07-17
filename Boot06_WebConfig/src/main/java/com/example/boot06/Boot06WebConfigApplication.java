package com.example.boot06;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.boot06.Boot06WebConfigApplication;

/*
 *  @PropertySource(value = "커스텀 properties 파일의 위치")
 * classpath:는 resources 폴더를 가리킨다.
 */
@SpringBootApplication
@PropertySource(value = "classpath:custom.properties")
public class Boot06WebConfigApplication {
		public static void main(String[] args) {
			//run하면 main 메소드가 스프링부트를 실행시켜줌
			SpringApplication.run(Boot06WebConfigApplication.class, args);
			
		MemberDto dto = new MemberDto();
		
		
		

	}
}
