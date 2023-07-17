package com.example.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
 * 	[Spring MVC 관련 설정]
 * 
 * 1. WebMvcConfigurer 인터페이스를 구현한다
 * 2. 설정에 필요한 메소드만 오버라이딩한다.
 * 	주로 Resource Hanler, Interceptor, view page 관련 설정을 여기서 한다.
 * (Spring에서는 Servlet-context.xml에서 설정해줬다)
 * 3. 설정에 관련된 클래스에는 @Configuration 어노테이션을 등록한다.
 */

import com.example.boot07.Interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//LoginInterceptor를 DI(주입) 받는다.
	@Autowired LoginInterceptor loginInter;
	
	//Interceptor를 추가할 때 오버라이드 하는 메소드
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 메소드의 인자로 전달되는 InterceptorRegistry 객체를 이용해서 Interceptor를 등록하면 된다.
		
		
		
		//registry.addInterceptor(loginInter);
								//LoginInterceptor 로 생성된 객체 (bean)이 전달이 되어야함
								//즉 주입 받으면 됨 (bean끼리는 서로 주입가능)
								//how? -> 필드에다가 @autowired해서 주입받아서 넣어줌.
		//InterceptorRegistry 메소드를 콜 하는 것인데 InterceptorRegistry를 리턴해줌
		// -> 즉 .동작 .동작.동작 가능
		
		registry.addInterceptor(loginInter)
			.addPathPatterns("/users/*, /cafe/*, /file/*, /gallery/*") //.addPathPatternsg할 때 String... 으로 문자열을 여러개 전달해도 되고, List<String>으로 전달해도 된다.
			.excludePathPatterns("/users/loginform", "/users/login", "/users/signup_form", "/users/signup",
					"/cafe/list", "/cafe/detail", "/cafe/ajax_comment_list",
					"/file/list", "/file/download",
					"/gallery/list", "/gallery/detail", "/gallery/images");
		/*
		 * excludePathPatterns 메소드는 가변인자를 받도록 설계되어있다.
		 */
//			이렇게 하나하나 나열하지 말고 한줄에 ,로 연결해서 전달 할 수 있다.
//			.excludePathPatterns("/users/login")
//			.excludePathPatterns("/users/signup_form")
//			.excludePathPatterns("/users/signup"); 
		
		
/*
		public class Gun{
			public void fire() {
				
			}
		}
		
		Gun g1 = new Gun();
		g1.fire();
		g1.fire();
		g1.fire();
		//이런 식으로 사용 가능
		
		
		public class MachineGun {
			public MachineGun fire() {
				return this;
			}
		}
		
		//이 메소드를 호출하면 자신의 참조값을 다시 되돌려줌.
		MachineGun g2 = new MachineGun();
		g2.fire().fire().fire();
		// .동작 .동작.동작 이렇게 사용 가능.( 메소드 안에서 this를 return해 주는 경우)
		 
		 new MachineGun().fire().fire()...
		 //여러개의 설정을 한번에 해야할 때 잘 사용..
*/
		
		
	}
	// webapp/resources폴더 설정
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resoures/**").addResourceLocations("/resources/");
	}
}
